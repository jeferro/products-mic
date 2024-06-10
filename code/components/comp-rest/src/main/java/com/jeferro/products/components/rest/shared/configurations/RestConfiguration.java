package com.jeferro.products.components.rest.shared.configurations;

import com.jeferro.products.components.rest.shared.filters.AuthRestFilter;
import com.jeferro.products.components.rest.shared.filters.TenantRestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class RestConfiguration implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
	registry.addMapping("/**");
  }

  @Bean
  UserDetailsService userDetailsService() {
	return new InMemoryUserDetailsManager();
  }

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http,
	  AuthRestFilter authRestFilter,
	  TenantRestFilter tenantRestFilter) throws Exception {
	return http.csrf(AbstractHttpConfigurer::disable)
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll())
		.addFilterAfter(authRestFilter, AnonymousAuthenticationFilter.class)
		.addFilterAfter(tenantRestFilter, AnonymousAuthenticationFilter.class)
		.build();
  }
}
