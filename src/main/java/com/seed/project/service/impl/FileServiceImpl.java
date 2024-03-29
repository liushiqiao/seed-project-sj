package com.seed.project.service.impl;

import com.github.pagehelper.PageHelper;
import com.seed.project.entity.SysFile;
import com.seed.project.exception.BusinessException;
import com.seed.project.exception.code.BaseResponseCode;
import com.seed.project.mapper.SysFileMapper;
import com.seed.project.service.FileService;
import com.seed.project.utils.PageUtil;
import com.seed.project.vo.req.FilePageReqVO;
import com.seed.project.vo.resp.PageVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName: FileServiceImpl
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService {
    @Value("${file.path}")
    private String FILE_PATH;
    @Value("${file.base-url}")
    private String baseUrl;
    @Autowired
    private SysFileMapper sysFileMapper;
    @Override
    public String upload(MultipartFile file, String userId, Integer type) {
        String originalFilename = file.getOriginalFilename();
        String extensionName=originalFilename.substring(originalFilename.lastIndexOf(".")+1).toLowerCase();
        String fileId= UUID.randomUUID().toString();
        String fileName=fileId+"."+extensionName;
        checkFileDirExists(FILE_PATH);
        File destFile=new File(FILE_PATH+fileName);
        if(!destFile.getParentFile().exists()){
            destFile.getParentFile().mkdirs();
        }
        String fileUrl=baseUrl+fileName;
        try {

            log.info("baseUrl:{}",fileUrl);
            file.transferTo(destFile);
            SysFile sysFile=new SysFile();
            sysFile.setId(UUID.randomUUID().toString());
            sysFile.setFileName(fileName);
            sysFile.setOriginalName(originalFilename);
            sysFile.setExtensionName(extensionName);
            sysFile.setCreateId(userId);
            sysFile.setType(type);
            sysFile.setFileUrl(fileUrl);
            sysFile.setSize(FileUtils.byteCountToDisplaySize(file.getSize()));
            int i = sysFileMapper.insertSelective(sysFile);
            if(i!=1){
                throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error("e:{}",e);
            throw new BusinessException(BaseResponseCode.UPLOAD_FILE_ERROR);
        }
        return fileUrl;
    }

    @Override
    public void download(String fileId, HttpServletResponse response) {
        SysFile sysFile = sysFileMapper.selectByPrimaryKey(fileId);
        if(sysFile==null){
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        try {
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            String fileName=new String(sysFile.getOriginalName().getBytes("UTF-8"),"ISO-8859-1");
            response.setHeader("content-disposition",String.format("attachment;filename=%s",fileName));
        } catch (UnsupportedEncodingException e) {
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
        ServletOutputStream outputStream=null;
        try {
            File file=new File(FILE_PATH+sysFile.getFileName());
            outputStream = response.getOutputStream();
            IOUtils.write(FileUtils.readFileToByteArray(file),outputStream);
        } catch (IOException e) {
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }finally {
            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public int deleteByFileUrl(String fileUrl) {
        //删除了文件信息表里面的数据
        int i = sysFileMapper.deleteByFileUrl(fileUrl);
        String fileName=fileUrl.substring(fileUrl.lastIndexOf("/")+1);
        //删除磁盘文件
        deleteDestFile(fileName);
        return i;
    }

    private void deleteDestFile(String fileName){
        File file=new File(FILE_PATH+fileName);
        if(file.exists()){
            file.delete();
        }
    }

    @Override
    public PageVO<SysFile> pageInfo(FilePageReqVO vo, String userId) {
        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        List<SysFile> sysFiles = sysFileMapper.selectByUserId(userId);
        return PageUtil.getPageVO(sysFiles);
    }

    private static void checkFileDirExists(String path){
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();//区别于mkdir。mkdir如果上级目录不存在，则报错
        }else{
            doClean(file);
        }
    }
    private static void doClean(File fileDir){
        if(fileDir.isDirectory()){ //检查该file是否为一个文件夹
            File[] fs = fileDir.listFiles();
            if(fs.length != 0){
                for(int i = 0; i < fs.length; i++){
                    if(fs[i].isDirectory()){
                        doClean(fs[i]);
                    }else{
                        fs[i].delete();  //delete只能删除文件和空文件夹
                    }
                }
            }
        }
        fileDir.delete();
    }
}
