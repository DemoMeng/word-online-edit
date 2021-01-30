package com.mqz.online.edit.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mqz.online.edit.config.properties.ConvertProperties;
import com.mqz.online.edit.config.properties.ServerProperties;
import com.mqz.online.edit.config.properties.WpsProperties;
import com.mqz.online.edit.config.wps.Context;
import com.mqz.online.edit.utils.*;
import com.mqz.online.edit.utils.file.FileUtil;
import com.mqz.online.edit.utils.upload.OSSUtil;
import com.mqz.online.edit.utils.upload.ResFileDTO;
import com.mqz.online.edit.web.dto.*;
import com.mqz.online.edit.web.entity.*;
import com.mqz.online.edit.web.mapper.OnlineEditFileMapper;
import com.mqz.online.edit.web.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 在线编辑文件表 服务实现类
 * </p>
 *
 * @author mqz
 * @since 2020-12-11
 */
@Service
@Slf4j
public class OnlineEditFileServiceImpl extends ServiceImpl<OnlineEditFileMapper, OnlineEditFile> implements IOnlineEditFileService {

    @Resource
    private IOnlineEditUserAclService userAclTService;
    @Resource
    private IOnlineEditFileWatermarkService watermarkTService;
    @Resource
    private IOnlineEditUserService userTService;
    @Resource
    private OSSUtil ossUtil;
    @Resource
    private IOnlineEditFileVersionService fileVersionTService;
    @Resource
    private WpsProperties wpsProperties;
    @Resource
    private WpsUtil wpsUtil;
    @Resource
    private ConvertProperties convertProperties;
    @Resource
    private ServerProperties serverProperties;


    /**
     * 获取文件元数据
     *
     * @param filePath 文件路径
     */
    private Map<String, Object> getWebFileInfo(String filePath) {
        log.info("_w_filepath:{}", filePath);
        // 构建默认user信息
        UserDTO wpsUser = new UserDTO()
                .setAvatar_url("http://api-new.oss-cn-hangzhou.aliyuncs.com/elephant.png")
                .setId("-1")
                .setName("蒙大大大拿")
                .setPermission("read");
        int fileSize = FileUtil.getFileSize(filePath);
        // 构建文件
        FileDTO file = new FileDTO()
                .setId(Context.getFileId())
                .setName(FileUtil.getFileName(filePath))
                .setVersion(1)
                .setSize(fileSize)
                .setCreator("-1")
                .setCreate_time(System.currentTimeMillis())
                .setDownload_url(filePath)
                .setUser_acl(new UserAclBO())
                .setWatermark(new WatermarkBO());

        return new HashMap<String, Object>() {
            {
                put("file", file);
                put("user", wpsUser);
            }
        };
    }

