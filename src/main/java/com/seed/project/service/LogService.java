package com.seed.project.service;

import com.seed.project.entity.SysLog;
import com.seed.project.vo.req.SysLogPageReqVO;
import com.seed.project.vo.resp.PageVO;

import java.util.List;

/**
 * @ClassName: LogService
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
public interface LogService {

    PageVO<SysLog> pageInfo(SysLogPageReqVO vo);

    void deletedLog(List<String> logIds);
}
