package com.seed.project.controller;

import com.seed.project.entity.SysRotationChart;
import com.seed.project.service.RotationChartService;
import com.seed.project.utils.DataResult;
import com.seed.project.utils.JwtTokenUtil;
import com.seed.project.vo.req.RotationChartDeleteReqVO;
import com.seed.project.vo.req.RotationChartReqAddVO;
import com.seed.project.vo.req.RotationChartUpdateReqVO;
import com.seed.project.vo.req.RotationReqVO;
import com.seed.project.vo.resp.PageVO;
import com.seed.project.contants.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName: RotationChartController
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@RestController
@RequestMapping("/api")
@Api(tags = "轮播图功能相关接口")
public class RotationChartController {
    @Autowired
    private RotationChartService rotationChartService;
    @PostMapping("/rotations")
    @ApiOperation(value = "分页获取轮播图信息接口")
    @RequiresPermissions("sys:rotation:list")
    public DataResult<PageVO<SysRotationChart>> pageInfo(@RequestBody RotationReqVO vo){
        DataResult result= DataResult.success();
        result.setData(rotationChartService.pageInfo(vo));
        return result;
    }

    @PostMapping("/rotation")
    @ApiOperation(value = "新增轮播图接口")
    @RequiresPermissions("sys:rotation:add")
    public DataResult addRotation(@RequestBody @Valid RotationChartReqAddVO vo, HttpServletRequest request){
        String userId= JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        DataResult result= DataResult.success();
        rotationChartService.addRotationChart(vo,userId);
        return result;
    }

    @PutMapping("/rotation")
    @ApiOperation(value = "轮播图后端编辑接口")
    @RequiresPermissions("sys:rotation:update")
    public DataResult updateRotation(@RequestBody @Valid RotationChartUpdateReqVO vo, HttpServletRequest request){
        DataResult result= DataResult.success();
        String userId= JwtTokenUtil.getUserId(request.getHeader(Constant.ACCESS_TOKEN));
        rotationChartService.updateRotationChart(userId,vo);
        return result;
    }

    @DeleteMapping("/rotation")
    @ApiOperation(value = "删除轮播图接口")
    @RequiresPermissions("sys:rotation:delete")
    public DataResult deleteRotation(@RequestBody @Valid List<RotationChartDeleteReqVO> list){
        DataResult result= DataResult.success();
        rotationChartService.batchDeleteRotation(list);
        return result;
    }

    @GetMapping("/rotations")
    @ApiOperation(value = "轮播图前端展现接口")
    public DataResult<List<SysRotationChart>> selectAll(){
        DataResult result= DataResult.success();
        List<SysRotationChart> sysRotationCharts = rotationChartService.selectAll();
        result.setData(sysRotationCharts);
        return result;
    }
}
