//package com.koala.springsecurity.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
///**
// * day03：
// *      自定义用户登录页面
// *          测试：
// *              0、注释掉其它配置相关内容
// *              1、浏览器访问（免登录测试）：http://localhost:8111/test/hello
// *              2、浏览器访问（自定义登录页测试）：http://localhost:8111/test/index
// *              3、输入用户名（lucy）、密码（123）
// *              4、查看浏览器输出
// */
//
//@Configuration
//public class SecurityConfigCustomLogin extends WebSecurityConfigurerAdapter {
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
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin()   //自定义自己编写的登录页面
//            .loginPage("/login.html")  //登录页面设置
//            .loginProcessingUrl("/user/login")   //登录访问路径
//            .defaultSuccessUrl("/test/index").permitAll()  //登录成功之后，跳转路径
//            .and().authorizeRequests()
//                    .antMatchers("/","/test/hello","/user/login").permitAll() //设置哪些路径可以直接访问，不需要认证
//                    .anyRequest().authenticated()
//                .and().csrf().disable();  //这里暂时先关闭csrf防护
//    }
//}
