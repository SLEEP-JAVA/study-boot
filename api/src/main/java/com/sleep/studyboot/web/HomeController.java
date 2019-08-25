package com.sleep.studyboot.web;

import com.sleep.studyboot.core.security.CustomUserDetailsService;
import com.sleep.studyboot.core.security.OAuth2UserInfo;
import com.sleep.studyboot.dto.user.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final CustomUserDetailsService userDetailsService;

    @GetMapping("/")
    public ResponseEntity<String> home(@AuthenticationPrincipal OAuth2UserInfo userInfo) {
        if (userInfo == null) {
            return ResponseEntity.ok().body("home");
        }

        // Usage example
        LoginUser loginUser = userDetailsService.loadUserByUsername(userInfo.getEmail());

        return ResponseEntity.ok().body(loginUser.getEmail());
    }
}
