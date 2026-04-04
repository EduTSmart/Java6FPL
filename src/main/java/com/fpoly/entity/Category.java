package com.fpoly.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "categories")
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // (Tùy chọn) Liên kết ngược lại để lấy danh sách sản phẩm theo loại
    // @OneToMany(mappedBy = "category")
    // private List<Product> products;
}