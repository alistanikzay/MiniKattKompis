package org.example.minikattkompis.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
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
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/", "/index.html", "/login.html", "/register.html",
                                "/add-cat.html", "/edit-cat.html", "/cats.html",
                                "/cat-detail.html", "/cat-recommendations.html",
                                "/cat-reminders.html", "/weather.html",
                                "/css/**", "/js/**", "/images/**"
                        ).permitAll()
                        .requestMatchers("/premium/**").hasRole("PREMIUM")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login.html")
                        .defaultSuccessUrl("/index.html", true)
                        .userInfoEndpoint(userInfo -> userInfo
                                .oidcUserService(this.oidcUserService())
                                .userService(this.facebookUserService())
                        )
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/index.html")
                        .permitAll()
                )
                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"));

        return http.build();
    }

    private OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService() {
        final OidcUserService delegate = new OidcUserService();

        return userRequest -> {
            OidcUser oidcUser = delegate.loadUser(userRequest);

            SimpleAuthorityMapper authorityMapper = new SimpleAuthorityMapper();
            authorityMapper.setConvertToUpperCase(true);

            // Mappa till Set<GrantedAuthority>
            Set<GrantedAuthority> mappedAuthorities = new HashSet<>(authorityMapper.mapAuthorities(oidcUser.getAuthorities()));

            mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

            if (oidcUser.getEmail() != null && oidcUser.getEmail().endsWith("@premium.com")) {
                mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_PREMIUM"));
            }

            return new DefaultOidcUser(mappedAuthorities, oidcUser.getIdToken(), oidcUser.getUserInfo());
        };
    }

    private OAuth2UserService<OAuth2UserRequest, OAuth2User> facebookUserService() {
        return userRequest -> {
            OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate =
                    new org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService();

            OAuth2User oAuth2User = delegate.loadUser(userRequest);

            Set<GrantedAuthority> mappedAuthorities = new HashSet<>();
            mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

            String email = (String) oAuth2User.getAttributes().get("email");
            if (email != null && email.endsWith("@premium.com")) {
                mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_PREMIUM"));
            }

            return new DefaultOAuth2User(mappedAuthorities, oAuth2User.getAttributes(), "id");
        };
    }
}
