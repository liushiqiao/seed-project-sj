package com.seed.project.service.impl;

import com.github.pagehelper.PageHelper;
import com.seed.project.entity.SysLog;
import com.seed.project.exception.BusinessException;
import com.seed.project.exception.code.BaseResponseCode;
import com.seed.project.mapper.SysLogMapper;
import com.seed.project.utils.PageUtil;
import com.seed.project.vo.req.SysLogPageReqVO;
import com.seed.project.vo.resp.PageVO;
import com.seed.project.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: LogServiceImpl
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private SysLogMapper sysLogMapper;
    @Override
    public PageVO<SysLog> pageInfo(SysLogPageReqVO vo) {
        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        return PageUtil.getPageVO(sysLogMapper.selectAll(vo));
    }

    @Override
    public void deletedLog(List<String> logIds) {
        int i = sysLogMapper.batchDeletedLog(logIds);
        if(i==0){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
    }
}
