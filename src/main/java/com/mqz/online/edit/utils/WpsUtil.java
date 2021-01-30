package com.mqz.online.edit.utils;

import com.mqz.online.edit.config.properties.WpsProperties;
import com.mqz.online.edit.utils.file.FileUtil;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author mqz
 * @since 
 * @description
 * @abount https://github.com/DemoMeng
 */
@Component
public class WpsUtil {

    @Resource
    private WpsProperties wpsProperties;

    public String getWpsUrl(Map<String, String> values, String fileType, String fileId) {
        String keyValueStr = SignatureUtil.getKeyValueStr(values);
        String signature = SignatureUtil.getSignature(values, wpsProperties.getAppsecret());
        String fileTypeCode = FileUtil.getFileTypeCode(fileType);

        return wpsProperties.getDomain() + fileTypeCode + "/" + fileId + "?"
                + keyValueStr + "_w_signature=" + signature;
    }

    public String getTemplateWpsUrl(String fileType, String userId) {
        Map<String, String> values = new HashMap<String, String>() {
            {
                put("_w_appid", wpsProperties.getAppid());
                put("_w_userid", userId);
            }
        };
        String keyValueStr = SignatureUtil.getKeyValueStr(values);
        String signature = SignatureUtil.getSignature(values, wpsProperties.getAppsecret());
        String fileTypeCode = FileUtil.getTypeCode(fileType);

        return wpsProperties.getDomain() + fileTypeCode + "/new/0" + "?"
                + keyValueStr + "_w_signature=" + signature;
    }

    @Getter
    public enum UserPic{

        ZERO(0,"http://api-new.oss-cn-hangzhou.aliyuncs.com/bear.png"),
        ONE(1,"http://api-new.oss-cn-hangzhou.aliyuncs.com/chicken.png"),
        TWO(2,"http://api-new.oss-cn-hangzhou.aliyuncs.com/dog.png"),
        THREE(3,"http://api-new.oss-cn-hangzhou.aliyuncs.com/elephant.png"),
        FOUR(4,"http://api-new.oss-cn-hangzhou.aliyuncs.com/fox.png"),
        FIVE(5,"http://api-new.oss-cn-hangzhou.aliyuncs.com/lion.png"),
        SIX(6,"http://api-new.oss-cn-hangzhou.aliyuncs.com/monkey.png"),
        SEVEN(7,"http://api-new.oss-cn-hangzhou.aliyuncs.com/niu.png"),
        EIGHT(8,"http://api-new.oss-cn-hangzhou.aliyuncs.com/pig.png"),
        NINE(9,"http://api-new.oss-cn-hangzhou.aliyuncs.com/wa.png"),
        TEN(10,"http://api-new.oss-cn-hangzhou.aliyuncs.com/cat.png");

        public Integer flag;
        public String pic;

        UserPic(Integer flag, String pic) {
            this.flag = flag;
            this.pic = pic;
        }

        public static UserPic getByKey(Integer flag) {
            for(UserPic s : values()) {
                if (s.getFlag().equals(flag)) {
                    return s;
                }
            }
            return null;
        }
    }

    /***
     * 随机头像
     * @return
     */
    public static String getPic(){
        UserPic up = UserPic.getByKey(new Random().nextInt(11));
        if(up == null){
            return UserPic.getByKey(1).getPic();
        }
        return up.getPic();
    }

}
