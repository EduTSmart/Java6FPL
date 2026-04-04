package com.fpoly.controller;

import com.fpoly.entity.Category;
import com.fpoly.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin("*") // Cho phép VueJS (thường chạy ở port 5173 hoặc 8080) gọi API mà không bị chặn lỗi CORS
public class CategoryRestApi {

    @Autowired
    private CategoryService categoryService;

    // 1. Lấy danh sách tất cả loại hàng (GET)
    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    // 2. Lấy 1 loại hàng theo ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<Category> getOne(@PathVariable("id") Long id) {
        Category category = categoryService.findById(id);
        if (category == null) {
            return ResponseEntity.notFound().build(); // Trả về lỗi 404 nếu không tìm thấy
        }
        return ResponseEntity.ok(category);
    }

    // 3. Thêm mới loại hàng (POST)
    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category category) {
        Category savedCategory = categoryService.save(category);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED); // Trả về 201 Created
    }

    // 4. Cập nhật loại hàng (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable("id") Long id, @RequestBody Category category) {
        Category existing = categoryService.findById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        category.setId(id); // Đảm bảo ghi đè đúng ID
        return ResponseEntity.ok(categoryService.save(category));
    }

    // 5. Xóa loại hàng (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (categoryService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        categoryService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}