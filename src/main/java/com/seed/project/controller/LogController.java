package com.seed.project.controller;

import com.seed.project.aop.annotation.LogAnnotation;
import com.seed.project.entity.SysLog;
import com.seed.project.service.LogService;
import com.seed.project.utils.DataResult;
import com.seed.project.vo.req.SysLogPageReqVO;
import com.seed.project.vo.resp.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: LogController
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@RestController
@RequestMapping("/api")
@Api(tags = "系统管理-日志管理",description = "日志管理相关接口")
public class LogController {
    @Autowired
    private LogService logService;

    @PostMapping("/logs")
    @ApiOperation(value = "分页查找操作日志接口")
    @RequiresPermissions("sys:log:list")
    public DataResult<PageVO<SysLog>> pageInfo(@RequestBody SysLogPageReqVO vo){
        PageVO<SysLog> sysLogPageVO = logService.pageInfo(vo);
        DataResult result=DataResult.success();
        result.setData(sysLogPageVO);
        return result;
    }
    @DeleteMapping("/log")
    @ApiOperation(value = "删除日志接口")
    @LogAnnotation(title = "系统管理-日志管理",action = "删除日志接口")
    @RequiresPermissions("sys:log:delete")
    public DataResult deletedLog(@RequestBody @ApiParam(value = "日志id集合") List<String> logIds){
        logService.deletedLog(logIds);
        DataResult result=DataResult.success();
        return result;
    }
}
