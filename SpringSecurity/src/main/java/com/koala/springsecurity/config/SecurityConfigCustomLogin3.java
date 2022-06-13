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
// * day05：
// *      自定义403页面
// *          测试：
// *              0、注释掉其它配置相关内容
// *              1、浏览器访问（自定义登录页测试）：http://localhost:8111/test/index
// *              2、输入用户名（lucy）、密码（123）
// *              3、查看浏览器输出
// */
//
//@Configuration
//public class SecurityConfigCustomLogin3 extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(password());
//    }
//    @Bean
//    PasswordEncoder password() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        //day05：配置没有权限访问跳转自定义页面
//        http.exceptionHandling().accessDeniedPage("/unauth.html");
//
//        http.formLogin()   //自定义自己编写的登录页面
//                .loginPage("/login.html")  //登录页面设置
//                .loginProcessingUrl("/user/login")   //登录访问路径
//                .defaultSuccessUrl("/test/index").permitAll()  //登录成功之后，跳转路径
//                .and().authorizeRequests()
//                .antMatchers("/","/test/hello","/user/login").permitAll() //设置哪些路径可以直接访问，不需要认证
//
//                // 当前登录用户，只有具有admins权限才可以访问这个路径，分别测试如下的三种方法
//                // 第一种方法：hasAuthority方法
//                // .antMatchers("/test/index").hasAuthority("admins")
//                // 第二种方法：hasAnyAuthority方法
//                // .antMatchers("/test/index").hasAnyAuthority("admins,manager")
//                // 第三种方法：hasRole方法   ROLE_sale
//                .antMatchers("/test/index").hasRole("sales")
//
//                .anyRequest().authenticated()
//                .and().csrf().disable();  //这里暂时先关闭csrf防护
//    }
//}
