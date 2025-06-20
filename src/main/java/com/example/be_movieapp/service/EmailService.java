package com.example.be_movieapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendOtpEmail(String to, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("🎬 Xác minh tài khoản MovieApp"); // Tiêu đề email

        String content = "Xin chào,\n\n"
                + "Bạn vừa yêu cầu tạo tài khoản trên MovieApp.\n"
                + "Để hoàn tất quá trình đăng ký, vui lòng sử dụng mã xác minh (OTP) bên dưới:\n\n"
                + "🔐 MÃ OTP CỦA BẠN: " + otp + "\n\n"
                + "Lưu ý:\n"
                + "- Mã OTP có hiệu lực trong 5 phút.\n"
                + "- Vui lòng KHÔNG chia sẻ mã này với bất kỳ ai.\n\n"
                + "Trân trọng,\n"
                + "MovieApp";

        message.setText(content);
        mailSender.send(message);
    }

}