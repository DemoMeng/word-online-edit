package com.mqz.online.edit.web.controller.wps;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mqz.online.edit.result.WpsResponseBean;
import com.mqz.online.edit.web.dto.FileReqDTO;
import com.mqz.online.edit.web.service.IOnlineEditFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author mqz
 * @since
 * @description 文件操作回调
 * @abount https://github.com/DemoMeng
 */
@RestController
@RequestMapping("v1/3rd/file")
@Slf4j
public class FileCallBackController {

    @Resource
    private IOnlineEditFileService fileService;

    /**
     * 获取文件元数据
     */
    @GetMapping("info")
    public Object getFileInfo(String _w_userid, String _w_filepath, String _w_filetype) {
        log.info("获取文件元数据userId:{},path:{},type:{}", _w_userid, _w_filepath, _w_filetype);
        try {
            Map<String, Object> map = fileService.getFileInfo(_w_userid, _w_filepath, _w_filetype);
            return JSONUtil.toJsonStr(map);
//            return WpsResponseBean.SUCCESS(map);
        } catch (Exception e) {
            return WpsResponseBean.ERROR("获取文件元数据异常");
        }
    }

    /**
     * 通知此文件目前有哪些人正在协作
     */
    @PostMapping("online")
    public ResponseEntity fileOnline(@RequestBody JSONObject obj) {
        log.info("通知此文件目前有哪些人正在协作param:{}", JSON.toJSON(obj));
        return WpsResponseBean.SUCCESS();
    }

    /**
     * 上传文件新版本
     */
    @PostMapping("save")
    public Object fileSave(MultipartFile file,String _w_userid){
        log.info("上传文件新版本");
        Map<String, Object> map = fileService.fileSave(file, _w_userid);
        return JSONUtil.toJsonStr(map);
        //return WpsResponseBean.SUCCESS(map);
    }

    /**
     * 获取特定版本的文件信息
     */
    @GetMapping("version/{version}")
    public Object fileVersion(@PathVariable Integer version) {
        log.info("获取特定版本的文件信息version:{}", version);
        Map<String, Object> res = fileService.fileVersion(version);
        return JSONUtil.toJsonStr(res);
        //return WpsResponseBean.SUCCESS(res);
    }

    /**
     * 文件重命名
     */
    @PutMapping("rename")
    public ResponseEntity fileRename(@RequestBody FileReqDTO req, String _w_userid) {
        log.info("文件重命名param:{},userId:{}", JSON.toJSON(req), _w_userid);
        fileService.fileRename(req.getName(), _w_userid);
        return WpsResponseBean.SUCCESS();
    }

    /**
     * 获取所有历史版本文件信息
     */
    @PostMapping("history")
    public Object fileHistory(@RequestBody FileReqDTO req) {
        log.info("获取所有历史版本文件信息param:{}", JSON.toJSON(req));
        Map<String, Object> result = fileService.fileHistory(req);
        return JSONUtil.toJsonStr(result);
        //return WpsResponseBean.SUCCESS(result);
    }

    /**
     * 新建文件
     */
    @PostMapping("new")
    public Object fileNew(MultipartFile file, String _w_userid) {
        log.info("新建文件_w_userid:{}", _w_userid);
        Map<String, Object> res = fileService.fileNew(file, _w_userid);
        return WpsResponseBean.SUCCESS(res);
    }

    /**
     * 文件格式转换回调--wps官方回掉用
     */
    @PostMapping("convertCallback")
    public ResponseEntity callback(HttpServletRequest request) {
        log.info("文件转换回掉");
        fileService.convertCallBack(request);
        return WpsResponseBean.SUCCESS();
    }

}
