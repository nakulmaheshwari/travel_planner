//package com.v1.planner.config;
//
//import com.v1.planner.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.converter.AbstractHttpMessageConverter;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import static com.v1.planner.enums.Role.ADMIN;
//import static com.v1.planner.enums.Role.MEMBER;
//import static org.springframework.http.HttpMethod.GET;
//import static org.springframework.http.HttpMethod.POST;
//import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//@EnableMethodSecurity
//public class SecurityConfiguration {
//    private final AuthenticationProvider authenticationProvider;
//    private final JwtAuthFilter jwtAuthFilter;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        return http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests( req->
//                        req.requestMatchers("/v1/auth/*")
//                                .permitAll()
//                                .requestMatchers("/v1/management/**").hasAnyRole(ADMIN.name(), USER.name())
//                                //.requestMatchers(GET, "/v1/management/**").hasAnyAuthority(ADMIN_READ.name(), MEMBER_READ.name())
//                               // .requestMatchers(POST, "/v1/management/**").hasAnyAuthority(ADMIN_CREATE.name(), MEMBER_CREATE.name())
//                                .anyRequest()
//                                .authenticated())
//                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
//                .authenticationProvider(authenticationProvider)
//                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
//                .build();
//    }
//
//    }
//
//
