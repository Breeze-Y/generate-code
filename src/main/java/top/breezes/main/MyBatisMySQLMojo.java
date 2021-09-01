package top.breezes.main;

import org.apache.maven.plugins.annotations.Mojo;

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
    }

}