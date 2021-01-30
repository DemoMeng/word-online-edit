package com.mqz.online.edit.web.controller;


import com.mqz.online.edit.result.WpsResponseBean;
import com.mqz.online.edit.utils.Token;
import com.mqz.online.edit.web.service.IOnlineEditFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * <p>
 * 在线编辑文件表 前端控制器
 * </p>
 *
 * @author mqz
 * @since 2020-12-11
 */
@Controller
@RequestMapping("/web/online-edit-file")
@Slf4j
public class OnlineEditFileController {

    @Resource
    private IOnlineEditFileService fileTService;

    /**
     * 获取网络文件预览URL
     * @param fileUrl
     * @return t
     */
    @GetMapping("getViewUrlWebPath")
    public ResponseEntity getViewUrlWebPath(String fileUrl) {
        log.info("getViewUrlWebPath：fileUrl={}", fileUrl);
        Token t = fileTService.getViewUrl(fileUrl, true);
        return WpsResponseBean.SUCCESS(t);
    }

    /**
     * 获取所有文件
     *
     * @return db下的所有文件
     */
//    @GetMapping("getFileList")
//    public ResponseEntity<Object> getFileList() {
//        log.info("获取所有文件");
//        List<FileListDTO> result = fileService.getFileList();
//        return Response.success(result);
//    }



    /**
     * 通过fileId获取wpsUrl以及token
     *
     * @param fileId 文件id
     * @return token（包含url）
     */
    @GetMapping("getViewUrlDbPath")
    public ResponseEntity<Object> getViewUrlDbPath(String fileId, String userId) {
        log.info("getViewUrlDbPath：fileId={},userId={}", fileId, userId);
        Token t = fileTService.getViewUrl(fileId, userId, true);
        if (t != null) {
            return WpsResponseBean.SUCCESS(t);
        } else {
            return WpsResponseBean.ERROR("文件不存在或其它异常！");
        }
    }

    /**
     * 转换文件
     *
     * @param srcUri     文件url
     * @param exportType 到处类型
     */
    @PutMapping("convert")
    public ResponseEntity<Object> convert(String taskId , String srcUri, String exportType) {
        fileTService.convertFile(taskId, srcUri, exportType);
        return WpsResponseBean.SUCCESS();
    }

    /**
     * 获取转换文件
     *
     * @param taskId 任务id，由convert接口生成
     */
    @GetMapping("getConvert")
    public ResponseEntity<Object> getConvert(String taskId) {
        Object res = fileTService.getConvertQueryRes(taskId);
        return WpsResponseBean.SUCCESS(res);
    }

}
