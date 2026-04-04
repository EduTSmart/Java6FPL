package com.fpoly.service.impl;

import com.fpoly.dao.CategoryDAO;
import com.fpoly.entity.Category;
import com.fpoly.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    // Tiêm (Inject) CategoryDAO vào để tương tác với Database
    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    public List<Category> findAll() {
        return categoryDAO.findAll();
    }

    @Override
    public Category findById(Long id) {
        // Sử dụng orElse(null) để trả về null nếu không tìm thấy id
        return categoryDAO.findById(id).orElse(null);
    }

    @Override
    public Category save(Category category) {
        // Hàm save của JpaRepository dùng cho cả thêm mới và cập nhật
        return categoryDAO.save(category);
    }

    @Override
    public void deleteById(Long id) {
        categoryDAO.deleteById(id);
    }
}