package com.sleep.studyboot.config;

import com.sleep.studyboot.core.user.User;
import com.sleep.studyboot.core.user.UserAuth;
import com.sleep.studyboot.core.user.UserAuthService;
import com.sleep.studyboot.core.user.UserService;
import com.sleep.studyboot.dto.GithubUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class GithubOAuth2ClientAuthenticationProcessingFilter extends OAuth2ClientAuthenticationProcessingFilter {
    private static final String GITHUB_LOGIN_URL = "/login/github";
    private final UserAuthService userAuthService;
    private ObjectMapper mapper = new ObjectMapper();

    public GithubOAuth2ClientAuthenticationProcessingFilter(UserAuthService userAuthService) {
        super(GITHUB_LOGIN_URL);
        this.userAuthService = userAuthService;
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        final OAuth2AccessToken accessToken = restTemplate.getAccessToken();
        final OAuth2Authentication auth = (OAuth2Authentication) authResult;
        final Object details = auth.getUserAuthentication().getDetails();
        final GithubUserDetails userDetails = mapper.convertValue(details, GithubUserDetails.class);
        userDetails.setAccessToken(String.valueOf(accessToken));
        UserAuth userAuth = UserAuth.from(userDetails);

        final UsernamePasswordAuthenticationToken authenticationToken = userAuthService.doAuthentication(userAuth);

        super.successfulAuthentication(request, response, chain, authenticationToken);
    }

}
