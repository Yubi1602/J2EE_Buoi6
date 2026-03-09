package com.example.bai2_letranbaokha.repository;

import com.example.bai2_letranbaokha.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    // Phương thức tìm người dùng bằng login_name (username)
    Optional<Account> findByUsername(String username);
}