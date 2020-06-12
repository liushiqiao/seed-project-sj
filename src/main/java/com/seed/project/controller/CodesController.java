package com.seed.project.controller;
import com.seed.project.entity.Codes;
import com.seed.project.entity.SysDept;
import com.seed.project.service.CodesService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seed.project.utils.PageUtil;
import com.seed.project.vo.req.CodePageReqVO;
import com.seed.project.vo.resp.CodeRespNodeVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.seed.project.aop.annotation.LogAnnotation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.seed.project.utils.DataResult;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2020/06/10.
*/
@RestController
@RequestMapping("/api/codes")
@Api(tags = "")
public class CodesController {
    @Resource
    private CodesService codesService;

    @ApiOperation(value = "添加码表记录")
    @LogAnnotation(title = "码表管理-添加码表记录",action = "添加码表记录")
    // @RequiresPermissions("codes:add")
    @PostMapping("/add")
    public DataResult add(@RequestBody Codes codes) {
        codesService.save(codes);
        return DataResult.success();
    }

    @ApiOperation(value = "删除码表")
    @LogAnnotation(title = "码表管理-删除码表",action = "删除码表")
    // @RequiresPermissions("codes:delete")
    @DeleteMapping("/delete")
    public DataResult delete(@RequestBody List<Integer> ids) {
        String strIds = StringUtils.join(ids, ",");
        codesService.deleteByIds(strIds);
        return DataResult.success();
    }
    @ApiOperation(value = "更新码表")
    @LogAnnotation(title = "码表管理-更新码表",action = "更新码表")
    // @RequiresPermissions("codes:update")
    @PutMapping("/update")
    public DataResult update(@RequestBody Codes codes) {
        codesService.update(codes);
        return DataResult.success();
    }

    @ApiOperation(value = "查询码表根据id")
    @LogAnnotation(title = "码表管理-查询码表根据id",action = "查询码表根据id")
    // @RequiresPermissions("codes:detail")
    @GetMapping("/detail")
    public DataResult detail(@RequestParam Integer id) {
        Codes codes = codesService.findById(id);
        return DataResult.success(codes);
    }

    @ApiOperation(value = "分页查询码表记录")
    @LogAnnotation(title = "码表管理-分页查询码表记录",action = "分页查询码表记录")
    // @RequiresPermissions("codes:list")
    @PostMapping("/list")
    public DataResult list(@RequestBody CodePageReqVO codePageReqVO) {
        PageHelper.startPage(codePageReqVO.getPageNum(), codePageReqVO.getPageSize());

        List<Codes> list = codesService.getCodeByKey(codePageReqVO);
        return DataResult.success(PageUtil.getPageVO(list));
    }

    @GetMapping("/codeTree")
    @ApiOperation(value = "查询所有码表树形结构数据接口")
    @LogAnnotation(title = "码表管理-码表管理",action = "查询所有码表树形结构数据接口")
    // @RequiresPermissions("sys:dept:list")
    public DataResult<List<CodeRespNodeVO>> getCodeTree(){
        DataResult result =DataResult.success();
        result.setData(codesService.getAllCodeTree());
        return result;
    }

    @PostMapping("/getCodeAndSubCodeById")
    @ApiOperation(value = "根据树形菜单过滤码表")
    @LogAnnotation(title = "码表管理-码表管理",action = "根据树形菜单过滤码表")
    public DataResult<List<Codes>> getCodeAndSubCodeById(@RequestBody CodePageReqVO codePageReqVO){
        DataResult result =DataResult.success();
        List<Codes> list = codesService.getCodeAndSubCodeById(codePageReqVO);
        result.setData(PageUtil.getPageVO(list));
        return result;
    }
}
