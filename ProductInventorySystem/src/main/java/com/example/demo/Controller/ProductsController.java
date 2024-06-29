package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.LargeCategory;
import com.example.demo.entity.Manager;
import com.example.demo.entity.Manufacturer;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductStore;
import com.example.demo.entity.SmallCategory;
import com.example.demo.entity.Subcategory;
import com.example.demo.repository.ManagerRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ManufacturerService;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductStoreService;

@Controller
public class ProductsController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ManufacturerService manufacturerService;

	@Autowired
	private ProductStoreService productStoreService;

	@GetMapping("/products")
	public String showProductsPage(@RequestParam(required = false) Integer largeCategoryId,
			@RequestParam(required = false) Integer subCategoryId,
			@RequestParam(required = false) Integer smallCategoryId,
			@RequestParam(required = false) String search,
			@RequestParam(defaultValue = "1") int page, //アノテーション設定 1ページあたり3種表示
			@RequestParam(defaultValue = "3") int size,
			Model model) {
		// カテゴリのリストを取得しモデルに追加
		List<LargeCategory> categories = categoryService.getAllCategories();
		model.addAttribute("categories", categories);

		// 商品検索結果を取得しモデルに追加
		Page<Product> productsPage = productService.searchProducts(largeCategoryId, subCategoryId, smallCategoryId,
				search, page, size);
		model.addAttribute("products", productsPage.getContent());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", productsPage.getTotalPages());

		return "products"; // productページにアクセス
	}

	    //商品詳細画面における情報取得
	@GetMapping("/product/details/{productStoreId}")
	public String getProductDetails(@PathVariable Integer productStoreId, Model model) {
		ProductStore productStore = productStoreService.getProductStoreById(productStoreId);
		if (productStore == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ProductStore not found for ID: " + productStoreId);
		}

		Product product = productStore.getProduct();
		Manufacturer manufacturer = product.getManufacturer();
		String manufacturerName = manufacturer != null ? manufacturer.getManufacturerName() : "Unknown";

		// SmallCategoryとLargeCategoryを取得
		SmallCategory smallCategory = product.getSmallCategory();
		Subcategory subcategory = smallCategory != null ? smallCategory.getSubcategory() : null; // Subcategoryを取得
		LargeCategory largeCategory = subcategory != null ? subcategory.getLargeCategory() : null; // LargeCategoryを取得

		String largeCategoryName = largeCategory != null ? largeCategory.getLargeCategoryName() : "Unknown";
		String subcategoryName = subcategory != null ? subcategory.getSubcategoryName() : "Unknown"; // Subcategory名を設定
		String smallCategoryName = smallCategory != null ? smallCategory.getSmallCategoryName() : "Unknown"; // SmallCategory名を設定

		model.addAttribute("product", product);
		model.addAttribute("manufacturerName", manufacturerName);
		model.addAttribute("productStore", productStore);
		model.addAttribute("largeCategoryName", largeCategoryName);
		model.addAttribute("subcategoryName", subcategoryName); // Subcategory名をモデルに追加
		model.addAttribute("smallCategoryName", smallCategoryName); // SmallCategory名をモデルに追加

		return "product-details"; // product-detailsにアクセス
	}

	//API JSON形式でデータを返すエンドポイントと定義 大カテゴリIDに関連するサブカテゴリ、小カテゴリを取得
	@RestController
	public static class CategoryRestController {

		@Autowired
		private CategoryService categoryService;

		@GetMapping("/subcategories/{largeCategoryId}")
		public List<Subcategory> getSubcategories(@PathVariable Integer largeCategoryId) {
			return categoryService.getSubcategoriesByLargeCategoryId(largeCategoryId);
		}

		@GetMapping("/smallcategories/{subcategoryId}")
		public List<SmallCategory> getSmallCategories(@PathVariable Integer subcategoryId) {
			return categoryService.getSmallCategoriesBySubcategoryId(subcategoryId);
		}
	}

	//API JSON形式でデータを返すエンドポイントと定義 特定商品、StoreID Storeごとの商品情報（店舗販売価格、在庫など）を取得
	@RestController
	public static class ProductRestController {

		@Autowired
		private ProductService productService;

		@Autowired
		private ProductStoreService productStoreService;

		@Autowired
		private ManagerRepository managerRepository;

		@GetMapping("/api/products")
		public ResponseEntity<Page<ProductStore>> getProducts(@RequestParam String productName,
				@RequestParam int page,
				@RequestParam int size) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String email = authentication.getName();
			Integer storeId = getStoreIdByEmail(email);

			Page<ProductStore> productStores = productStoreService.getProductStoresByNameAndStoreId(productName,
					storeId, page, size);
			return ResponseEntity.ok(productStores);
		}

		private Integer getStoreIdByEmail(String email) {
			Manager manager = managerRepository.findByEmail(email);
			if (manager == null) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Manager not found for email: " + email);
			}
			return manager.getStoreId();
		}
	}
}
