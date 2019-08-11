package com.sleep.studyboot.config;

import com.sleep.studyboot.core.security.CustomOAuth2UserService;
import com.sleep.studyboot.core.security.GithubAuthenticationFailureHandler;
import com.sleep.studyboot.core.security.GithubAuthenticationSuccessHandler;
import com.sleep.studyboot.core.security.OAuth2UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;
    private final GithubAuthenticationSuccessHandler successHandler;
    private final GithubAuthenticationFailureHandler failureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/", "/login**", "/oauth2/authorization/**").permitAll()
//                .antMatchers("/v1/**").permitAll()
                .anyRequest().authenticated()
            .and().exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
            .and().cors()
            .and().logout()
                .logoutSuccessUrl("/").permitAll()
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
            .and().oauth2Login()
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .userInfoEndpoint()
                .userService(customOAuth2UserService)
                .customUserType(OAuth2UserInfo.class, "github");
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
