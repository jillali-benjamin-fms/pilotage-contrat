package com.fms.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.server.authentication.logout.DelegatingServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.SecurityContextServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.WebSessionServerLogoutHandler;

import com.fms.Controller.CommentController;

import jakarta.servlet.http.HttpServletResponse;

import static com.fms.enums.entity_enums.Permission.*;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {
    
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authtenificationProvider;
   // private final LogoutHandler logoutHandler;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        DelegatingServerLogoutHandler logoutHandler = new DelegatingServerLogoutHandler(
//                new WebSessionServerLogoutHandler(), new SecurityContextServerLogoutHandler()
//        );
//        http.authorizeHttpRequests((authz)-> authz.anyRequest().permitAll());
//        http.csrf().disable();
        //http.cors(Customizer.withDefaults())
       // http.csrf().disable().authorizeHttpRequests().requestMatchers()
        
        http.csrf(AbstractHttpConfigurer::disable)
        .cors(Customizer.withDefaults())
        .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests((authz)-> authz.requestMatchers("/api/v1/auth/authenticate").permitAll()
                .requestMatchers("/api/v1/auth/register").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/v1/**").hasAnyAuthority("USER", "ADMIN", "MANAGER")
                .requestMatchers("/api/v1/contract/**", "/api/v1/comment/**", "/api/v1/client/**", "/api/v1/client/**").hasAnyAuthority("ADMIN", "MANAGER")
                .requestMatchers("/api/v1/user/**").hasAnyAuthority("ADMIN")
                .anyRequest().authenticated()
                //.anyRequest().permitAll()
                //.requestMatchers(HttpMethod.OPTIONS, "/*").permitAll().anyRequest().authenticated()
                //.requestMatchers("/api/v1/**").authenticated()
                 //.permitAll()
                )
        //.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authenticationProvider(authtenificationProvider)
        .addFilterBefore(jwtAuthFilter,  UsernamePasswordAuthenticationFilter.class);
        http.logout((logout) -> logout.permitAll()
                .logoutSuccessHandler((request, response, authentication) -> {
                    logout.logoutUrl("/api/v1/auth/logout");
                    SecurityContextHolder.clearContext();
                    response.setStatus(HttpServletResponse.SC_OK);
                }));
//        .logout(logout -> logout
//                .logoutUrl("api/v1/auth/logout").permitAll()
//                //.addLogoutHandler(logoutHandler)
//                //.logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
//        );
        //http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
      //  .authorizeHttpRequests(request -> request
//                .requestMatchers("/api/auth").permitAll()
//                .requestMatchers(HttpMethod.OPTIONS, "/*").permitAll()
               // .requestMatchers(HttpMethod.OPTIONS).permitAll()
                //.requestMatchers(SYS_ADMIN_PATTERNS).hasAuthority("SYSTEM_ADMIN")
                //.requestMatchers("/api/v1/**").authenticated()
 //       )
        return http.build();
        
//        http
//        .authorizeHttpRequests((authorize) -> authorize
//        .requestMatchers(
//                "/**",
//                "/v2/api-docs",
//                "/v3/api-docs",
//                "/v3/api-docs/**",
//                "/swagger-resources",
//                "/swagger-resources/**",
//                "/configuration/ui",
//                "/configuration/security",
//                "/swagger-ui/**",
//                "/webjars/**",
//                "/swagger-ui.html"
//        )
//          .permitAll()
          

//        .requestMatchers("/api/v1/management/**").hasAnyRole(ADMIN.name(), MANAGER.name())
//
//
//        .requestMatchers(GET, "/api/v1/management/**").hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name())
//        .requestMatchers(POST, "/api/v1/management/**").hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name())
//        .requestMatchers(PUT, "/api/v1/management/**").hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name())
//        .requestMatchers(DELETE, "/api/v1/management/**").hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name())


       /* .requestMatchers("/api/v1/admin/**").hasRole(ADMIN.name())

        .requestMatchers(GET, "/api/v1/admin/**").hasAuthority(ADMIN_READ.name())
        .requestMatchers(POST, "/api/v1/admin/**").hasAuthority(ADMIN_CREATE.name())
        .requestMatchers(PUT, "/api/v1/admin/**").hasAuthority(ADMIN_UPDATE.name())
        .requestMatchers(DELETE, "/api/v1/admin/**").hasAuthority(ADMIN_DELETE.name())*/


//        .anyRequest()
//          .authenticated()
//          
//          );
//        http.csrf().disable();
//        http.sessionManagement()
//        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.authenticationProvider(authtenificationProvider)
//        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//       // .logout()
//       // .logoutUrl("/api/v1/auth/logout")
//       // .addLogoutHandler(logoutHandler)
//       // .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
//    ;

//    return http.getOrBuild();
    }

}
