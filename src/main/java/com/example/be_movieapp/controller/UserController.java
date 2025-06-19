package com.example.be_movieapp.controller;

import com.example.be_movieapp.model.User;
import com.example.be_movieapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;

    // Lấy danh sách người dùng
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Đăng ký người dùng
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody Map<String, String> registerData) {
        Map<String, Object> response = new HashMap<>();

        try {
            User newUser = new User();
            newUser.setUsername(registerData.get("username"));
            newUser.setPassword(registerData.get("password"));
            newUser.setEmail(registerData.get("email"));

            String otp = registerData.get("otp");

            User savedUser = userService.register(newUser, otp);

            response.put("success", true);
            response.put("message", "Đăng ký thành công");
            response.put("user", savedUser.getUsername());
            response.put("email", savedUser.getEmail());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("success", false);
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }

    // Đăng nhập người dùng
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");

        User user = userService.login(username, password); // trả về User nếu đúng

        Map<String, Object> response = new HashMap<>();
        if (user != null) {
            response.put("success", true);
            response.put("message", "Login success");
            response.put("userId", user.getId()); // ✅ Trả về userId
            response.put("username", user.getUsername());
        } else {
            response.put("success", false);
            response.put("error", "Invalid credentials");
        }

        return ResponseEntity.ok(response);
    }

}
