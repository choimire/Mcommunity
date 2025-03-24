package net.mirechoi.mcommunity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import net.mirechoi.mcommunity.service.CustomerUserDetailService;

@Configuration
@ComponentScan(basePackages="net.mirechoi.mcommunity.service")
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private CustomerUserDetailService customerUserDetailService;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http
		.csrf()
		.and()
		.authorizeRequests()
		.antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/member/**").hasAnyRole("ADMIN","USER")
		.antMatchers("/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.userDetailsService(customerUserDetailService)
		.formLogin()
			.loginPage("/login")
			.usernameParameter("userid")
			.passwordParameter("userpass")
			.defaultSuccessUrl("/",true)
			.failureUrl("/login?error=true")
			.permitAll()
		.and()
		.logout()
			.logoutUrl("/logout")
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout","GET"))
			.logoutSuccessUrl("/")
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID")
			.permitAll();
		return http.build();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
