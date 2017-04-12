package ru.kichenko.example;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
*  Пример конфигурации web-security.
*
*  @EnableWebSecurity импортирует WebSecurityConfiguration, 
*  в которой через метод springSecurityFilterChain() 
*  создается бин-фильтр FilterChainProxy
*  с дефолтным именем 'springSecurityFilterChain'.
*  
*/
//@Configuration
//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private DataSource dataSource;

    /* 
     * Кастомизация WebSecurity (там где создается фильтр FilterChainProxy aka 'springSecurityFilterChain')
     * В повседневной жизни не требуется, но все же...
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        //Например Вам надо, чтобы SpringSecurity полностью игнорил URL'ы начинающиеся с '/resources/'
        web.ignoring().antMatchers("/resources/**");
    }

    /* 
       Конфигурация менеджера аутентификации
       Здесь он настроен через JDBC authentication (т.е. юзера для аутентификации будем искать в БД)
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .withDefaultSchema()
            .withUser("user").password("password").roles("USER")
            .and()
            .withUser("admin").password("password").roles("USER", "ADMIN");
    }
    
    /* 
     * Метод для конфигурирования web-security
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        /* 
         * Конфигурируем авторизацию и аутентифакацтю url
         */
        http
            .authorizeRequests()
                .antMatchers("/resources/**", "/signup", "/about").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')").anyRequest()
                .authenticated();

        /* 
         * Аутентификация через форму логина
         */
        http
        .formLogin()
            .loginPage("/login")
            .permitAll();
            
        /* 
         * Настройки лог-аута
         */
        http
        .logout()                                                                
            .logoutUrl("/my/logout")                                                 
            .logoutSuccessUrl("/my/index")                                                                       
            .invalidateHttpSession(true);  
    }
}
