package com.example.demo.service;

//必要なツールをインポートしています
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

//Userクラスを使うためにインポートしています
import com.example.demo.entity.Manager;

//UserDetailsインターフェースを実装したManagerServiceというクラスを作成します。これはSpring Securityでユーザー情報を扱うためのクラスです。
public class ManagerService implements UserDetails {
	 private  Manager manager;

	 public ManagerService(Manager manager) {
	     this.manager = manager;
	 }

	 @Override
	 public Collection<? extends GrantedAuthority> getAuthorities() {
	     return Collections.singleton(new SimpleGrantedAuthority("USER")); // ロールに応じて変更する
	 }

	 @Override
	 public String getPassword() {
	     return manager.getPassword();
	 }

	 @Override
	 public String getUsername() {
	     return manager.getEmail(); // 認証にemailを使用
	 }

	 @Override
	 public boolean isAccountNonExpired() {
	     return true;
	 }

	 @Override
	 public boolean isAccountNonLocked() {
	     return true;
	 }

	 @Override
	 public boolean isCredentialsNonExpired() {
	     return true;
	 }

	 @Override
	 public boolean isEnabled() {
	     return true;
	 }
	}