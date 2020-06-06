package com.seed.project.controller;

import com.seed.project.aop.annotation.LogAnnotation;
import com.seed.project.contants.Constant;
import com.seed.project.service.HomeService;
import com.seed.project.utils.DataResult;
import com.seed.project.utils.JwtTokenUtil;
import com.seed.project.vo.resp.HomeRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: HomeController
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@RestController
@RequestMapping("/api")
@Api(tags = "首页模块",description = "首页相关模块")
public class HomeController {
    @Autowired
    private HomeService homeService;
    @GetMapping("/home")
    @ApiOperation(value = "获取首页数据接口")
    @LogAnnotation(title = "首页模块",action = "获取首页数据接口")
    public DataResult<HomeRespVO> getHome(HttpServletRequest request){
        String accessToken=request.getHeader(Constant.ACCESS_TOKEN);
        String userId= JwtTokenUtil.getUserId(accessToken);
        DataResult result =DataResult.success();
        result.setData(homeService.getHome(userId));
        return result;
    }
}
