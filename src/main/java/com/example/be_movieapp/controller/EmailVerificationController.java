package com.example.be_movieapp.controller;

import com.example.be_movieapp.service.EmailVerificationService;
import com.example.be_movieapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/verify")
public class EmailVerificationController {

    @Autowired
    private UserService userService;

    @PostMapping("/send-otp")
    public ResponseEntity<?> sendOtp(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        try {
            userService.sendOtpToEmail(email);
            return ResponseEntity.ok(Map.of("success", true, "message", "OTP đã được gửi"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("success", false, "error", e.getMessage()));
        }
    }
}

