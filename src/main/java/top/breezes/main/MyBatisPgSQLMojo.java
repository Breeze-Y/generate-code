package top.breezes.main;

import com.thoughtworks.qdox.model.JavaClass;
import freemarker.template.Template;
import org.apache.maven.plugins.annotations.Mojo;
import top.breezes.model.MapperFile;
import top.breezes.model.pgsql.PgsqlMapperXmlFile;
import top.breezes.model.pgsql.PgsqlScriptFile;
import top.breezes.utils.FreemarkerUtils;
import top.breezes.utils.ParseUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author yuchengxin <breezes_y@163.com>
 * @date 2021/9/1 21:15
 * @description mybatis+mysql的模板生成器
 */
@Mojo(name = "mybatis-pgsql")
public class MyBatisPgSQLMojo extends AbstractMainMojo {

    /**
     * 代码生成逻辑
     */
    @Override
    public void generate() {
        getLog().info("-------------------< Generate [ mybatis PostgreSQL ] >------------------");

        ParseUtil parseUtil = new ParseUtil(scanner, sourceDir);
        List<JavaClass> classes = parseUtil.getClasses();
        if (classes.isEmpty()) {
            getLog().info("No valid class was found, the plugin stop running.");
            return;
        }

        /*generate mappe file*/
        if (template.getNormal().getDao().getGenerate()) {
            List<MapperFile> mapperFiles = buildMapperTemplateParams(classes);
            Template mapper = FreemarkerUtils.getTemplate("mapper.ftl");
            for (MapperFile mapperFile : mapperFiles) {
                try {
                    FreemarkerUtils.run(mapper, mapperFile.getFilePath(), mapperFile);
                } catch (IOException e) {
                    getLog().error("Generate file " + mapperFile.getClassName() + " error.");
                }
            }

            List<PgsqlMapperXmlFile> xmlFiles = buildMapperXmlParams(classes);
            Template mysql = FreemarkerUtils.getTemplate("pgsql_mapper_xml.ftl");

            for (PgsqlMapperXmlFile xmlFile : xmlFiles) {
                try {
                    FreemarkerUtils.run(mysql, xmlFile.getFilePath(), xmlFile);
                } catch (IOException e) {
                    getLog().error("Generate file " + xmlFile.getFileName() + " error.");
                }
            }

            Template mysqlScript = FreemarkerUtils.getTemplate("pgsql_sql_script.ftl");
            List<PgsqlScriptFile> scriptFiles = buildSqlScriptParams(classes);
            for (PgsqlScriptFile scriptFile : scriptFiles) {
                try {
                    FreemarkerUtils.run(mysqlScript, scriptFile.getFilePath(), scriptFile);
                } catch (IOException e) {
                    getLog().error("Generate file " + scriptFile.getFileName() + " error.");
                }
            }
        }

    }

    private List<PgsqlScriptFile> buildSqlScriptParams(List<JavaClass> classes) {
        return classes.parallelStream()
                .map(javaClass -> new PgsqlScriptFile(javaClass, output.getBaseDir()
                        + File.separator + "pgsql"))
                .collect(Collectors.toList());
    }

    private List<PgsqlMapperXmlFile> buildMapperXmlParams(List<JavaClass> classes) {
        String packages = template.getNormal().getDao().getPackages();
        return classes.parallelStream()
                .map(javaClass -> new PgsqlMapperXmlFile(packages, javaClass, output.getBaseDir()
                        + File.separator + "pgsql"))
                .collect(Collectors.toList());
    }

    private List<MapperFile> buildMapperTemplateParams(List<JavaClass> classList) {
        String packages = template.getNormal().getDao().getPackages();
        List<String> docList = buildDocListByMap();
        return classList.parallelStream()
                .map(entity -> new MapperFile(packages, docList, entity,
                        output.getBaseDir() + File.separator + "pgsql"))
                .collect(Collectors.toList());
    }


}