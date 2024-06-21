package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
// コメントアウトされたインポート
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.service.ProductService;

@Controller
public class ProductDetailsController {

    @Autowired
    private ProductService productService;

    // 一旦使用しない
    // @GetMapping("/product/details/{productId}")
    // public String viewProductDetails(@PathVariable Integer productId, Model model) {
    //     Product product = productService.getProductById(productId);
    //     model.addAttribute("product", product);
    //     return "product-details";
    // }
}
