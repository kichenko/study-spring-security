package ru.kichenko.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.intercept.RunAsImplAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private DataSource dataSource;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public PersistentTokenRepository jdbcTokenRepositoryImpl() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setCreateTableOnStartup(true);
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }
        
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        RunAsImplAuthenticationProvider runAsAuthProvider = new RunAsImplAuthenticationProvider();
        runAsAuthProvider.setKey("MyRunAsKey");
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
    
    /* 
     * Кастомизация WebSecurity (там где создается фильтр FilterChainProxy aka 'springSecurityFilterChain')
     * В повседневной жизни не требуется, но все же...
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        //Например Вам надо, чтобы SpringSecurity полностью игнорил URL'ы начинающиеся с '/ignore/'
        web.ignoring().antMatchers("/ignore/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/resources/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();
        
        http
            .rememberMe()
                .tokenValiditySeconds(86400)
                //.rememberMeParameter("remember-me")
                .tokenRepository(jdbcTokenRepositoryImpl());
        /*
        http
            .sessionManagement()
            .
            .maximumSessions(1)
            .expiredUrl("/login?expired")
            .maxSessionsPreventsLogin(true);*/
    }   
}