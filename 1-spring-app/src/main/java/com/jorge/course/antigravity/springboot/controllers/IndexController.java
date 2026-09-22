package com.jorge.course.antigravity.springboot.controllers;

import com.jorge.course.antigravity.springboot.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class IndexController {

    @GetMapping({ "/index" })
    public ResponseEntity<Map<String, Object>> index() {
        User user = new User("Jorge", "Doe", "[EMAIL_ADDRESS]");
        Map<String, Object> response = new HashMap<>();
        response.put("message", "hola mundo desde spring boot");
        response.put("user", user);
        return ResponseEntity.ok(response);
    }

    @GetMapping({ "/greeting" })
    public Map<String, Object> index2() {
        User user = new User("Jorge", "Doe", "[EMAIL_ADDRESS]");
        Map<String, Object> response = new HashMap<>();
        response.put("message", "hola mundo desde spring boot");
        response.put("user", user);
        return response;
    }

    // http://localhost:8080/detail
    // http://localhost:8080/user
    @GetMapping({ "/details", "/user" })
    public ResponseEntity<User> details() {
        User user = new User("Jorge", "Doe", "[EMAIL_ADDRESS]");
        return ResponseEntity.ok(user);
    }

}
