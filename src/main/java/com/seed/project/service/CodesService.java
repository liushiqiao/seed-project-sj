package com.seed.project.service;

import com.seed.project.core.Service;
import com.seed.project.entity.Codes;
import com.seed.project.vo.req.CodePageReqVO;
import com.seed.project.vo.resp.CodeRespNodeVO;

import java.util.List;


/**
 * Created by CodeGenerator on 2020/06/10.
 */
public interface CodesService extends Service<Codes> {

    List<CodeRespNodeVO> getAllCodeTree();

    List<Codes> getCodeByKey(CodePageReqVO codePageReqVO);

    List<Codes> getCodeAndSubCodeById(CodePageReqVO codePageReqVO);
}
