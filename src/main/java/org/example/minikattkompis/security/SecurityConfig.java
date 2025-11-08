package org.example.minikattkompis.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Rollbaserad autorisation
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/cats/**", "/weather/**", "/api/cats/**", "/css/**").permitAll()
                        .requestMatchers("/premium/**").hasRole("PREMIUM")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                // OAuth2-login med Google och Facebook
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo
                                .oidcUserService(this.oidcUserService())   // Google (OIDC)
                                .userService(this.facebookUserService())   // Facebook (OAuth2)
                        )
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .permitAll()
                )
                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"));

        return http.build();
    }

    /**
     * OidcUserService för Google-login med dynamisk rollmappning
     */
    private OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService() {
        final OidcUserService delegate = new OidcUserService();

        return userRequest -> {
            OidcUser oidcUser = delegate.loadUser(userRequest);

            SimpleAuthorityMapper authorityMapper = new SimpleAuthorityMapper();
            authorityMapper.setConvertToUpperCase(true);

            var mappedAuthorities = new HashSet<>(authorityMapper.mapAuthorities(oidcUser.getAuthorities()));

            // Alla får ROLE_USER
            mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

            // Exempel: premium baserat på e-postdomän
            if (oidcUser.getEmail() != null && oidcUser.getEmail().endsWith("@premium.com")) {
                mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_PREMIUM"));
            }

            return new DefaultOidcUser(mappedAuthorities, oidcUser.getIdToken(), oidcUser.getUserInfo());
        };
    }

    /**
     * OAuth2UserService för Facebook-login med dynamisk rollmappning
     */
    private OAuth2UserService<OAuth2UserRequest, OAuth2User> facebookUserService() {
        return userRequest -> {
            OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate =
                    new org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService();

            OAuth2User oAuth2User = delegate.loadUser(userRequest);

            Set<SimpleGrantedAuthority> mappedAuthorities = new HashSet<>();
            mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

            // Exempel: premium baserat på e-postdomän
            String email = (String) oAuth2User.getAttributes().get("email");
            if (email != null && email.endsWith("@premium.com")) {
                mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_PREMIUM"));
            }

            return new DefaultOAuth2User(mappedAuthorities, oAuth2User.getAttributes(), "id");
        };
    }
}
