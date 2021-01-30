package com.mqz.online.edit.web.service;

import com.alibaba.fastjson.JSONObject;
import com.mqz.online.edit.web.entity.OnlineEditUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 在线编辑用户表 服务类
 * </p>
 *
 * @author mqz
 * @since 2020-12-11
 */
public interface IOnlineEditUserService extends IService<OnlineEditUser> {

    List<OnlineEditUser> findByIdIn(List<String> userIdList);

    Map<String, Object> userInfo(JSONObject reqObj);

}
