package com.seed.project.service;

import com.seed.project.entity.SysPermission;
import com.seed.project.vo.req.PermissionAddReqVO;
import com.seed.project.vo.req.PermissionUpdateReqVO;
import com.seed.project.vo.resp.PermissionRespNodeVO;

import java.util.List;

/**
 * @ClassName: PermissionService
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
public interface PermissionService {
    List<SysPermission> selectAll();
    List<PermissionRespNodeVO> selectAllMenuByTree();
    SysPermission addPermission(PermissionAddReqVO vo);
    List<PermissionRespNodeVO> permissionTreeList(String userId);
    List<PermissionRespNodeVO> selectAllTree();
    void updatePermission(PermissionUpdateReqVO vo);
    void deletedPermission(String permissionId);
    List<String> getPermissionByUserId(String userId);
    List<SysPermission> getPermissions(String userId);
}
