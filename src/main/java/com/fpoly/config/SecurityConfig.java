package com.fpoly.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;

import jakarta.servlet.http.HttpSession;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true) // Của Bài 2
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        // 1. Tắt CSRF và CORS (Của Bài 2)
        http.csrf(csrf -> csrf.disable()).cors(cors -> cors.disable());

        // 2. KHAI BÁO BẮT BUỘC ĐỂ TRÁNH LỖI (Của Bài 2)
        // Mở cửa cho tất cả các đường dẫn, việc chặn sẽ do @PreAuthorize lo
        http.authorizeHttpRequests(req -> req
            .anyRequest().permitAll() 
        );

        // 3. Cấu hình đăng nhập OAuth2 (Của Bài 1)
        http.oauth2Login(login -> login
            .permitAll()
            .successHandler((request, response, authentication) -> {
                // Lấy thông tin user từ Google trả về
                DefaultOidcUser oidcUser = (DefaultOidcUser) authentication.getPrincipal();
                String username = oidcUser.getEmail(); 
                String role = "OAUTH"; 

                // Tạo đối tượng UserDetails mới 
                UserDetails newUser = User.withUsername(username)
                        .password("{noop}") 
                        .roles(role)
                        .build();

                // Set Authentication mới vào Context
                Authentication newAuth = new UsernamePasswordAuthenticationToken(
                        newUser, null, newUser.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(newAuth);

                // Chuyển hướng
                HttpSession session = request.getSession();
                String attr = "SPRING_SECURITY_SAVED_REQUEST";
                DefaultSavedRequest req = (DefaultSavedRequest) session.getAttribute(attr);
                String redirectUrl = (req == null) ? "/" : req.getRedirectUrl();
                response.sendRedirect(redirectUrl);
            })
        );
        
        return http.build();
    }
}