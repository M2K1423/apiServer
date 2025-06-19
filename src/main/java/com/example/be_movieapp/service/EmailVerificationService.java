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

    public String generateOtp(String email) {
        String otp = String.valueOf(new Random().nextInt(900000) + 100000); // OTP 6 chữ số
        LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(5); // Hết hạn sau 5 phút

        EmailVerification entity = new EmailVerification();
        entity.setEmail(email);
        entity.setOtp(otp);
        entity.setExpiresAt(expiresAt);

        emailVerificationRepository.save(entity);

        return otp;
    }

    public boolean verifyOtp(String email, String otp) {
        Optional<EmailVerification> record = emailVerificationRepository.findByEmail(email);
        return record.isPresent()
                && record.get().getOtp().equals(otp)
                && record.get().getExpiresAt().isAfter(LocalDateTime.now());
    }
}