    /**
     * 获取文件元数据
     *
     * @param userId 用户id
     */
    private Map<String, Object> getDbFileInfo(String userId) {
        String fileId = Context.getFileId();

        // 获取文件信息
        OnlineEditFile fileEntity = getById(fileId);
        // 初始化文件读写权限为read
        String permission = "write";
        // 增加用户权限
        LambdaQueryWrapper<OnlineEditUserAcl> la = new LambdaQueryWrapper<>();
        la.eq(OnlineEditUserAcl::getFileId,fileId)
                .eq(OnlineEditUserAcl::getUserId,userId);
        OnlineEditUserAcl userAclEntity =  userAclTService.getOne(la);
        UserAclBO userAcl = new UserAclBO();
        if (userAclEntity != null) {
            userAcl.setHistory(userAclEntity.getHistory())
                    .setRename(userAclEntity.getReName())
                    .setCopy(userAclEntity.getCopy())
                    .setExport(userAclEntity.getExport())
                    .setPrint(userAclEntity.getPrint());
            permission = userAclEntity.getPermission();
        }
        LambdaQueryWrapper<OnlineEditFileWatermark> waterLa = new LambdaQueryWrapper<>();
        waterLa.eq(OnlineEditFileWatermark::getFileId,fileId);
        // 增加水印
        OnlineEditFileWatermark watermarkEntity = watermarkTService.getOne(waterLa);
        WatermarkBO watermark = new WatermarkBO();
        if (watermarkEntity != null) {
            watermark.setFillstyle(watermarkEntity.getFillstyle())
                        .setFont(watermarkEntity.getFont())
                        .setHorizontal(watermarkEntity.getHorizontal())
                        .setRotate(watermarkEntity.getRotate())
                        .setType(watermarkEntity.getType())
                        .setValue(watermarkEntity.getValue())
                        .setVertical(watermarkEntity.getVertical());
        }
        //获取user
        LambdaQueryWrapper<OnlineEditUser> userLa = new LambdaQueryWrapper<>();
        userLa.eq(OnlineEditUser::getId,userId);
        OnlineEditUser wpsUser = userTService.getOne(userLa);
        UserDTO user = new UserDTO();
        if (wpsUser != null) {
            user.setAvatar_url(wpsUser.getAvatarUrl())
                    .setId(String.valueOf(wpsUser.getId()))
                    .setName(wpsUser.getName())
                    .setPermission(permission);
        }
        // 构建fileInfo
        FileDTO file = new FileDTO()
                .setId(fileEntity.getId())
                .setCreate_time(fileEntity.getCreateTime())
                .setCreator(fileEntity.getCreator())
                .setVersion(fileEntity.getVersion())
                .setDownload_url(fileEntity.getDownloadUrl())
                .setName(fileEntity.getName())
                .setSize(fileEntity.getSize())
                .setModifier(fileEntity.getModifier())
                .setModify_time(fileEntity.getModifyTime())
                .setUser_acl(userAcl)
                .setWatermark(watermark);
        return new HashMap<String, Object>(5) {
            {
                put("code", 200);
                put("msg", "ok");
                put("status", 200);
                put("file", file);
                put("user", user);
            }
        };
    }

    @Override
    public Map<String, Object> getFileInfo(String w_userid, String w_filepath, String w_filetype) {
        if ("web".equalsIgnoreCase(w_filetype)) {
            return getWebFileInfo(w_filepath);
        } else if ("db".equalsIgnoreCase(w_filetype)) {
            return getDbFileInfo(w_userid);
        }
        return null;
    }

    @Override
    public Map<String, Object> fileSave(MultipartFile multipartFile, String w_userid) {
        Date date = new Date();
        // 上传
        ResFileDTO resFileDTO = ossUtil.uploadMultipartFile(multipartFile);
        int size = (int) resFileDTO.getFileSize();
        String fileId = Context.getFileId();
        LambdaQueryWrapper<OnlineEditFile> la = new LambdaQueryWrapper<>();
        la.eq(OnlineEditFile::getId,fileId);
        OnlineEditFile file =getOne(la);
        FileDTO fileInfo = new FileDTO();
        String oldFileUrl = file.getDownloadUrl();
        // 更新当前版本
        file.setVersion(file.getVersion() + 1)
            .setDownloadUrl(resFileDTO.getFileUrl())
            .setModifier(w_userid)
            .setModifyTime(date.getTime())
            .setSize(size);
        updateById(file);
        // 保存历史版本
        OnlineEditFileVersion fileVersion = new OnlineEditFileVersion();
        fileVersion.setCreateTime(file.getCreateTime())
                .setCreator(file.getCreator())
                .setModifier(file.getModifier())
                .setModifyTime(file.getModifyTime())
                .setName(file.getName())
                .setSize(size)
                .setDownloadUrl(oldFileUrl)
                .setVersion(file.getVersion() - 1)
                .setFileId(fileId);
        fileVersionTService.save(fileVersion);

        // 返回当前版本信息
        BeanUtils.copyProperties(file, fileInfo);
        Map<String, Object> map = new HashMap<>();
        map.put("file", fileInfo);
        map.put("code", 200);
        map.put("msg", "ok");
        map.put("status", 200);
        return map;
    }

