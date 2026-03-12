package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/users")
public class UserProfileController {

    @Autowired
    private AuthSoapClient authSoapClient;

    private Map<String, Map<String, String>> profileDb = new HashMap<>();

    public UserProfileController() {
        Map<String, String> dummyProfile = new HashMap<>();
        dummyProfile.put("name", "Bat-Erdene");
        dummyProfile.put("email", "bat@example.com");
        dummyProfile.put("bio", "I love SOA architecture!");
        profileDb.put("1", dummyProfile);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProfile(@PathVariable String id, @RequestHeader(value = "Authorization", required = false) String token) {
        
        if (token == null || token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error: Missing Authorization Header");
        }

        boolean isValid = authSoapClient.isTokenValid(token);
        
        if (!isValid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error: Invalid Token");
        }

        if (profileDb.containsKey(id)) {
            return ResponseEntity.ok(profileDb.get(id));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Profile not found");
        }
    }
}