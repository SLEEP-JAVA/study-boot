package com.sleep.studyboot.core.user;

import com.sleep.studyboot.dto.GithubUserDetails;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "user_auth")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "sns_id")
    private String snsId;

    @Column(name = "avartar_url")
    private String avartarUrl;

    @Column(name = "email")
    private String email;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "provider_id")
    private long providerId;

    @Column(name = "provider")
    private ProviderType provider;

    @Builder
    public UserAuth(String name, String snsId, String avartarUrl, String email, String accessToken, long providerId, ProviderType provider) {
        this.name = name;
        this.snsId = snsId;
        this.avartarUrl = avartarUrl;
        this.email = email;
        this.accessToken = accessToken;
        this.providerId = providerId;
        this.provider = provider;
    }

    public static UserAuth from(GithubUserDetails userDetails) {
        return UserAuth.builder()
                .name(userDetails.getName())
                .snsId(userDetails.getLogin())
                .avartarUrl(userDetails.getAvatarUrl())
                .email(userDetails.getEmail())
                .accessToken(userDetails.getAccessToken())
                .providerId(userDetails.getId())
                .provider(ProviderType.GITHUB)
                .build();
    }
}
