package com.seed.project.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: IndexController
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@Controller
@RequestMapping("/index")
@Api(tags = "试图",description = "跳转试图的控制器")
public class IndexController {

    @GetMapping("/404")
    @ApiOperation(value = "跳转404错误页面")
    public String error404(){
        return "error/404";
    }

    @GetMapping("/login")
    @ApiOperation(value = "跳转登录页面")
    public String login(){
        return "login";
    }

    @GetMapping("/home")
    @ApiOperation(value = "跳转首页页面")
    public String home(){
        return "home";
    }
    @GetMapping("/main")
    @ApiOperation(value = "跳转主页页面")
    public String main(){
        return "main";
    }

    @GetMapping("/menus")
    @ApiOperation(value = "跳转菜单权限管理页面")
    public String menus(){
        return "menus/menu";
    }

    @GetMapping("/roles")
    @ApiOperation(value = "跳转角色管理页面")
    public String roles(){
        return "roles/role";
    }

    @GetMapping("/depts")
    @ApiOperation(value = "跳转部门管理页面")
    public String depts(){
        return "depts/dept";
    }

    @GetMapping("/users")
    @ApiOperation(value = "跳转用户管理页面")
    public String users(){
        return "users/user";
    }

    @GetMapping("/logs")
    @ApiOperation(value = "跳转日志管理页面")
    public String logs(){
        return "logs/log";
    }

    @GetMapping("/users/info")
    @ApiOperation(value = "跳转个人信息编辑页面")
    public String usersInfo(){
        return "users/user_edit";
    }

    @GetMapping("/users/pwd")
    @ApiOperation(value = "跳转用户编辑密码页面")
    public String updatePwd(){
        return "users/user_pwd";
    }

    @GetMapping("/rotations")
    @ApiOperation(value = "轮播图管理页面入口")
    public String rotations(){
        return "rotations/rotation";
    }

    @GetMapping("/rotation/show")
    @ApiOperation(value = "轮播图前端页面展示入口")
    public String rotationShow(){
        return "rotations/rotation_show";
    }

    @GetMapping("/files")
    @ApiOperation(value ="我的文件页面入口")
    public String myFile(){
        return "files/my_file";
    }
}
