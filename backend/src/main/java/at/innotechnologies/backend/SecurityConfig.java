package at.innotechnologies.backend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain rootFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic().disable().cors().and().csrf().disable(); // disable spring default security for project (in production the default security would even be extended)

        return http.build();
    }
}
