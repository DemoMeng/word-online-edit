package com.mqz.online.edit.web.controller.wps;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mqz.online.edit.result.WpsResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mqz
 * @since
 * @description 通知回调
 * @abount https://github.com/DemoMeng
 */
@RestController
@RequestMapping("v1/3rd")
@Slf4j
public class NotifyCallBackController{

    /**
     * 回调通知
     */
    @PostMapping("onnotify")
    public ResponseEntity<Object> onNotify(@RequestBody JSONObject obj) {
        log.info("回调通知param:{}", JSON.toJSONString(obj));
        // TODO
        // 返回数据暂不处理
        return WpsResponseBean.SUCCESS();
    }

}
