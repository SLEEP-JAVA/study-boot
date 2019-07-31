package com.sleep.studyboot.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final HttpSession httpSession;

    @GetMapping("/")
    public ResponseEntity<String> home() {
        String email = (String) httpSession.getAttribute("LOGIN_USER_EMAIL");

        if (ObjectUtils.isEmpty(email)) {
            return ResponseEntity.ok().body("home");
        }

        return ResponseEntity.ok().body(email);
    }
}
