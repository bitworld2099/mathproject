package cn.math.browser.security;

import cn.math.browser.security.handler.MathAuthenticationFailureHandler;
import cn.math.browser.security.handler.MathAuthenticationSuccessHandler;
import cn.math.security.core.propertes.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
    //定义一个秘密加密对象
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private MathAuthenticationSuccessHandler mathAuthenticationSuccessHandler;
    @Autowired
    private MathAuthenticationFailureHandler mathAuthenticationFailureHandler;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                .successHandler(mathAuthenticationSuccessHandler)
                .failureHandler(mathAuthenticationFailureHandler)
                //.failureHandler(mathAuthenticationFailureHandler)
                //.httpBasic()
            .and()
            .authorizeRequests()
                .antMatchers("/user","/authentication/require",securityProperties.getBrowser().getLoginPage()).permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .csrf().disable();
    }
}
