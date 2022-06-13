package com.koala.csrfsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * day09：
 *      CSRF实现
 *
 *         测试：
 *              1、启动项目
 *              2、浏览器访问：http://localhost:8080/toupdate
 *              3、输入用户名（lucy）、密码（123）
 *              4、查看浏览器显示内容
 *              5、解开 //http.csrf().disable(); 的注释
 *              6、注释 csrfTest.html、csrf_token.html 中关于 _csrf 的信息
 *              7、浏览器访问：http://localhost:8080/toupdate
 *              8、输入用户名（lucy）、密码（123）
 *              9、查看登录结果
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    UserDetailsService userDetailsService;

    //实现用户身份认证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //配置url的访问权限
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/**update**").permitAll()
                .antMatchers("/login/**").permitAll()
                .anyRequest().authenticated();

        //day09：关闭csrf保护功能
        //http.csrf().disable();

        //使用自定义的登录窗口
      http.formLogin()
              .loginPage("/userLogin").permitAll()
              .usernameParameter("username").passwordParameter("password")
              .defaultSuccessUrl("/")
              .failureUrl("/userLogin?error");
    }
}




