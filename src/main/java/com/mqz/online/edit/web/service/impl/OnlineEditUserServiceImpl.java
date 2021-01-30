package com.mqz.online.edit.web.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mqz.online.edit.web.entity.OnlineEditUser;
import com.mqz.online.edit.web.mapper.OnlineEditUserMapper;
import com.mqz.online.edit.web.service.IOnlineEditUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 在线编辑用户表 服务实现类
 * </p>
 *
 * @author mqz
 * @since 2020-12-11
 */
@Service
public class OnlineEditUserServiceImpl extends ServiceImpl<OnlineEditUserMapper, OnlineEditUser> implements IOnlineEditUserService {

    @Override
    public List<OnlineEditUser> findByIdIn(List<String> userIdList) {
        return baseMapper.findByIdIn(userIdList);
    }

    @Override
    public Map<String, Object> userInfo(JSONObject reqObj) {
        List<String> ids = null;

        if (reqObj != null) {
            if (reqObj.containsKey("ids")) {
                ids = JSONArray.parseArray(reqObj.getString("ids"), String.class);
            }
        }
        Map<String, Object> map = new HashMap<>();
        List<OnlineEditUser> users = new ArrayList<>();
        if (ids != null && !ids.isEmpty()) {
            OnlineEditUser user;
            for (String id : ids) {
                user = getById(id);
                if (user != null) {
                    users.add(user);
                }
            }
        }
        map.put("users", users);
        map.put("code", 200);
        map.put("msg", "ok");
        map.put("status", 200);
        return map;
    }



}
