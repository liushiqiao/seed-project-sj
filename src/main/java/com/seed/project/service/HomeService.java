package com.seed.project.service;

import com.seed.project.vo.resp.HomeRespVO;

/**
 * @ClassName: HomeService
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
public interface HomeService {
    HomeRespVO getHome(String userId);
}
