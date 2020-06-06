package com.seed.project.service;

import com.seed.project.vo.req.LoginReqVO;
import com.seed.project.vo.req.UserAddReqVO;
import com.seed.project.vo.req.UserOwnRoleReqVO;
import com.seed.project.vo.req.UserPageReqVO;
import com.seed.project.vo.req.UserUpdateDetailInfoReqVO;
import com.seed.project.vo.req.UserUpdatePwdReqVO;
import com.seed.project.vo.req.UserUpdateReqVO;
import com.seed.project.vo.resp.LoginRespVO;
import com.seed.project.vo.resp.PageVO;
import com.seed.project.entity.SysUser;
import com.seed.project.vo.req.*;
import com.seed.project.vo.resp.UserOwnRoleRespVO;

import java.util.List;

/**
 * @ClassName: UserService
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
public interface UserService {

    LoginRespVO login(LoginReqVO vo);


    PageVO<SysUser> pageInfo(UserPageReqVO vo);

    PageVO<SysUser> pageInfoByDeptId(UserPageReqVO vo);

    void addUser(UserAddReqVO vo);

    UserOwnRoleRespVO getUserOwnRole(String userId);

    void setUserOwnRole(UserOwnRoleReqVO vo);

    String refreshToken(String refreshToken);

    void updateUserInfo(UserUpdateReqVO vo, String operationId);

    void deletedUsers(List<String> list, String operationId);

    List<SysUser> selectUserInfoByDeptIds(List<String> deptIds);

    SysUser detailInfo(String userId);

    //个人用户编辑信息接口
    void userUpdateDetailInfo(UserUpdateDetailInfoReqVO vo, String userId);

    void userUpdatePwd(UserUpdatePwdReqVO vo, String accessToken, String refreshToken);

    void logout(String accessToken,String refreshToken);

}
