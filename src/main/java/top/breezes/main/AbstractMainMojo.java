package top.breezes.main;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Parameter;
import top.breezes.config.handler.GenerateCodeAble;
import top.breezes.config.output.OutputConfigurer;
import top.breezes.config.scanner.ScannerConfigurer;
import top.breezes.config.template.TemplateConfigurer;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;

/**
 * @author yuchengxin <breezes_y@163.com>
 * @date 2021/9/1 20:48
 * @description 抽象核心入口，模板方法，集中处理通用逻辑
 */
public abstract class AbstractMainMojo extends AbstractMojo implements GenerateCodeAble {

    @Parameter
    protected ScannerConfigurer scanner;

    @Parameter
    protected TemplateConfigurer template;

    @Parameter
    protected OutputConfigurer output;

    @Parameter(defaultValue = "${project.build.sourceDirectory}", readonly = true)
    protected File sourceDir;

    /**
     * 执行入口
     * 此方法在插件运行时被调用
     *
     * @throws MojoExecutionException
     * @throws MojoFailureException
     */
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        init();
        printlnLog();
    }

    /**
     * 初始化操作
     */
    private void init() {
        if (null != template && null != template.getGlobal()) {
            LinkedHashMap<String, String> docMap = template.getGlobal().getDoc();
            if (null == docMap || docMap.isEmpty()) {
                return;
            }
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            docMap.put("@date", formatter.format(new Date()));
        }
    }

    /**
     * 初始化日志输出
     */
    private void printlnLog() {
        if (null != scanner) {
            scanner.println();
        }
        if (null != template) {
            template.println();
        }
        if (null != output) {
            output.println();
        }
    }


}
