package com.seed.project.service;

import com.seed.project.vo.req.AddRoleReqVO;
import com.seed.project.vo.req.RolePageReqVO;
import com.seed.project.vo.req.RoleUpdateReqVO;
import com.seed.project.vo.resp.PageVO;
import com.seed.project.entity.SysRole;

import java.util.List;

/**
 * @ClassName: RoleService
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
public interface RoleService {
    PageVO<SysRole> pageInfo(RolePageReqVO vo);
    SysRole addRole(AddRoleReqVO vo);
    List<SysRole> selectAll();
    SysRole detailInfo(String id);
    void updateRole(RoleUpdateReqVO vo);
    void deletedRole(String roleId);

    List<String> getNamesByUserId(String userId);
}
