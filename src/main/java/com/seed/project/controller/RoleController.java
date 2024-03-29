package com.seed.project.controller;

import com.seed.project.aop.annotation.LogAnnotation;
import com.seed.project.entity.SysRole;
import com.seed.project.service.RoleService;
import com.seed.project.utils.DataResult;
import com.seed.project.vo.req.AddRoleReqVO;
import com.seed.project.vo.req.RolePageReqVO;
import com.seed.project.vo.req.RoleUpdateReqVO;
import com.seed.project.vo.resp.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @ClassName: RoleController
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@RestController
@RequestMapping("/api")
@Api(tags = "组织管理-角色管理",description = "角色管理相关接口")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/roles")
    @ApiOperation(value = "分页获取角色数据接口")
    @LogAnnotation(title = "组织管理-角色管理",action = "分页获取角色数据接口")
    @RequiresPermissions("sys:role:list")
    public DataResult<PageVO<SysRole>> pageInfo(@RequestBody RolePageReqVO vo){
        DataResult result =DataResult.success();
        result.setData(roleService.pageInfo(vo));
        return result;
    }

    @PostMapping("/role")
    @ApiOperation(value = "新增角色接口")
    @LogAnnotation(title = "组织管理-角色管理",action = "新增角色接口")
    @RequiresPermissions("sys:role:add")
    public DataResult<SysRole> addRole(@RequestBody @Valid AddRoleReqVO vo){
        DataResult result =DataResult.success();
        result.setData(roleService.addRole(vo));
        return result;
    }

    @GetMapping("/role/{id}")
    @ApiOperation(value = "获取角色详情接口")
    @LogAnnotation(title = "组织管理-角色管理",action = "获取角色详情接口")
    @RequiresPermissions("sys:role:detail")
    public DataResult<SysRole> detailInfo(@PathVariable("id") String id){
        DataResult result=DataResult.success();
        result.setData(roleService.detailInfo(id));
        return result;
    }

    @PutMapping("/role")
    @ApiOperation(value = "更新角色信息接口")
    @LogAnnotation(title = "组织管理-角色管理",action = "更新角色信息接口")
    @RequiresPermissions("sys:role:update")
    public DataResult updateRole(@RequestBody @Valid RoleUpdateReqVO vo){
        DataResult result=DataResult.success();
        roleService.updateRole(vo);
        return result;
    }

    @DeleteMapping("/role/{id}")
    @ApiOperation(value = "删除角色接口")
    @LogAnnotation(title = "组织管理-角色管理",action = "删除角色接口")
    @RequiresPermissions("sys:role:delete")
    public DataResult deletedRole(@PathVariable("id") String id){
        roleService.deletedRole(id);
        DataResult result=DataResult.success();
        return result;
    }
}
