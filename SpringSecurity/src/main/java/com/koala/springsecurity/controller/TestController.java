package com.koala.springsecurity.controller;

import com.koala.springsecurity.entity.Users;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    /**
     * day01：
     *      入门案例
     *
     *          测试：
     *              0、注释掉 day02 相关内容
     *              1、运行项目
     *              2、浏览器打开：http://localhost:8111/test/hello
     *              3、进入SpringSecurity的登录页面
     *              4、输入用户名（默认：user）、密码（控制台中拿）
     *              5、查看浏览器输出
     */
    @GetMapping("hello")
    public String hello() {
        return "hello security";
    }

    @GetMapping("index")
    public String index() {
        return "hello index";
    }

    /**
     * day06：
     *      注解使用
     *
     *          测试：
     *              0、注释掉其它配置相关内容、解开 SecurityConfigCustom.java、MyUserDetailsService.java 的注释
     *              1、运行项目
     *              2、浏览器打开：http://localhost:8111/test/update
     *              3、输入用户名（lucy）、密码（123）
     *              5、查看浏览器输出
     */
    @GetMapping("update")
    //@Secured({"ROLE_sale","ROLE_manager"})
    //@PreAuthorize("hasAnyAuthority('admin')")
    @PostAuthorize("hasAnyAuthority('admin')")
    public String update() {
        System.out.println("update......");
        return "hello update";
    }

    /**
     * day06：
     *      注解使用
     *
     *          测试：
     *              0、注释掉其它配置相关内容、解开 SecurityConfigCustom.java、MyUserDetailsService.java 的注释
     *              1、运行项目
     *              2、浏览器打开：http://localhost:8111/test/getAll
     *              3、输入用户名（lucy）、密码（123）
     *              5、查看浏览器输出
     */
    @GetMapping("getAll")
    @PostFilter("filterObject.username == 'admin1'")
    public List<Users> getAllUser(){
        ArrayList<Users> list = new ArrayList<>();
        list.add(new Users(11,"admin1","6666"));
        list.add(new Users(21,"admin2","888"));
        System.out.println(list);
        return list;
    }


}
