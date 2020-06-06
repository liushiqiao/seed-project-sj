package com.seed.project.service;

import com.seed.project.vo.req.RotationChartDeleteReqVO;
import com.seed.project.vo.req.RotationChartReqAddVO;
import com.seed.project.vo.req.RotationChartUpdateReqVO;
import com.seed.project.vo.req.RotationReqVO;
import com.seed.project.vo.resp.PageVO;
import com.seed.project.entity.SysRotationChart;

import java.util.List;

/**
 * @ClassName: RotationChartService
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
public interface RotationChartService {

    PageVO<SysRotationChart> pageInfo(RotationReqVO vo);
    void addRotationChart(RotationChartReqAddVO vo, String userId);
    void updateRotationChart(String userId, RotationChartUpdateReqVO vo);
    int batchDeleteRotation(List<RotationChartDeleteReqVO> list);
    List<SysRotationChart> selectAll();
}
