package com.seed.project.controller;
import com.seed.project.entity.Score;
import com.seed.project.service.ScoreService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.seed.project.aop.annotation.LogAnnotation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.seed.project.utils.DataResult;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2020/05/30.
*/
@RestController
@RequestMapping("/score")
@Api(tags = "")
public class ScoreController {
    @Resource
    private ScoreService scoreService;

    @ApiOperation(value = "")
    @LogAnnotation(title = "",action = "")
    @RequiresPermissions("score:add")
    @PostMapping("/add")
    public DataResult add(Score score) {
        scoreService.save(score);
        return DataResult.success();
    }

    @ApiOperation(value = "")
    @LogAnnotation(title = "",action = "")
    @RequiresPermissions("score:delete")
    @PostMapping("/delete")
    public DataResult delete(@RequestParam Integer id) {
        scoreService.deleteById(id);
        return DataResult.success();
    }
    @ApiOperation(value = "")
    @LogAnnotation(title = "",action = "")
    @RequiresPermissions("score:update")
    @PostMapping("/update")
    public DataResult update(Score score) {
        scoreService.update(score);
        return DataResult.success();
    }

    @ApiOperation(value = "")
    @LogAnnotation(title = "",action = "")
    @RequiresPermissions("score:detail")
    @PostMapping("/detail")
    public DataResult detail(@RequestParam Integer id) {
        Score score = scoreService.findById(id);
        return DataResult.success(score);
    }

    @ApiOperation(value = "")
    @LogAnnotation(title = "",action = "")
    @RequiresPermissions("score:list")
    @PostMapping("/list")
    public DataResult list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Score> list = scoreService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return DataResult.success(pageInfo);
    }
}
