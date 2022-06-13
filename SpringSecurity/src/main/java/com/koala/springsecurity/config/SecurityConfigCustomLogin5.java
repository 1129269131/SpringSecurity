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
//import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
//import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
//
//import javax.sql.DataSource;
//
///**
// * day08：
// *      自动登录
// *          测试：
// *              0、注释掉其它配置相关内容
// *              1、浏览器访问（自定义登录页测试）：http://localhost:8111/login1.html
// *              2、勾选自动登录
// *              2、输入用户名（lucy）、密码（123）
// *              3、另起浏览器访问：http://localhost:8111/test/index
// *              4、关闭浏览器
// *              5、重新打开浏览器访问：http://localhost:8111/test/index
// *              6、查看浏览器显示
// *              7、查看表 persistent_logins 中是否存在相关数据
// */
//
//@Configuration
//public class SecurityConfigCustomLogin5 extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    //注入数据源
//    @Autowired
//    private DataSource dataSource;
//
//    //配置对象
//    @Bean
//    public PersistentTokenRepository persistentTokenRepository() {
//        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
//        jdbcTokenRepository.setDataSource(dataSource);
//        //jdbcTokenRepository.setCreateTableOnStartup(true);//day08：启动时自动创建自动登录相关表（persistent_logins），如果该表已存在请注释该行
//        return jdbcTokenRepository;
//    }
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
//        //退出
//        http.logout().logoutUrl("/logout").
//                logoutSuccessUrl("/test/hello").permitAll();
//
//        //配置没有权限访问跳转自定义页面
//        http.exceptionHandling().accessDeniedPage("/unauth.html");
//        http.formLogin()   //自定义自己编写的登录页面
//            .loginPage("/login1.html")  //登录页面设置
//            .loginProcessingUrl("/user/login")   //登录访问路径
//            .defaultSuccessUrl("/success.html").permitAll()  //登录成功之后，跳转路径
//                .failureUrl("/unauth.html")
//            .and().authorizeRequests()
//                .antMatchers("/","/test/hello","/user/login").permitAll() //设置哪些路径可以直接访问，不需要认证
//
//                // 当前登录用户，只有具有admins权限才可以访问这个路径，分别测试如下的三种方法
//                // 第一种方法：hasAuthority方法
//               // .antMatchers("/test/index").hasAuthority("admins")
//                // 第二种方法：hasAnyAuthority方法
//               // .antMatchers("/test/index").hasAnyAuthority("admins,manager")
//                // 第三种方法：hasRole方法   ROLE_sale
//                .antMatchers("/test/index").hasRole("sale")
//
//                .anyRequest().authenticated()
//                .and().rememberMe().tokenRepository(persistentTokenRepository())
//                .tokenValiditySeconds(60)//设置有效时长，单位秒
//                .userDetailsService(userDetailsService)
//           .and().csrf().disable();  //这里暂时先关闭csrf防护
//    }
//}
