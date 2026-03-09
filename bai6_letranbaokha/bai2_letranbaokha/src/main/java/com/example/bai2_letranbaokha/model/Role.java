package com.example.bai2_letranbaokha.model;

import jakarta.persistence.*; // Thay đổi ở đây
import lombok.Getter;
import lombok.Setter;
// Xóa dòng import org.springframework.data.annotation.Id;

@Entity
@Table(name = "role")
@Getter
@Setter
public class Role {
    @Id // Bây giờ nó sẽ hiểu là jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}