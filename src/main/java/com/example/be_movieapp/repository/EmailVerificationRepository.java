package com.example.be_movieapp.repository;

import com.example.be_movieapp.model.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailVerificationRepository extends JpaRepository<EmailVerification, Long> {
    // Phương thức để tìm kiếm EmailVerification theo email
    Optional<EmailVerification> findByEmail(String email);
}