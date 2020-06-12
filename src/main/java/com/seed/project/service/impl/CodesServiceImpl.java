package com.seed.project.service.impl;

import com.github.pagehelper.PageHelper;
import com.seed.project.entity.SysDept;
import com.seed.project.mapper.CodesMapper;
import com.seed.project.entity.Codes;
import com.seed.project.service.CodesService;
import com.seed.project.core.AbstractService;
import com.seed.project.utils.SqlUtils;
import com.seed.project.vo.req.CodePageReqVO;
import com.seed.project.vo.resp.CodeRespNodeVO;
import com.seed.project.vo.resp.DeptRespNodeVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Created by CodeGenerator on 2020/06/10.
 */
@Service
@Transactional
public class CodesServiceImpl extends AbstractService<Codes> implements CodesService {

    @Resource
    private CodesMapper codesMapper;

    @Override
    public List<CodeRespNodeVO> getAllCodeTree() {
        List<Codes> codesList = codesMapper.selectAll();
        if (codesList.isEmpty()) {
            return Collections.emptyList();
        }
        List<CodeRespNodeVO> codeRespNodeVOS = new ArrayList<>();
        for (Codes code : codesList) {
            CodeRespNodeVO codeRespNodeVO = new CodeRespNodeVO();
            codeRespNodeVO.setId(code.getCodeid());
            codeRespNodeVO.setTitle(code.getCodename());
            codeRespNodeVO.setPid(code.getParentcode());
            codeRespNodeVOS.add(codeRespNodeVO);
        }
        Map<String, List<CodeRespNodeVO>> groupByPidDept = codeRespNodeVOS.stream().filter(codeRespNodeVO -> StringUtils.isNotBlank(codeRespNodeVO.getPid())).collect(Collectors.groupingBy(CodeRespNodeVO::getPid));
        List<CodeRespNodeVO> result = new ArrayList<>();
        for (CodeRespNodeVO codeRespNodeVO : codeRespNodeVOS) {
            codeRespNodeVO.setChildren(groupByPidDept.get(codeRespNodeVO.getId().toString()));
            if (StringUtils.isBlank(codeRespNodeVO.getPid())) {
                result.add(codeRespNodeVO);
            }
        }
        //加根节点
        List<CodeRespNodeVO> res = new ArrayList<>();
        CodeRespNodeVO codeRespNodeVO = new CodeRespNodeVO();
        codeRespNodeVO.setTitle("代码表");
        codeRespNodeVO.setId(-1);
        codeRespNodeVO.setPid("root");
        codeRespNodeVO.setChildren(result);
        res.add(codeRespNodeVO);
        return res;
    }

    @Override
    public List<Codes> getCodeByKey(CodePageReqVO codePageReqVO) {
        Condition condition = new Condition(Codes.class);
        Example.Criteria criteria = condition.createCriteria();
        if (StringUtils.isNotBlank(codePageReqVO.getCodeName())) {
            criteria.andLike("codename", SqlUtils.spliceAllFuzzyQuery(codePageReqVO.getCodeName()));
        }
        if (StringUtils.isNotBlank(codePageReqVO.getCodeValue())) {
            criteria.andLike("codevalue", SqlUtils.spliceAllFuzzyQuery(codePageReqVO.getCodeValue()));
        }
        if (codePageReqVO.getIsuse() != null) {
            criteria.andEqualTo("isuse", codePageReqVO.getIsuse());
        }
        List<Codes> byCondition = findByCondition(condition);
        return byCondition;
    }

    @Override
    public List<Codes> getCodeAndSubCodeById(CodePageReqVO codePageReqVO) {

        PageHelper.startPage(codePageReqVO.getPageNum(),codePageReqVO.getPageSize());
        Integer codeId = codePageReqVO.getCodeId();
        List<Codes> codesList;
        if (-1 == codeId) {
            codesList = codesMapper.selectAll();
        } else {
            Condition condition = new Condition(Codes.class);
            Example.Criteria criteria = condition.createCriteria();
            criteria.orEqualTo("codeid", codeId);
            criteria.orEqualTo("parentcode", codeId);
            codesList = findByCondition(condition);
        }
        PageHelper.clearPage();
        return codesList;
    }

}
