package com.mqz.online.edit.web.mapper;

import com.mqz.online.edit.web.entity.OnlineEditUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 在线编辑用户表 Mapper 接口
 * </p>
 *
 * @author mqz
 * @since 2020-12-11
 */
public interface OnlineEditUserMapper extends BaseMapper<OnlineEditUser> {

    List<OnlineEditUser> findByIdIn(@Param("idList") List<String> userIdList);
}
