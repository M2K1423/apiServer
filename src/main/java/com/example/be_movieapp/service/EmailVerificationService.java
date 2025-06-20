package com.example.be_movieapp.service;

import com.example.be_movieapp.model.EmailVerification;
import com.example.be_movieapp.repository.EmailVerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class EmailVerificationService {

    @Autowired
    private EmailVerificationRepository emailVerificationRepository;

    @Autowired // Inject EmailService vào đây
    private EmailService emailService;

    // Phương thức này sẽ được gọi bởi UserService để tạo và gửi OTP
    public void sendOtp(String email) {
        // 1. Xóa các OTP cũ của email này nếu có (tùy chọn, để tránh nhiều OTP cùng lúc)
        emailVerificationRepository.findByEmail(email).ifPresent(emailVerificationRepository::delete);

        // 2. Tạo OTP mới
        String otp = String.valueOf(new Random().nextInt(900000) + 100000); // OTP 6 chữ số
        LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(5); // Hết hạn sau 5 phút

        // 3. Lưu OTP vào database
        EmailVerification entity = new EmailVerification();
        entity.setEmail(email);
        entity.setOtp(otp);
        entity.setExpiresAt(expiresAt);
        emailVerificationRepository.save(entity);

        // 4. Gửi email chứa OTP
        emailService.sendOtpEmail(email, otp);
    }

    public boolean verifyOtp(String email, String otp) {
        Optional<EmailVerification> recordOptional = emailVerificationRepository.findByEmail(email);

        if (recordOptional.isEmpty()) {
            return false; // Không tìm thấy bản ghi OTP cho email này
        }

        EmailVerification record = recordOptional.get();

        // Kiểm tra OTP có khớp và còn hạn không
        return record.getOtp().equals(otp) && record.getExpiresAt().isAfter(LocalDateTime.now());
    }
}