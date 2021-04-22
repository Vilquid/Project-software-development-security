package ku.message.config;

import ku.message.service.UserDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	private UserDetailsServiceImp userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http
				.authorizeRequests()
				.antMatchers("/home", "/signup", "/css/**", "/js/**").permitAll()
				.anyRequest().authenticated();

		http.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/message", true)
				.permitAll()

		.and()
				.logout()
				.logoutUrl("/logout")
				.clearAuthentication(true)
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID", "remember-me")
				.permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());

	}

	@Bean
	public PasswordEncoder encoder()
	{
		return new BCryptPasswordEncoder(12);
	}

	@Override
	public void configure(WebSecurity web) throws Exception
	{
		web
				.ignoring()
				.antMatchers("/h2-console/**");
	}
}
