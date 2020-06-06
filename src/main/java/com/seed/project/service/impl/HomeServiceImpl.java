package com.seed.project.service.impl;

import com.seed.project.entity.SysUser;
import com.seed.project.mapper.SysUserMapper;
import com.seed.project.service.HomeService;
import com.seed.project.vo.resp.HomeRespVO;
import com.seed.project.vo.resp.PermissionRespNodeVO;
import com.seed.project.vo.resp.UserInfoRespVO;
import com.seed.project.service.PermissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: HomeServiceImpl
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private PermissionService permissionService;
    @Override
    public HomeRespVO getHome(String userId) {
        HomeRespVO homeRespVO=new HomeRespVO();
        /**
         * mock 导航菜单数据后期直接从DB获取
         */
//        String home="[{\"children\":[{\"children\":[{\"children\":[{\"children\":[{\"children\":[],\"id\":\"6\",\"title\":\"五级类目5-6\",\"url\":\"string\"}],\"id\":\"5\",\"title\":\"四级类目4-5\",\"url\":\"string\"}],\"id\":\"4\",\"title\":\"三级类目3-4\",\"url\":\"string\"}],\"id\":\"3\",\"title\":\"二级类目2-3\",\"url\":\"string\"}],\"id\":\"1\",\"title\":\"类目1\",\"url\":\"string\"},{\"children\":[],\"id\":\"2\",\"title\":\"类目2\",\"url\":\"string\"}]";
//        String home="[\n" +
//                "    {\n" +
//                "        \"children\": [\n" +
//                "            {\n" +
//                "                \"children\": [],\n" +
//                "                \"id\": \"3\",\n" +
//                "                \"title\": \"菜单权限管理\",\n" +
//                "                \"url\": \"/index/menus\"\n" +
//                "            }\n" +
//                "        ],\n" +
//                "        \"id\": \"1\",\n" +
//                "        \"title\": \"组织管理\",\n" +
//                "        \"url\": \"string\"\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"children\": [],\n" +
//                "        \"id\": \"2\",\n" +
//                "        \"title\": \"类目2\",\n" +
//                "        \"url\": \"string\"\n" +
//                "    }\n" +
//                "]";
        List<PermissionRespNodeVO> list=permissionService.permissionTreeList(userId);
        homeRespVO.setMenus(list);
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        UserInfoRespVO vo=new UserInfoRespVO();
        if(sysUser!=null){
            BeanUtils.copyProperties(sysUser,vo);
            vo.setDeptName("迎学教育总公司");
        }
        homeRespVO.setUserInfoVO(vo);
        return homeRespVO;
    }
}
