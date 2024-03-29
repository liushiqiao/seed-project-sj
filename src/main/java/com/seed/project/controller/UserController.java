package com.seed.project.controller;

import com.seed.project.vo.req.LoginReqVO;
import com.seed.project.vo.req.UserAddReqVO;
import com.seed.project.vo.req.UserOwnRoleReqVO;
import com.seed.project.vo.req.UserPageReqVO;
import com.seed.project.vo.req.UserUpdateDetailInfoReqVO;
import com.seed.project.vo.req.UserUpdatePwdReqVO;
import com.seed.project.vo.req.UserUpdateReqVO;
import com.seed.project.vo.resp.LoginRespVO;
import com.seed.project.vo.resp.PageVO;
import com.seed.project.aop.annotation.LogAnnotation;
import com.seed.project.contants.Constant;
import com.seed.project.entity.SysUser;
import com.seed.project.service.UserService;
import com.seed.project.utils.DataResult;
import com.seed.project.utils.JwtTokenUtil;
import com.seed.project.vo.resp.UserOwnRoleRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName: UserController
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@RestController
@RequestMapping("/api")
@Api(tags = "组织管理-用户管理",description = "用户模块相关接口")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/login")
    @ApiOperation(value = "用户登录接口")
    public DataResult<LoginRespVO> login(@RequestBody @Valid LoginReqVO vo){
        DataResult result=DataResult.success();
        result.setData(userService.login(vo));
        return result;
    }



    @PostMapping("/users")
    @ApiOperation(value = "分页查询用户接口")
    @RequiresPermissions("sys:user:list")
    @LogAnnotation(title = "组织管理-用户管理",action = "分页查询用户接口")
    public DataResult<PageVO<SysUser>> pageInfo(@RequestBody UserPageReqVO vo){
        DataResult result=DataResult.success();
        result.setData(userService.pageInfo(vo));
        return result;
    }

    @PostMapping("/user")
    @ApiOperation(value = "新增用户接口")
    @LogAnnotation(title = "组织管理-用户管理",action = "新增用户接口")
    @RequiresPermissions("sys:user:add")
    public DataResult addUser(@RequestBody @Valid UserAddReqVO vo){
        DataResult result=DataResult.success();
        userService.addUser(vo);
        return result;
    }

    @GetMapping("/user/roles/{userId}")
    @ApiOperation(value = "查询用户拥有的角色数据接口")
    @LogAnnotation(title = "组织管理-用户管理",action = "查询用户拥有的角色数据接口")
    @RequiresPermissions("sys:user:role:update")
    public DataResult<UserOwnRoleRespVO> getUserOwnRole(@PathVariable("userId") String userId){
        DataResult result=DataResult.success();
        result.setData(userService.getUserOwnRole(userId));
        return result;
    }

    @PutMapping("/user/roles")
    @ApiOperation(value = "保存用户拥有的角色信息接口")
    @LogAnnotation(title = "组织管理-用户管理",action = "保存用户拥有的角色信息接口")
    @RequiresPermissions("sys:user:role:update")
    public DataResult saveUserOwnRole(@RequestBody @Valid UserOwnRoleReqVO vo){
        DataResult result=DataResult.success();
        userService.setUserOwnRole(vo);
        return result;
    }


    @GetMapping("/user/token")
    @ApiOperation(value = "jwt token 刷新接口")
    @LogAnnotation(title = "组织管理-用户管理",action = "jwt token 刷新接口")
    public DataResult<String> refreshToken(HttpServletRequest request){
        String refreshToken=request.getHeader(Constant.REFRESH_TOKEN);
        String newAccessToken = userService.refreshToken(refreshToken);
        DataResult result=DataResult.success();
        result.setData(newAccessToken);
        return result;
    }

    @PutMapping("/user")
    @ApiOperation(value ="列表修改用户信息接口")
    @LogAnnotation(title = "组织管理-用户管理",action = "列表修改用户信息接口")
    @RequiresPermissions("sys:user:update")
    public DataResult updateUserInfo(@RequestBody @Valid UserUpdateReqVO vo, HttpServletRequest request){
        String accessToken=request.getHeader(Constant.ACCESS_TOKEN);
        String userId= JwtTokenUtil.getUserId(accessToken);
        DataResult result=DataResult.success();
        userService.updateUserInfo(vo,userId);
        return result;
    }

    @DeleteMapping("/user")
    @ApiOperation(value = "批量/删除用户接口")
    @LogAnnotation(title = "组织管理-用户管理",action = "批量/删除用户接口")
    @RequiresPermissions("sys:user:delete")
    public DataResult deletedUsers(@RequestBody @ApiParam(value = "用户id集合") List<String> list, HttpServletRequest request){
        String accessToken=request.getHeader(Constant.ACCESS_TOKEN);
        String operationId=JwtTokenUtil.getUserId(accessToken);
        userService.deletedUsers(list,operationId);
        DataResult result=DataResult.success();
        return result;
    }

    @GetMapping("/user/info")
    @ApiOperation(value = "用户信息详情接口")
    @LogAnnotation(title = "组织管理-用户管理",action = "用户信息详情接口")
    public DataResult<SysUser> detailInfo(HttpServletRequest request){
        String accessToken=request.getHeader(Constant.ACCESS_TOKEN);
        String id=JwtTokenUtil.getUserId(accessToken);
        DataResult result=DataResult.success();
        result.setData(userService.detailInfo(id));
        return result;
    }

    @PutMapping("/user/info")
    @ApiOperation(value = "保存个人信息接口")
    @LogAnnotation(title = "组织管理-用户管理",action = "保存个人信息接口")
    public DataResult saveUserInfo(@RequestBody UserUpdateDetailInfoReqVO vo, HttpServletRequest request){
        String accessToken=request.getHeader(Constant.ACCESS_TOKEN);
        String id=JwtTokenUtil.getUserId(accessToken);
        userService.userUpdateDetailInfo(vo,id);
        DataResult result=DataResult.success();
        return result;
    }

    @PutMapping("/user/pwd")
    @ApiOperation(value = "修改个人密码接口")
    public DataResult updatePwd(@RequestBody @Valid UserUpdatePwdReqVO vo, HttpServletRequest request){
        String accessToken=request.getHeader(Constant.ACCESS_TOKEN);
        String refresgToken=request.getHeader(Constant.REFRESH_TOKEN);
        userService.userUpdatePwd(vo,accessToken,refresgToken);
        DataResult result=DataResult.success();
        return result;
    }

    @GetMapping("/user/logout")
    @ApiOperation(value = "用户推出登录")
    public DataResult logout(HttpServletRequest request){
        try {
            String accessToken=request.getHeader(Constant.ACCESS_TOKEN);
            String refreshToken=request.getHeader(Constant.REFRESH_TOKEN);
            userService.logout(accessToken,refreshToken);
        } catch (Exception e) {
            log.error("logout:{}",e);
        }
        return DataResult.success();
    }

}
