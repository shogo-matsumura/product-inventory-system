package com.example.demo.repository;
//必要なツールをインポートしています
import org.springframework.data.jpa.repository.JpaRepository;

//Userクラスを使うためにインポートしています
import com.example.demo.entity.Manager;

//UserRepositoryというインターフェースを作成します。JpaRepositoryを拡張して、UserオブジェクトとそれらのIDとしてLong型を扱えるようにします。
public interface ManagerRepository extends JpaRepository<Manager, Long> {
 // ユーザー名でユーザーを探すメソッド。ユーザー名をパラメータとして渡すと、そのユーザー名を持つユーザーをデータベースから探して返します。
    Manager findByEmail(String email);
}

