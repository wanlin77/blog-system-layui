package com.wl.mp;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;
import java.sql.Driver;

/**
 * @description: 多模块中，MyBatis Plus生成代码到各自模块
 * @author: wanlin
 * @version: 1.0
 * @createtime: 2019/9/29 16:27
 */
public class MyBatisPlusGeneratorForMultiModule {
    private static final String TABLE_PREFIX = "tb";
    private static final String AUTHOR = "wanlin";

    private static final String DATA_URL = "jdbc:mysql://localhost:3306/blog_system_dev?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&characterSetResults=utf8";
    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";



    // 各包名
    private static final String PACKAGE_ENTITY_NAME = "blog-system-entity.src.main.java.com.wl.bs.entity.sys";
    private static final String PACKAGE_DAO_NAME = "blog-system-dao.src.main.java.com.wl.bs.mapper.sys";
    private static final String PACKAGE_MAPPER_XML_NAME = "blog-system-dao.src.resources.mapper.sys";
    private static final String PACKAGE_SERVICE_NAME = "blog-system-service.src.main.java.com.wl.bs.service.sys";
    private static final String PACKAGE_SERVICE_IMPL_NAME = "blog-system-service-impl.src.main.java.com.wl.bs.serviceimpl.sys";
    private static final String PACKAGE_CONTROLLER_NAME = "blog-system-controller.src.main.java.com.wl.bs.controller.sys";

    private static final String PACKAGE_NAME = "sys";

    public static void main(String[] args) {
        // 表名
        String [] tableNames = new String[]{"tb_admin_user", "tb_blog_config"};

        // 项目模块名
        String [] modules = new String[]{"blog-system-entity", "blog-system-dao", "blog-system-dao-xml",
                "blog-system-service", "blog-system-service-impl", "blog-system-controller"};
        for (String module : modules) {
            moduleGenerator(module,tableNames);
        }
    }

    private static void moduleGenerator(String module,String[] tableNames){
        // 全局配置
        GlobalConfig globalConfig = getGlobalConfig(module);

        // 数据源配置
        DataSourceConfig dataSourceConfig = getDataSourceConfig();

        // 包配置
        PackageConfig packageConfig = getPackageConfig(module);

        // 策略配置
        StrategyConfig strategyConfig = getStrategyConfig(tableNames);

        // 配置模板
        TemplateConfig templateConfig = getTemplateConfig(module);

        new AutoGenerator()
                .setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setPackageInfo(packageConfig)
                .setStrategy(strategyConfig)
                .setTemplate(templateConfig)
                .execute();

    }

    // 全局配置
    private static GlobalConfig getGlobalConfig(String module) {
        GlobalConfig globalConfig = new GlobalConfig();
        String projectPath = System.getProperty("user.dir"); // 当前工程路径
        globalConfig.setOutputDir(projectPath + "/" + module + "/src/main/java"); // 生成路径
        if ("blog-system-dao-xml".equals(module)) {
            globalConfig.setOutputDir(projectPath + "/" + "blog-system-dao" + "/src/main/resources");
        }
        globalConfig.setAuthor(AUTHOR); // 作者
        globalConfig.setBaseResultMap(true);
        globalConfig.setBaseColumnList(true);
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        globalConfig.setServiceName("I%sService");
        globalConfig.setFileOverride(true); // 覆盖生成的文件
        //config.setIdType(IdType.UUID); // 生成的主键的ID类型
        //config.setDateType(DateType.ONLY_DATE); // 时间类型对应策略
        //config.setOpen(true); // 是否打开输出目录
        //config.setEnableCache(true); // 是否在xml中添加二级缓存配置
        return globalConfig;
    }

    // 数据源配置
    private static DataSourceConfig getDataSourceConfig() {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setUrl(DATA_URL);
        dataSourceConfig.setDriverName(DRIVER_CLASS_NAME);
        dataSourceConfig.setUsername(USERNAME);
        dataSourceConfig.setPassword(PASSWORD);
        return dataSourceConfig;
    }

    // 包名配置
    private static PackageConfig getPackageConfig(String module) {
        PackageConfig packageConfig = new PackageConfig();
        String packageName = "";
        switch (module) {
            case "blog-system-entity":
                packageName = "com.wl.bs.entity";
                break;
            case "blog-system-dao":
                packageName = "com.wl.bs.dao";
                break;
            case "blog-system-dao-xml":
                packageName = "mapper";
                break;
            case "blog-system-service":
                packageName = "com.wl.bs.service";
                break;
            case "blog-system-service-impl":
                packageName = "com.wl.bs.serviceimpl";
                break;
            case "blog-system-controller":
                packageName = "com.wl.bs.controller";
                break;
            default:
                    break;
        }
        packageConfig.setParent(packageName)
                .setEntity(PACKAGE_NAME)
                .setMapper(PACKAGE_NAME)
                .setXml(PACKAGE_NAME)
                .setService(PACKAGE_NAME)
                .setServiceImpl(PACKAGE_NAME)
                .setController(PACKAGE_NAME);

        return packageConfig;
    }

    // 策略配置
    private static StrategyConfig getStrategyConfig(String[] tableNames) {
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setInclude(tableNames);
        strategy.setEntityTableFieldAnnotationEnable(true);
        strategy.setTablePrefix(TABLE_PREFIX);
        strategy.setEntityLombokModel(true);
        return strategy;
    }

    private static TemplateConfig getTemplateConfig(String module) {
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setEntity(null)
                .setMapper(null)
                .setXml(null)
                .setService(null)
                .setServiceImpl(null)
                .setController(null);
        switch (module) {
            case "blog-system-entity":
                templateConfig.setEntity(new TemplateConfig().getEntity(false));
                break;
            case "blog-system-dao":
                templateConfig.setMapper(new TemplateConfig().getMapper());
                break;
            case "blog-system-dao-xml":
                templateConfig.setXml(new TemplateConfig().getXml());
                break;
            case "blog-system-service":
                templateConfig.setService(new TemplateConfig().getService());
                break;
            case "blog-system-service-impl":
                templateConfig.setServiceImpl(new TemplateConfig().getServiceImpl());
                break;
            case "blog-system-controller":
                templateConfig.setController(new TemplateConfig().getController());
                break;
            default:
                throw new IllegalArgumentException("参数匹配错误，请检查");
        }
        return templateConfig;
    }


}
