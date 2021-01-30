package com.mqz.online.edit.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mqz
 * @since 2020/4/20
 * @description
 * @abount https://github.com/DemoMeng
 */
public class Generator {

    private static void make(String[] tableNames,
                             String authorName,
                             String moduleName,
                             String url,
                             String driverName,
                             String userName,
                             String password){

        String projectPath = System.getProperty("user.dir");

        //============================== 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(projectPath + "/src/main/java")
                // 是否支持 AR
                .setActiveRecord(true)
                //设置作者名字
                .setAuthor(authorName)
                //文件覆盖(全新文件)
                .setFileOverride(true)
                //主键策略
                .setIdType(IdType.AUTO)
                //SQL 映射文件
                .setBaseResultMap(true)
                //SQL 片段
                .setBaseColumnList(true)
                .setOpen(false);
        //============================== 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MARIADB)
                .setUrl(url)
                .setDriverName(driverName)
                .setUsername(userName)
                //.setSchemaName("public")
                .setPassword(password);
        //==============================包配置
        PackageConfig pc = new PackageConfig();
        //配置业务包路径
        pc.setParent("com.mqz.online.edit")
                .setModuleName(moduleName)
                .setEntity("entity")
                .setMapper("mapper")
                .setService("service")
                .setController("controller").setServiceImpl("service.impl");

        //会自动生成 impl，可以不设定
        //============================== 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                System.out.println(tableInfo.getName());
                System.out.println(tableInfo.getEntityName());
                // 自定义输入文件名称
                return projectPath + "/src/main/java/com/mqz/online/edit/"+pc.getModuleName()+"/mapper"
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        //============================== 策略配置
        StrategyConfig strategy = new StrategyConfig();
        //设置命名规则  underline_to_camel 底线变驼峰
        strategy.setNaming(NamingStrategy.underline_to_camel)
                //设置设置列命名  underline_to_camel 底线变驼峰
                .setColumnNaming(NamingStrategy.underline_to_camel)
                //.setSuperEntityClass("com.maoxs.pojo")//设置继承类
                // 设置继承类
                //.setSuperControllerClass("com.maoxs.controller")
                //表格前缀，加了这个实体会去掉前缀
                //.setTablePrefix("t_")
                .setLogicDeleteFieldName("")
                //是否加入lombok
                .setEntityLombokModel(true)
                //设置表名
                .setInclude(tableNames)
                // 设置超级超级列
                //.setSuperEntityColumns("id")
                //设置controller映射联字符
                .setControllerMappingHyphenStyle(true);
        //============================== 生成配置
        AutoGenerator mpg = new AutoGenerator();
        mpg.setCfg(cfg)
                .setTemplate(new TemplateConfig().setXml(null))
                .setGlobalConfig(gc)
                .setDataSource(dsc)
                .setPackageInfo(pc)
                .setStrategy(strategy)
                // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！ (需要导入 freemarker-springboot 的依赖)
                .setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

//    public static void main(String[] args) {
//        /**数据库连接信息-manage*/
//        String manage_url = "jdbc:mysql://rdsv45k6542skme03476.mysql.rds.aliyuncs.com:3306/online_edit?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
//        String manage_driverName = "com.mysql.cj.jdbc.Driver";
//        String manage_userName = "hlw197";
//        String manage_password = "HuLuWa110";
//
//        String authorName = "mqz";
//        //模块名分为 open 和 manage ，open针对开放平台的后台， manage 针对4.0的后台
//        String moduleName = "web";
//        String[] tableNames = new String[]{
//                "online_edit_file",
//                "online_edit_file_version",
//                "online_edit_file_watermark",
//                "online_edit_user_acl",
//                "online_edit_user"
//        };
//        make(tableNames,
//                authorName,
//                moduleName,
//                manage_url,
//                manage_driverName,
//                manage_userName,
//                manage_password);
//    }


}
