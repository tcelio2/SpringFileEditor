package com.att.ub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

import com.att.ub.auth.htpasswd.HtPasswdUserDetailsService;
import com.att.ub.auth.htpasswd.HtpasswdPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityWebAppConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public SessionRegistry getSessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public SessionAuthenticationStrategy getSessionAuthStrategy(SessionRegistry sessionRegistry) {
        ConcurrentSessionControlAuthenticationStrategy controlAuthenticationStrategy =  new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry);
        return controlAuthenticationStrategy;
    }

    
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(new HtPasswdUserDetailsService()).passwordEncoder(new HtpasswdPasswordEncoder());
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {

	http
	    .authorizeRequests()
	    	.antMatchers("*","index.html").authenticated()
	    	.anyRequest().authenticated() 
	    .and()
	    	.formLogin()  
	        .loginPage("/login") 
            	.failureUrl("/login?error")
            	.loginProcessingUrl("/loginAuth")
            	.defaultSuccessUrl("/selection", true)
            	//.successHandler(new SuccessAuthenticationHandler())
	        .permitAll()
	    .and()
                .logout()
                .logoutUrl("/logout")
                .permitAll(); 
	 
	http.sessionManagement().maximumSessions(1).sessionRegistry(getSessionRegistry());
	
	http.csrf().disable();
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
    	 .antMatchers("/includes/**")
    	 .antMatchers("/WEB-INF/**")
    	 .antMatchers("/WEB-INF/views/**")
    	 .antMatchers("/login" )
    	 ;
    } 
    
}
