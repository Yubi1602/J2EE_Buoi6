package com.example.bai2_letranbaokha.service;



import com.example.bai2_letranbaokha.model.Account;
import com.example.bai2_letranbaokha.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Tìm account dựa trên username (login_name)
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy người dùng: " + username));

        return User.withUsername(account.getUsername())
                .password(account.getPassword()) // Mật khẩu đã mã hóa BCrypt từ DB
                .authorities(account.getRoles().stream()
                        // Sửa lại chỗ này: Lấy tên role (VD: ROLE_ADMIN) từ database
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList()))
                .build();
    }
}