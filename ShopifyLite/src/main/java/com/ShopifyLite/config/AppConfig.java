package com.ShopifyLite.config;




import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;
@Configuration
public class AppConfig {
@Bean
public SecurityFilterChain springSecurityConfiguration(HttpSecurity http) throws Exception {
	http.cors(cors -> {
		cors.configurationSource(new CorsConfigurationSource() {
		@Override
		public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
		CorsConfiguration cfg = new CorsConfiguration();
		cfg.setAllowedOriginPatterns(Collections.singletonList("*"));
		cfg.setAllowedMethods(Collections.singletonList("*"));
		cfg.setAllowCredentials(true);
		cfg.setAllowedHeaders(Collections.singletonList("*"));
		cfg.setExposedHeaders(Arrays.asList("Authorization"));
		return cfg;
		}
		});
		}).authorizeHttpRequests(auth ->{
			auth
			.requestMatchers(HttpMethod.POST, "/**")
			.permitAll()
			.requestMatchers(HttpMethod.PUT, "/**")
			.permitAll()
			.requestMatchers(HttpMethod.DELETE, "/**")
			.permitAll()
			.requestMatchers(HttpMethod.GET, "/**")
			.permitAll()
			.requestMatchers(HttpMethod.PATCH, "/**")
			.permitAll()
//			.requestMatchers(HttpMethod.DELETE,"/user/**").hasRole("ADMIN")
			
//			.requestMatchers("/swagger-ui*/**","/v3/api-docs/**").permitAll()
//			.requestMatchers(HttpMethod.POST,"/register").permitAll()
//			.requestMatchers(HttpMethod.PUT,"/user/**").hasAnyRole("ADMIN","User")
//			.requestMatchers(HttpMethod.GET,"/user/**").hasAnyRole("ADMIN","USER")
//			.requestMatchers(HttpMethod.DELETE,"/user/**").hasAnyRole("ADMIN","USER")
//			.requestMatchers(HttpMethod.POST,"/size").hasRole("ADMIN")
//			.requestMatchers(HttpMethod.PUT,"/size").hasRole("ADMIN")
//			.requestMatchers(HttpMethod.GET,"/size").hasRole("ADMIN")
//			.requestMatchers(HttpMethod.DELETE,"/size/**").hasRole("ADMIN")
//			.requestMatchers(HttpMethod.POST,"/product").hasRole("ADMIN")
//			.requestMatchers(HttpMethod.PUT,"/product").hasRole("ADMIN")
//			.requestMatchers(HttpMethod.GET,"/admin/**").permitAll()
//			.requestMatchers(HttpMethod.DELETE,"/product/**").hasRole("ADMIN")
//			.requestMatchers(HttpMethod.POST,"/order/**").hasAnyRole("ADMIN","USER")
//			.requestMatchers(HttpMethod.GET,"/order/**").hasAnyRole("ADMIN","USER")
//			.requestMatchers(HttpMethod.GET,"/cart/**").hasAnyRole("ADMIN","USER")
//			.requestMatchers(HttpMethod.DELETE,"/cart/**").hasAnyRole("ADMIN","USER")
//			.requestMatchers(HttpMethod.POST,"/cart/**").hasAnyRole("ADMIN","USER")
			
			
//			.requestMatchers(HttpMethod.GET,"/students/**").hasAnyRole("ADMIN","USER","STUDENT")
			.anyRequest().authenticated();
		})
		.csrf(csrf -> csrf.disable())
		.addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
		.addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
		.formLogin(Customizer.withDefaults())
		.httpBasic(Customizer.withDefaults());
		return http.build();
}
@Bean
public PasswordEncoder passwordEncoder() {
	
		return new BCryptPasswordEncoder();
	}


}
