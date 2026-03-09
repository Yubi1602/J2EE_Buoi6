package com.example.bai2_letranbaokha.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tiêu đề sách không được để trống")
    @Size(min = 2, max = 200, message = "Tiêu đề phải từ 2 đến 200 ký tự")
    private String title;

    @NotBlank(message = "Tên tác giả không được để trống")
    private String author;

    // Constructor tùy chỉnh để tạo nhanh đối tượng
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }
}