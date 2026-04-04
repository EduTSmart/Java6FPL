package com.fpoly.dao;
import com.fpoly.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDAO extends JpaRepository<Category, Long> {
}