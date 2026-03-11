package com.fpoly.entity; // Đổi thành tên package của bạn

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "J6users")
public class User {
    @Id
    private String username;
    private String password;
    private boolean enabled;
    
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserRole> userRoles;
}