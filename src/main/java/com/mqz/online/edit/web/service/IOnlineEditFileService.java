package com.mqz.online.edit.web.service;

import com.mqz.online.edit.utils.Token;
import com.mqz.online.edit.web.dto.FileReqDTO;
import com.mqz.online.edit.web.entity.OnlineEditFile;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 在线编辑文件表 服务类
 * </p>
 *
 * @author mqz
 * @since 2020-12-11
 */
public interface IOnlineEditFileService extends IService<OnlineEditFile> {

    Map<String, Object> getFileInfo(String w_userid, String w_filepath, String w_filetype);

    Map<String, Object> fileSave(MultipartFile file, String w_userid);

    Map<String, Object> fileVersion(Integer version);

    void fileRename(String name, String w_userid);

    Map<String, Object> fileHistory(FileReqDTO req);

    Map<String, Object> fileNew(MultipartFile file, String w_userid);

    void convertCallBack(HttpServletRequest request);

    Token getViewUrl(String fileUrl, boolean b);

    Token getViewUrl(String fileId, String userId, boolean b);

    void convertFile(String taskId, String srcUri, String exportType);

    Object getConvertQueryRes(String taskId);

}
