package ua.org.hasper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                //.passwordEncoder(getShaPasswordEncoder())
                .passwordEncoder(bCryptPasswordEncoder);


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").hasAnyRole("ADMIN", "TEACHER")
                .anyRequest().authenticated()
                .and().exceptionHandling().accessDeniedPage("/unauthorized")
                .and().formLogin().defaultSuccessUrl("/", false)
                .and().formLogin().loginPage("/login").loginProcessingUrl("/j_spring_security_check")
                .failureUrl("/login?error").usernameParameter("j_login").passwordParameter("j_password").permitAll()
                .and().logout().permitAll().logoutUrl("/logout").logoutSuccessUrl("/login?logout").invalidateHttpSession(true);


    }

    @Bean
    public ShaPasswordEncoder getShaPasswordEncoder() {
        return new ShaPasswordEncoder();
    }
}
