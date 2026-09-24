package com.jorge.course.antigravity.springboot.controllers;

import com.jorge.course.antigravity.springboot.models.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
        // return ResponseEntity.ok(response);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping({ "/greeting" })
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Map<String, Object> index2() {
        User user = new User("Jorge", "Doe", "[EMAIL_ADDRESS]");
        Map<String, Object> response = new HashMap<>();
        response.put("message", "hola mundo desde spring boot");
        response.put("user", user);
        return response;
    }

    // http://localhost:8080/api/details
    // http://localhost:8080/api/user
    @GetMapping(value = { "/details", "/user" }, produces = "application/json")
    public ResponseEntity<User> user() {
        User user = new User("Jorge", "Doe", "[EMAIL_ADDRESS]");
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .header("Content-Type", "application/json")
                .header("X-Custom-Header", "mi header customizado")
                .header("Authorization", "Bearer token_123456")
                .body(user);
    }

    // http://localhost:8080/api/details-text
    // http://localhost:8080/api/user-text
    // http://localhost:8080/api/details (con header Accept: text/plain)
    @GetMapping(value = { "/details", "/user", "/details-text", "/user-text" }, produces = "text/plain")
    public ResponseEntity<String> userPlainText() {
        User user = new User("Jorge", "Doe", "[EMAIL_ADDRESS]");
        return ResponseEntity.ok(user.toString());
    }

    @GetMapping(value = { "/user-text-new" })
    public ResponseEntity<String> userPlainText2() {
        User user = new User("Jorge", "Doe", "[EMAIL_ADDRESS]");
        return ResponseEntity.status(HttpStatus.OK)
                // .header("Content-Type", "application/json")
                .contentType(MediaType.TEXT_PLAIN)
                .body(user.toString());
    }

    // http://localhost:8080/api/details-xml
    // http://localhost:8080/api/user-xml
    // http://localhost:8080/api/details (con cabecera Accept: application/xml)
    @GetMapping(value = { "/details", "/user", "/details-xml", "/user-xml" }, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<User> userXml() {
        User user = new User("Jorge", "Doe", "[EMAIL_ADDRESS]");
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_XML)
                .body(user);
    }

}

