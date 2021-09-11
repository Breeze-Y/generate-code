package top.breezes.utils;

import com.google.common.collect.Lists;
import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;
import top.breezes.config.scanner.ScannerConfigurer;
import top.breezes.enums.ErrorEnum.ErrorEnum;
import top.breezes.exception.GenerateCodeException;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yuchengxin <breezes_y@163.com>
 * @date 2021/9/11 10:47
 * @description 扫描器解析工具类
 */
public class ParseUtil {

    private ScannerConfigurer scanner;

    private File sourceDir;

    private JavaProjectBuilder builder;

    private static final Log LOGGER = new SystemStreamLog();


    public ParseUtil(ScannerConfigurer scannerConfigurer, File sourceDir) {
        this.scanner = scannerConfigurer;
        this.sourceDir = sourceDir;
        this.builder = createJavaProjectBuilder();
    }

    /**
     * 获取JavaClass
     *
     * @return Set<JavaClass>
     */
    public List<JavaClass> getClasses() {
        List<JavaClass> classesByBeansName = getClassesByBeansName();
        List<JavaClass> classesByBasePackage = getClassesByBasePackagePath();

        Set<JavaClass> classSet = new TreeSet<>(Comparator.comparing(JavaClass::getGenericFullyQualifiedName));
        classSet.addAll(classesByBeansName);
        classSet.addAll(classesByBasePackage);

        if (CollectionUtils.isEmpty(classSet)) {
            return Collections.emptyList();
        }

        for (JavaClass entity : classSet) {
            LOGGER.info(entity.getFullyQualifiedName());
        }
        return new ArrayList<>(classSet);
    }

    /**
     * 根据base package获取项目中的java class
     *
     * @return List<JavaClass>
     */
    private List<JavaClass> getClassesByBasePackagePath() {
        if (StringUtils.isBlank(scanner.getBasePackage())) {
            return Lists.newArrayList();
        }
        Collection<JavaClass> allClassList = this.builder.getClasses();
        if (CollectionUtils.isEmpty(allClassList)) {
            throw new GenerateCodeException(ErrorEnum.GENERATE_ERROR,
                    "The sourceDir error, place check you maven project.");
        }

        return allClassList.parallelStream()
                .filter(javaClass -> StringUtils.startsWith(javaClass.getPackageName(), scanner.getBasePackage()))
                .collect(Collectors.toList());
    }

    /**
     * 根据beans name获取项目中的java class
     *
     * @return
     */
    private List<JavaClass> getClassesByBeansName() {
        if (CollectionUtils.isEmpty(scanner.getBeans())) {
            return Lists.newArrayList();
        }
        return scanner.getBeans().stream()
                .map(this.builder::getClassByName)
                .collect(Collectors.toList());
    }

    /**
     * 获取java解析对象
     *
     * @return {@link JavaProjectBuilder}
     */
    private JavaProjectBuilder createJavaProjectBuilder() {
        if (null == sourceDir) {
            throw new GenerateCodeException(ErrorEnum.PARAMETER_CHECK, "sourceDir cannot be null.");
        }
        JavaProjectBuilder builder = new JavaProjectBuilder();
        builder.addSourceTree(sourceDir);
        return builder;
    }

    public ScannerConfigurer getScanner() {
        return scanner;
    }

    public File getSourceDir() {
        return sourceDir;
    }

    public JavaProjectBuilder getBuilder() {
        return builder;
    }
}
