package top.breezes.main;

import com.thoughtworks.qdox.model.JavaClass;
import freemarker.template.Template;
import org.apache.maven.plugins.annotations.Mojo;
import top.breezes.model.MapperFile;
import top.breezes.model.MysqlMapperXmlFile;
import top.breezes.utils.FreemarkerUtils;
import top.breezes.utils.ParseUtil;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author yuchengxin <breezes_y@163.com>
 * @date 2021/9/1 21:15
 * @description mybatis+mysql的模板生成器
 */
@Mojo(name = "mybatis-mysql")
public class MyBatisMySQLMojo extends AbstractMainMojo {

    /**
     * 代码生成逻辑
     */
    @Override
    public void generate() {
        getLog().info("---------------------< Generate [ mybatis mysql ] >---------------------");

        ParseUtil parseUtil = new ParseUtil(scanner, sourceDir);
        List<JavaClass> classes = parseUtil.getClasses();
        if (classes.isEmpty()) {
            getLog().info("No valid class was found, the plugin stop running.");
            return;
        }

        /*generate mappe file*/
        if (template.getNormal().getDao().getGenerate()) {
            List<MapperFile> mapperFiles = buildMapperTemplateParams(classes);
            Template mapper = null;
            for (MapperFile mapperFile : mapperFiles) {
                try {
                    mapper = FreemarkerUtils.getTemplate("mapper.ftl");
                    FreemarkerUtils.run(mapper, mapperFile.getFilePtah(), mapperFile);
                } catch (IOException e) {
                    getLog().error("Generate file " + mapperFile.getClassName() + " error.");
                }
            }

            List<MysqlMapperXmlFile> xmlFiles = buildMapperXmlParams(classes);
            Template mysql = null;
            for (MysqlMapperXmlFile xmlFile : xmlFiles) {
                try {
                    mysql = FreemarkerUtils.getTemplate("mysql_mapper_xml.ftl");
                    FreemarkerUtils.run(mysql, xmlFile.getFilePtah(), xmlFile);
                } catch (IOException e) {
                    getLog().error("Generate file " + xmlFile.getFileName() + " error.");
                }
            }
        }

    }

    private List<MysqlMapperXmlFile> buildMapperXmlParams(List<JavaClass> classes) {
        String packages = template.getNormal().getDao().getPackages();
        return classes.parallelStream()
                .map(javaClass -> new MysqlMapperXmlFile(packages, javaClass, output.getBaseDir()))
                .collect(Collectors.toList());
    }

    private List<MapperFile> buildMapperTemplateParams(List<JavaClass> classList) {
        String packages = template.getNormal().getDao().getPackages();
        List<String> docList = buildDocListByMap();
        return classList.parallelStream()
                .map(entity -> new MapperFile(packages, docList, entity, output.getBaseDir()))
                .collect(Collectors.toList());
    }


}