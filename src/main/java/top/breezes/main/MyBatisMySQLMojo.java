package top.breezes.main;

import com.thoughtworks.qdox.model.JavaClass;
import org.apache.maven.plugins.annotations.Mojo;
import top.breezes.utils.ParseUtil;

import java.util.List;


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
    }

}