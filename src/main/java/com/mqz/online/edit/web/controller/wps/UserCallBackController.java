package com.mqz.online.edit.web.controller.wps;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mqz.online.edit.result.WpsResponseBean;
import com.mqz.online.edit.web.service.IOnlineEditUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author mqz
 * @since
 * @description 用户回调
 * @abount https://github.com/DemoMeng
 */
@RestController
@RequestMapping("v1/3rd/user")
@Slf4j
public class UserCallBackController{

    @Resource
    private IOnlineEditUserService userTService;

    /**
     * 获取用户信息
     */
    @PostMapping("info")
    public Object userInfo(@RequestBody JSONObject reqObj) {
        log.info("获取用户信息param:{}", JSON.toJSON(reqObj));
        try {
            Map<String, Object> map = userTService.userInfo(reqObj);
            return JSONUtil.toJsonStr(map);
            //return WpsResponseBean.SUCCESS(map);
        } catch (Exception e) {
            return WpsResponseBean.ERROR("获取用户信息异常");
        }
    }

}
