## 在线编辑文档

- 调用wps的开放接口文档，官方文档：https://open.wps.cn/docs/wwo/access/api-list#des1

- 使用步骤

  - 执行 online_edit.sql 生成数据库表结构及数据

  - 配置 application.yml 参数：

      

    ```yml
    #wps回调的域名地址 (就是本项目需要部署到的外网上，wps保存文档等会回调项目接口)
    server:
      domain: http://xxx.com
    
    # wps edit 在线编辑配置
    wps:
      domain: https://wwo.wps.cn/office/
      appid: you appid
      appsecret: you appsecret
      # wps convert  文档转换配置
      convert:
        appid: you appid
        appsecret: you appsecret
        convert: https://dhs.open.wps.cn/pre/v1/convert
        query: https://dhs.open.wps.cn/pre/v1/query
        
    #oss配置
    oss:
      file_url_prefix: xxxxxx
      bucket_name: xxxx
      disk_name: xxxx
      region_id: xxx
      endpoint: xxxxxx
      access_key: xxxxx
      access_secret: xxxxxx
    
    ```

  - 部署项目到服务器，并且配置wps回调地址![image-20201212115314831](https://github.com/DemoMeng/online-edit/blob/master/images/image-20201212115314831.png)
  - 执行请求获取到wps在线访问链接： http://xxxx/web/online-edit-file/getViewUrlDbPath?fileId=fb977165d0f04d2fae4743834fa25cf1&userId=1  这里的就是wps管理台数据回调地址的项目，就是下面的wpsUrl，其中对参数进行了签名设置

    ```
    
    {
        "msg": "ok",
        "code": 200,
        "data": {
            "expires_in": 600,
            "token": "c0f7c6ae27bc470fa0cd544d6652eff0",
            "wpsUrl": "http://xxxx/web/online-edit-file/getViewUrlDbPath?fileId=fb977165d0f04d2fae4743834fa25cf1&userId=1"
        },
        "status": 200
    }
    
    ```

  - 打开链接：

     ![image-20201212120058667](https://github.com/DemoMeng/online-edit/blob/master/images/image-20201212120058667.png)
              

​		   
