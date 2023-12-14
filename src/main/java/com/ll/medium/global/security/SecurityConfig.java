package com.ll.medium.global.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
<<<<<<< HEAD
=======
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
>>>>>>> 0b68a47 (일단 글 프론트엔드 작업중)
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
<<<<<<< HEAD
import org.springframework.security.web.util.matcher.AnyRequestMatcher;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;

import static org.springframework.security.config.http.MatcherType.mvc;

=======
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;

>>>>>>> 0b68a47 (일단 글 프론트엔드 작업중)

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .headers(headers ->
                        headers.frameOptions(FrameOptionsConfig::disable)
                )
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
<<<<<<< HEAD
                                .requestMatchers(PathRequest.toH2Console()).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/member/signup")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/member/login")).permitAll()

                                .anyRequest().authenticated()
=======
                                .requestMatchers(new AntPathRequestMatcher("/")).permitAll()
                                .requestMatchers(PathRequest.toH2Console()).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/member/signup")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/member/login")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/api/**")).permitAll()

>>>>>>> 0b68a47 (일단 글 프론트엔드 작업중)
                )
                .formLogin((formLogin) -> formLogin
                        .loginPage("/member/login")
                        .defaultSuccessUrl("/"))
                .logout((logout) -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true))
        ;
        return http.build();
    }
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }
    @Bean
    public SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }
    
}
