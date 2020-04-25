package com.mindtree.mongosecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.mindtree.mongosecurity.service.MongoUserDetailsService;

@Configuration
@EnableConfigurationProperties
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	MongoUserDetailsService userDetailsService;
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Bean
	public AuthenticationProvider authProvider()
	{
		System.out.println("entered AuthenticationProvider authProvider");
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		System.out.println("bcrypted ");
		return provider;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests().antMatchers("/login").permitAll().anyRequest().authenticated().and()
				.formLogin().loginPage("/login").permitAll().and().logout().invalidateHttpSession(true)
				.clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/logout-sucess").permitAll();
		System.out.println("inside configure");
	}

//	 @Bean
//	  public PasswordEncoder passwordEncoder() {
//		 System.out.println("bcrypted");
//	    return new BCryptPasswordEncoder();
//	  }
//	 
//	 @Override
//	  public void configure(AuthenticationManagerBuilder builder) throws Exception {
//	    builder.userDetailsService(userDetailsService);
//	  }
	
//	@Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
//    }

	
	
}
