package com.fpoly.dao;
import com.fpoly.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDAO extends JpaRepository<Product, Long> {
}