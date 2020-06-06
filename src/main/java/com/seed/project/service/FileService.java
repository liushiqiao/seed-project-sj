package com.seed.project.service;

import com.seed.project.vo.req.FilePageReqVO;
import com.seed.project.vo.resp.PageVO;
import com.seed.project.entity.SysFile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;


/**
 * @ClassName: FileService
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
public interface FileService {

    String upload(MultipartFile file, String userId, Integer type);

    void download(String fileId, HttpServletResponse response);

    int deleteByFileUrl(String fileUrl);

    PageVO<SysFile> pageInfo(FilePageReqVO vo, String userId);
}
