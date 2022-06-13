//package com.koala.springsecurity.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
///**
// * day02：
// *      设置springSecurity的用户名、密码（第三种方式：自定义编写实现类）
// *          测试：
// *              0、注释掉其它配置相关内容
// *              1、浏览器访问：http://localhost:8111/test/hello
// *              2、输入用户名（lucy）、密码（123）
// *              3、查看浏览器输出
// */
//
//@Configuration
//public class SecurityConfigCustom extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(password());
//    }
//
//    @Bean
//    PasswordEncoder password() {
//        return new BCryptPasswordEncoder();
//    }
//}
