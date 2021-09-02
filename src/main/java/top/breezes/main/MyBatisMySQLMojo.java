package top.breezes.main;

import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugins.annotations.Mojo;
import top.breezes.enums.ErrorEnum.ErrorEnum;
import top.breezes.exception.GenerateCodeException;

import java.util.ArrayList;
import java.util.Collection;
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

        List<String> beans = scanner.getBeans();
        JavaProjectBuilder builder = new JavaProjectBuilder();
        builder.addSourceTree(this.sourceDir);

        List<JavaClass> classList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(beans)) {
            classList.addAll(beans.stream()
                    .map(builder::getClassByName)
                    .collect(Collectors.toList()));
        }

        if (StringUtils.isNotBlank(scanner.getBasePackage())) {
            Collection<JavaClass> allClassList = builder.getClasses();
            if (CollectionUtils.isEmpty(allClassList)) {
                throw new GenerateCodeException(ErrorEnum.GENERATE_ERROR,
                        "The sourceDir error, place check you maven project.");
            }

            classList.addAll(allClassList.parallelStream()
                    .filter(javaClass -> StringUtils.equals(javaClass.getPackageName(), scanner.getBasePackage()))
                    .collect(Collectors.toList()));

        }

        // todo classList 去重

        if (CollectionUtils.isEmpty(classList)) {
            getLog().info("No valid class was found, the plugin stop running.");
            return;
        }

        for (int i = 0; i < classList.size(); i++) {
            getLog().info("class[" + i + "]" + classList.get(i).getGenericFullyQualifiedName());
        }
    }

}