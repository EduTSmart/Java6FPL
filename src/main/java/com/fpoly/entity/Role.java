package com.fpoly.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "J6roles")
public class Role {
    @Id
    private String id;
    private String name;
    
    @OneToMany(mappedBy = "role")
    private List<UserRole> userRoles;
}