package ru.kichenko.example;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

/**
 * Конфигурация method-security
 */
//@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true, securedEnabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    /* 
     *  Можем заменить сервис для авторизации 
     */
    @Override
    protected AccessDecisionManager accessDecisionManager() {
        return super.accessDecisionManager();
    }

    /* 
     * 
     */
    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return super.createExpressionHandler();
    }
}
