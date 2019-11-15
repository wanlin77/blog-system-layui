package com.wl.mp;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class MybatisPlusGenerator {
    private static final String DATA_URL = "jdbc:mysql://localhost:3306/blog_system_dev?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&characterSetResults=utf8";
    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static final String PARENT_PACKAGE_NAME = "com.wl.mp";
    private static final String MODULE_NAME = "sys";
    private static final String TABLE_PREFIX = "tb";
    private static final String AUTHOR = "wanlin";

    public static void main(String[] args) {
        // 表名
        String [] tableNames = new String[]{"tb_admin_user", "tb_blog_config", "tb_blog_category",
                "tb_blog_comment", "tb_blog_info", "tb_blog_link", "tb_blog_tag", "tb_blog_tag_relation"};

        // 全局配置
        GlobalConfig globalConfig = getGlobalConfig();

        // 数据源配置
        DataSourceConfig dataSourceConfig = getDataSourceConfig();

        // 包配置
        PackageConfig packageConfig = getPackageConfig();

        // 策略配置
        StrategyConfig strategyConfig = getStrategyConfig(tableNames);

        new AutoGenerator()
                .setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setPackageInfo(packageConfig)
                .setStrategy(strategyConfig)
                .execute();
    }

    // 全局配置
    private static GlobalConfig getGlobalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        String projectPath = System.getProperty("user.dir"); // 当前工程路径
        globalConfig.setOutputDir(projectPath + "/mybatis-plus-generator/src/main/java"); // 生成路径
        globalConfig.setAuthor(AUTHOR); // 作者
        globalConfig.setBaseResultMap(true);
        globalConfig.setBaseColumnList(true);
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        globalConfig.setServiceName("I%sService");
        globalConfig.setFileOverride(true); // 覆盖生成的文件
        //globalConfig.setIdType(IdType.UUID); // 生成的主键的ID类型
        //globalConfig.setDateType(DateType.ONLY_DATE); // 时间类型对应策略
        globalConfig.setOpen(false); // 是否打开输出目录
        //globalConfig.setEnableCache(true); // 是否在xml中添加二级缓存配置
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
    private static PackageConfig getPackageConfig() {
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(PARENT_PACKAGE_NAME)
                .setModuleName(MODULE_NAME);
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

}
