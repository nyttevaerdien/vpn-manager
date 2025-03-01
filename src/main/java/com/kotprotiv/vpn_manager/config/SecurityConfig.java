package com.kotprotiv.vpn_manager.config;

import com.kotprotiv.vpn_manager.model.ServiceUser;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  @ConfigurationProperties("security.user")
  public ServiceUser serviceUser() {
    return new ServiceUser();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
        .httpBasic(httpSecurityHttpBasicConfigurer -> {})
        .csrf(AbstractHttpConfigurer::disable); // Disable CSRF protection

    return http.build();
  }

  @Bean
  public UserDetailsService userDetailsService(ServiceUser serviceUser) {
    UserDetails user =
        User.withDefaultPasswordEncoder()
            .username(serviceUser.getUsername())
            .password(serviceUser.getPassword())
            .roles("USER")
            .build();

    return new InMemoryUserDetailsManager(user);
  }
}
