package bml.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

/**
 * @author 月影
 * Date 2020/3/21 14:49
 */
public class GeneratorCode {
    public static void main(String[] args) {
        AutoGenerator autoGenerator = new AutoGenerator();
        //全局配置
        GlobalConfig config = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        config.setOutputDir(projectPath+"/src/main/java");
        config.setAuthor("月影");
        config.setOpen(false);
        //是否覆盖
        config.setFileOverride(true);
        config.setServiceName("%sService");
        config.setIdType(IdType.AUTO);
        config.setDateType(DateType.ONLY_DATE);
        config.setSwagger2(true);
        autoGenerator.setGlobalConfig(config);

        //数据源配置
        DataSourceConfig sourceConfig = new DataSourceConfig();
        sourceConfig.setUrl("jdbc:mysql://127.0.0.1:3306/design?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false");
        sourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        sourceConfig.setUsername("root");
        sourceConfig.setPassword("123456");
        sourceConfig.setDbType(DbType.MYSQL);
        autoGenerator.setDataSource(sourceConfig);

        //包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("bml");
        packageConfig.setEntity("entity");
        packageConfig.setMapper("mapper");
        packageConfig.setService("service");
        packageConfig.setController("controller");
        autoGenerator.setPackageInfo(packageConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        //设置要映射的表名
        strategy.setInclude("bml_permission");
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setLogicDeleteFieldName("deleted");

        //自动填充配置
        TableFill createTime = new TableFill("create_time", FieldFill.INSERT);
        TableFill updateTime = new TableFill("update_Time", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> list = new ArrayList<>();
        list.add(createTime);
        list.add(updateTime);
        strategy.setTableFillList(list);

        //乐观锁
        /*strategy.setVersionFieldName("version");*/

        strategy.setRestControllerStyle(true);
        autoGenerator.setStrategy(strategy);
        autoGenerator.execute();

    }
}
