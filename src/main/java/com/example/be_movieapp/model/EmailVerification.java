package com.example.be_movieapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id; // Giữ nguyên @Id trên email
import jakarta.persistence.Table; // Thêm lại nếu bạn muốn đặt tên bảng cụ thể
import java.time.LocalDateTime;

@Entity
@Table(name = "email_verifications") // Tùy chọn: đặt tên bảng rõ ràng
public class EmailVerification {

    @Id // Đánh dấu email là khóa chính
    private String email;

    private String otp;

    private LocalDateTime expiresAt;

    public EmailVerification() {
    }

    public EmailVerification(String email, String otp, LocalDateTime expiresAt) {
        this.email = email;
        this.otp = otp;
        this.expiresAt = expiresAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    @Override
    public String toString() {
        return "EmailVerification{" +
                "email='" + email + '\'' +
                ", otp='" + otp + '\'' +
                ", expiresAt=" + expiresAt +
                '}';
    }
}