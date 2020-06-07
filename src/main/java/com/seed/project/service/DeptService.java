package com.seed.project.service;

import com.seed.project.entity.SysDept;
import com.seed.project.vo.req.DeptAddReqVO;
import com.seed.project.vo.req.DeptUpdateReqVO;
import com.seed.project.vo.resp.DeptRespNodeVO;

import java.util.List;

/**
 * @ClassName: DeptService
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
public interface DeptService {

    List<SysDept> selectAll();

    List<String> getSubDeptIdByPid(String pid);

    List<DeptRespNodeVO> deptTreeList(String deptId);

    SysDept addDept(DeptAddReqVO vo);

    void updateDept(DeptUpdateReqVO vo);

    void deletedDept(String id);

    List<DeptRespNodeVO> getAllDeptTreeList();

}
