package com.example.demo.Config;
import com.example.demo.Service.AuthenticationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity // Saying server to ignore built in filter chains and access our own security chain
public class SecurityConfig {
    private final AuthenticationUserService userDetailsService;

    @Autowired
    private JWTFilter jwtFilter;

    public SecurityConfig(AuthenticationUserService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean // returning a Bean with Customized SecurityFilterChain
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//      Disabling the csrf token
        http.csrf(customizer -> customizer.disable());
        http.cors(Customizer.withDefaults());
//      Asking for Authorization on all the request, if any exceptions include it
        http.authorizeHttpRequests(request -> request
                .requestMatchers("/register", "/login").permitAll() // No auth needed
//                .requestMatchers("/customer").hasRole("CUSTOMER")
//                .requestMatchers("/seller").hasRole("SELLER")
//                .requestMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated() // Everything else needs authentication
        );

//      form Login inbuilt in Spring Security
//      http.formLogin(Customizer.withDefaults());

//      Allowing login through postman or API
        http.httpBasic(Customizer.withDefaults());

//      We are going with stateless for session, and there are other options also, just explore it
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        // Password deEncryptor
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:5173")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}

