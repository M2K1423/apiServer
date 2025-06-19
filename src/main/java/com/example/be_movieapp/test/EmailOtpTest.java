package com.example.be_movieapp.test;

import com.example.be_movieapp.service.EmailService;
import com.example.be_movieapp.service.EmailVerificationService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import com.example.be_movieapp.BeMovieAppApplication;

public class EmailOtpTest {
    public static void main(String[] args) {
        // Lấy context Spring
        ApplicationContext context = SpringApplication.run(BeMovieAppApplication.class, args);

        // Lấy các bean service
        EmailVerificationService emailVerificationService = context.getBean(EmailVerificationService.class);
        EmailService emailService = context.getBean(EmailService.class);

        // Email để test
        String email = "phuongtran35789@gmail.com";

        // Gửi OTP
        String otp = emailVerificationService.generateOtp(email);
        emailService.sendOtpEmail(email, otp);
        System.out.println("✅ Đã gửi OTP: " + otp);

        // Test xác minh OTP
        boolean isValid = emailVerificationService.verifyOtp(email, otp);
        System.out.println("✅ OTP hợp lệ? " + isValid);
    }
}