    @Override
    public Map<String, Object> fileVersion(Integer version) {
        FileDTO fileInfo = new FileDTO();
        LambdaQueryWrapper<OnlineEditFileVersion> la = new LambdaQueryWrapper<>();
        String fileId = Context.getFileId();
        la.eq(OnlineEditFileVersion::getVersion,version)
                .eq(OnlineEditFileVersion::getFileId,fileId);
        OnlineEditFileVersion fileVersion = fileVersionTService.getOne(la);
        if (fileVersion != null) {
            fileInfo.setModify_time(fileVersion.getModifyTime());
            fileInfo.setModifier(fileVersion.getModifier());
            fileInfo.setSize(fileVersion.getSize());
            fileInfo.setDownload_url(fileVersion.getDownloadUrl());
            fileInfo.setName(fileVersion.getName());
            fileInfo.setCreator(fileVersion.getCreator());
            fileInfo.setCreate_time(fileVersion.getCreateTime());
            fileInfo.setVersion(fileVersion.getVersion());
            fileInfo.setId(fileVersion.getFileId());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("file", fileInfo);
        return map;
    }

    @Override
    public void fileRename(String name, String w_userid) {
        String fileId = Context.getFileId();
        LambdaQueryWrapper<OnlineEditFile> la = new LambdaQueryWrapper<>();
        la.eq(OnlineEditFile::getId,fileId);
        OnlineEditFile file = getOne(la);
        if (file != null) {
            file.setName(name);
            file.setModifier(w_userid);
            Date date = new Date();
            file.setModifyTime(date.getTime());
            updateById(file);
        }
    }

    @Override
    public Map<String, Object> fileHistory(FileReqDTO req) {
        List<FileHisDTO> result = new ArrayList<>();
        if (req.getId() != null) {
            // 目前先实现获取所有的历史记录
            LambdaQueryWrapper<OnlineEditFileVersion> la = new LambdaQueryWrapper<>();
            la.eq(OnlineEditFileVersion::getFileId,req.getId())
                    .orderByDesc(OnlineEditFileVersion::getVersion);
            List<OnlineEditFileVersion> versionList = fileVersionTService.list(la);

            if (versionList != null && versionList.size() > 0) {
                Set<String> userIdSet = new HashSet<>();
                for (OnlineEditFileVersion fileVersion : versionList) {
                    userIdSet.add(fileVersion.getModifier());
                    userIdSet.add(fileVersion.getCreator());
                }
                List<String> userIdList = new ArrayList<>(userIdSet);
                // 获取所有关联的人
                List<OnlineEditUser> userList = userTService.findByIdIn(userIdList);
                if (userList != null && userList.size() > 0) {
                    for (OnlineEditFileVersion fileVersion : versionList) {
                        FileHisDTO fileHis = new FileHisDTO();
                        fileHis.setId(fileVersion.getFileId());
                        fileHis.setCreate_time(fileVersion.getCreateTime());
                        fileHis.setDownload_url(fileVersion.getDownloadUrl());
                        fileHis.setName(fileVersion.getName());
                        fileHis.setSize(fileVersion.getSize());
                        fileHis.setVersion(fileVersion.getVersion());
                        UserDTO creator = new UserDTO();
                        UserDTO modifier = new UserDTO();
                        for (OnlineEditUser user : userList) {
                            if (user.getId().equals(fileVersion.getCreator())) {
                                //BeanUtils.copyProperties(user, creator);
                                creator.setAvatar_url(user.getAvatarUrl());
                                creator.setId(String.valueOf(user.getId()));
                                creator.setName(user.getName());
                            }
                            if (user.getId().equals(fileVersion.getModifier())) {
                                //BeanUtils.copyProperties(user, modifier);
                                modifier.setAvatar_url(user.getAvatarUrl());
                                modifier.setId(String.valueOf(user.getId()));
                                modifier.setName(user.getName());
                            }
                        }
                        fileHis.setModifier(modifier);
                        fileHis.setCreator(creator);
                        result.add(fileHis);
                    }
                }
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "ok");
        map.put("status", 200);
        map.put("histories", result);
        return map;
    }

    @Override
    public Map<String, Object> fileNew(MultipartFile file, String w_userid) {
        ResFileDTO resFileDTO = ossUtil.uploadMultipartFile(file);
        String fileName = resFileDTO.getFileName();
        String fileUrl = resFileDTO.getFileUrl();
        int fileSize = (int) file.getSize();
        Date date = new Date();
        long dataTime = date.getTime();
        // 保存文件
        OnlineEditFile f = new OnlineEditFile()
                .setName(fileName)
                .setVersion(1)
                .setSize(fileSize)
                .setCreator(w_userid)
                .setModifier(w_userid)
                .setCreateTime(dataTime)
                .setModifyTime(dataTime)
                .setDownloadUrl(fileUrl);
        save(f);

        // 处理权限
        OnlineEditUserAcl u = new OnlineEditUserAcl()
                .setFileId(f.getId())
                .setPermission("write")
                .setUserId(Integer.valueOf(w_userid))
                .setReName(1)
                .setHistory(1);
        userAclTService.save(u);

        // 处理水印
        OnlineEditFileWatermark water = new OnlineEditFileWatermark()
                .setFileId(f.getId())
                .setType(0)
                .setValue("水印")
                .setFillstyle("rgba( 192, 192, 192, 0.6 )")
                .setFont("bold 20px Serif")
                .setRotate(new BigDecimal("0"))
                .setHorizontal(50)
                .setVertical(50);
        watermarkTService.save(water);
        // 处理返回
        Map<String, Object> map = new HashMap<>();
        map.put("redirect_url", this.getViewUrl(f.getId(), w_userid, false).getWpsUrl());
        map.put("user_id", w_userid);
        map.put("code", 200);
        map.put("msg", "ok");
        map.put("status", 200);
        return map;
    }



    /**
     * 获取预览用URL
     *
     * @param fileId     文件id
     * @param userId     用户id
     * @param checkToken 是否校验token
     */
    @Override
    public Token getViewUrl(String fileId, String userId, boolean checkToken) {
        LambdaQueryWrapper<OnlineEditFile> la = new LambdaQueryWrapper<>();
        la.eq(OnlineEditFile::getId,fileId);
        OnlineEditFile fileEntity = getOne(la);
        if (fileEntity != null) {
            Token t = new Token();
            String fileName = fileEntity.getName();
            String fileType = FileUtil.getFileTypeByName(fileName);
            UUID randomUUID = UUID.randomUUID();
            String uuid = randomUUID.toString().replace("-", "");
            Map<String, String> values = new HashMap<String, String>() {
                {
                    put("_w_appid", wpsProperties.getAppid());
                    if (checkToken) {
                        put("_w_tokentype", "1");
                    }
                    put("_w_filepath", fileName);
                    put("_w_userid", userId);
                    put("_w_filetype", "db");
                }
            };
            String wpsUrl = wpsUtil.getWpsUrl(values, fileType, fileEntity.getId());
            t.setToken(uuid);
            t.setExpires_in(600);
            t.setWpsUrl(wpsUrl);
            return t;
        }
        return null;
    }

    @Override
    public void convertFile(String taskId, String srcUri, String exportType) {
        if (StringUtils.isEmpty(taskId)) {
            taskId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        }
        System.out.println("--convertFile:taskId:-> " + taskId);
        String headerDate = Common.getGMTDate();
        Map<String, Object> param = new LinkedHashMap<>();
        param.put("SrcUri", srcUri);
        param.put("FileName", FileUtil.getFileName(srcUri));
        param.put("ExportType", exportType);
        param.put("CallBack", serverProperties.getDomain() + ":" + serverProperties.getPort() + "/v1/3rd/file/convertCallback");//回调地址，文件转换后的通知地址，需保证可访问
        param.put("TaskId", taskId);
        //Content-MD5 表示请求内容数据的MD5值，对消息内容（不包括头部）计算MD5值获得128比特位数字，对该数字进行base64编码而得到，如”eB5eJF1ptWaXm4bijSPyxw==”，也可以为空；
        String contentMd5 = Common.getMD5(param);
        //签名url的参数不带请求参数
        String authorization = SignatureUtil.getAuthorization("POST", convertProperties.getConvert(), contentMd5, headerDate, convertProperties.getAppid(), convertProperties.getAppsecret()); //签名

        //header参数
        Map<String, String> headers = new LinkedHashMap<>();
        headers.put(HttpHeaders.CONTENT_TYPE, Common.CONTENTTYPE);
        headers.put(HttpHeaders.DATE, headerDate);
        headers.put(HttpHeaders.CONTENT_MD5, contentMd5);//文档上是 "Content-Md5"
        headers.put(HttpHeaders.AUTHORIZATION, authorization);

        // 请求
        String result = HttpUtil.post(convertProperties.getConvert(), headers, JSON.toJSONString(param));
        if (!StringUtils.isEmpty(result)) {
            JSONObject dataJson = JSON.parseObject(result);
            String code = dataJson.get("Code").toString();
            if (code.equals("OK")) {
                //成功，做其它业务处理
            } else {
                String errorMsg = "文件格式转换失败";
                if (dataJson.get("Message") != null) {
                    String message = dataJson.get("Message").toString();
                    errorMsg = errorMsg + message;
                }
                //失败
            }
        }
    }


    @Override
    public void convertCallBack(HttpServletRequest request) {
        try {
            BufferedReader buf = request.getReader();
            String str;
            StringBuilder data = new StringBuilder();
            while ((str = buf.readLine()) != null) {
                data.append(str);
            }
            log.info("文件转换callBask取得data={}", data);
            if (data.length() > 0) {
                JSONObject dataJson = JSON.parseObject(data.toString());
                if (dataJson.get("Code") != null) {
                    String code = (String) dataJson.get("Code");
                    String taskId = (String) dataJson.get("TaskId");
                    String url = getConvertQueryRes(taskId);
                    if (!StringUtils.isEmpty(url) && code.equalsIgnoreCase(HttpStatus.OK.getReasonPhrase())) {
                        //
                        System.out.println(url);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Token getViewUrl(String fileUrl, boolean b) {

        Token t = new Token();

        String fileType = FileUtil.getFileTypeByPath(fileUrl);
        // fileId使用uuid保证出现同样的文件而是最新文件
        UUID randomUUID = UUID.randomUUID();
        String uuid = randomUUID.toString().replace("-", "");

        Map<String, String> values = new HashMap<String, String>() {
            {
                put("_w_appid", wpsProperties.getAppid());
                if (b) {
                    put("_w_tokentype", "1");
                }
                put("_w_userid", "1");
                put("_w_filepath", fileUrl);
                put("_w_filetype", "web");
            }
        };

        String wpsUrl = wpsUtil.getWpsUrl(values, fileType, uuid);

        t.setToken(uuid);
        t.setExpires_in(600);
        t.setWpsUrl(wpsUrl);

        return t;
    }

    /**
     * 文件转换查询
     *
     * @param taskId 任务id，由convertFil接口生成
     */

    @Override
    public String getConvertQueryRes(String taskId) {
        String headerDate = Common.getGMTDate();
        String downLoadUrl = "";
        try {
            //请求参数
            String contentMd5 = Common.getMD5(null); //请求内容数据的MD5值，用null作入参
            String url = convertProperties.getQuery() + "?TaskId=" + taskId + "&AppId=" + convertProperties.getAppid();
            String authorization = SignatureUtil.getAuthorization("GET", url, contentMd5, headerDate, convertProperties.getAppid(), convertProperties.getAppsecret()); //签名

            //header参数
            Map<String, String> headers = new LinkedHashMap<>();
            headers.put(HttpHeaders.CONTENT_TYPE, Common.CONTENTTYPE);
            headers.put(HttpHeaders.DATE, headerDate);
            headers.put(HttpHeaders.CONTENT_MD5, contentMd5);//文档上是 "Content-Md5"
            headers.put(HttpHeaders.AUTHORIZATION, authorization);

            //开始调用
            String result = HttpUtil.get(url, headers);
            if (!StringUtils.isEmpty(result)) {
                JSONObject dataJson = JSON.parseObject(result);
                String code = dataJson.get("Code").toString();
                if (code.equals("OK")) {
                    if (dataJson.get("Urls") != null) { //实际上返回这个参数
                        downLoadUrl = (dataJson.get("Urls")).toString();
                        // 源["xxx"]转换
                        JSONArray jsonArray = JSONArray.parseArray(downLoadUrl);
                        downLoadUrl = jsonArray.get(0).toString();
                    } else if (dataJson.get("Url") != null) {//文档是返回这个参数
                        downLoadUrl = dataJson.get("Url").toString();
                    }
                    //成功
                } else {
                    String errorMsg = "文件格式转换失败";
                    if (dataJson.get("Message") != null) {
                        String message = dataJson.get("Message").toString();
                        errorMsg = errorMsg + message;
                    }
                    //失败
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("recordWPSConvertResult处理出错，错误={}", e.getMessage(), e);
        }
        return downLoadUrl;
    }


}
