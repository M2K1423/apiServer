package com.example.be_movieapp.service;

import com.example.be_movieapp.model.User;
import com.example.be_movieapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailVerificationService emailVerificationService;

    // Không cần inject EmailService trực tiếp vào UserService nữa
    // vì EmailVerificationService đã gọi EmailService bên trong nó
    // @Autowired
    // private EmailService emailService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void sendOtpToEmail(String email) {
        // Kiểm tra xem email đã được sử dụng chưa (nếu đây là luồng đăng ký)
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email đã được sử dụng");
        }

        // GỌI PHƯƠNG THỨC 'sendOtp' TRONG EmailVerificationService
        // Phương thức này sẽ TỰ ĐỘNG TẠO OTP, LƯU VÀO DB và GỬI EMAIL.
        emailVerificationService.sendOtp(email);
    }

    public User register(User user, String otp) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Tên đăng nhập đã tồn tại");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email đã được sử dụng");
        }

        // Xác minh OTP
        if (!emailVerificationService.verifyOtp(user.getEmail(), otp)) {
            throw new RuntimeException("OTP không hợp lệ hoặc đã hết hạn");
        }

        // Mã hóa mật khẩu
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public User login(String username, String password) {
        return userRepository.findByUsername(username)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElse(null);
    }
}