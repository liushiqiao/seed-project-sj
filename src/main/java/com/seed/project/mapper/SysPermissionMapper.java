package com.seed.project.mapper;

import com.seed.project.entity.SysPermission;

import java.util.List;


public interface SysPermissionMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    SysPermission selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);

    List<SysPermission> selectAll();

    List<SysPermission> selectChild(String permissionId);
    List<SysPermission> selectByIds (List<String> ids);


}