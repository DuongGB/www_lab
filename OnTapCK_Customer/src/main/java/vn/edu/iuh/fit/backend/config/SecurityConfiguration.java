/*
 * @ {#} SecurityConfiguration.java   1.0     20/12/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/*
 * @description:
 * @author: Tran Hien Vinh
 * @date:   20/12/2024
 * @version:    1.0
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails user = User.withUsername("user") // create a user
                .password(encoder.encode("123")) // encode the password
                .roles("USER") // assign role
                .build();
        UserDetails admin = User.withUsername("admin") // create a user
                .password(encoder.encode("123")) // encode the password
                .roles("ADMIN") // assign role
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    // Hàm này sẽ xử lý sau khi đăng nhập thành công và chuyển hướng người dùng đến trang tương ứng với vai trò của họ
    @Bean
    public AuthenticationSuccessHandler successHandler(){
        return (request, response, authentication) -> {
            String redirectUrl="/home";
            for (var authority: authentication.getAuthorities()){
                String role=authority.getAuthority();
                if (role.equals("ROLE_ADMIN")){
                    redirectUrl="/admin/dashboard";
                    break;
                } else if (role.equals("ROLE_USER")) {
                    redirectUrl="/user/dashboard";
                    break;
                }
            }
            response.sendRedirect(redirectUrl);
        };
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/", "/index", "/error/**", "/home","/register","/saveUser").permitAll()
                                .requestMatchers("/admin*", "/admin/**").hasRole("ADMIN")
                                .requestMatchers("/api/**").permitAll()
                                .requestMatchers("/user*", "/user/**").hasRole("USER")
                                .requestMatchers("/sensitive").denyAll()
                )
                .formLogin(form->form
                        .loginPage("/login")
                        .successHandler(successHandler())
//                                .defaultSuccessUrl("/admin/dashboard")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .clearAuthentication(true)
                        .permitAll()
                )
                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/error/access-denied") // Chỉ định trang tùy chỉnh khi bị lỗi 403
                );
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
