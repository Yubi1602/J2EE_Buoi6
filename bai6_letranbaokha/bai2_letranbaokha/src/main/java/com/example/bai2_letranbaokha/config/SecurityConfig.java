package com.example.bai2_letranbaokha.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // BƯỚC 1: Xóa bỏ hoàn toàn Bean userDetailsService tại đây.
    // Spring sẽ tự tìm đến AccountService của bạn để đăng nhập từ DB.

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Cho phép xem danh sách công khai
                        .requestMatchers("/", "/home", "/books", "/products").permitAll()

                        // Chỉ ADMIN mới có quyền CUD (Thêm, Sửa, Xóa) cho cả Sách và Sản phẩm
                        .requestMatchers("/books/create/**", "/books/edit/**", "/books/delete/**").hasRole("ADMIN")
                        .requestMatchers("/products/create/**", "/products/edit/**", "/products/delete/**").hasRole("ADMIN")

                        // Các trang còn lại phải đăng nhập
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/home", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/home")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Trả về BCryptPasswordEncoder để khớp với mật khẩu tài khoản "kha" bạn vừa tạo
        return new BCryptPasswordEncoder();
    }
}