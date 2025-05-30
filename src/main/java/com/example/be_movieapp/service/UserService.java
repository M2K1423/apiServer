package com.example.be_movieapp.service;

import com.example.be_movieapp.model.User;
import com.example.be_movieapp.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// ...

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User register(User user) {
        // 1. Kiểm tra xem người dùng đã tồn tại chưa
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Tên đăng nhập đã tồn tại");
        }

        // 2. Kiểm tra mật khẩu có hợp lệ không (tùy yêu cầu)
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Mật khẩu không được để trống");
        }

        // 3. Mã hóa mật khẩu
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // 4. Lưu người dùng mới
        return userRepository.save(user);
    }

    public boolean login(String username, String password) {
        return userRepository.findByUsername(username)
                .map(u -> passwordEncoder.matches(password, u.getPassword()))
                .orElse(false);
    }

}
