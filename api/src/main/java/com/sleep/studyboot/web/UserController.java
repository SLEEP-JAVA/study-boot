package com.sleep.studyboot.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/loginFailure")
    public ResponseEntity<String> loginFailure() {
        return ResponseEntity.badRequest().body("login failure");
    }
}
