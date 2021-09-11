package top.breezes.utils;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Objects;

/**
 * @author yuchengxin
 * @date 2021/4/2 16:23
 */
public class FreemarkerUtils {

    private final static Configuration CONFIGURATION;

    private final static Log logger;

    static {
        //创建一个Configuration对象
        CONFIGURATION = new Configuration(Configuration.getVersion());
        // 告诉config对象模板文件存放的路径。
        CONFIGURATION.setTemplateLoader(new ClassTemplateLoader(FreemarkerUtils.class, "/template"));
        // 设置config的默认字符集。一般是utf-8
        CONFIGURATION.setDefaultEncoding("utf-8");

        logger = new SystemStreamLog();
    }

    public static Template getTemplate(String templateName) {
        try {
            return CONFIGURATION.getTemplate(templateName);
        } catch (IOException e) {
            throw new RuntimeException("Get Template error", e);
        }
    }

    public static void run(Template template, String target, Object parameter) throws IOException {
        Writer out = null;
        try {
            File file = new File(target);
            if (file.exists()) {
                logger.info(logger + " is exists");
                return;
            }
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            out = new FileWriter(file);
            template.process(parameter, out);
        } catch (Exception e) {
            if (Objects.nonNull(out)) {
                out.flush();
                out.close();
            }
        }
    }

    public static void run(String templateName, String target, Object parameter) throws IOException {
        Template template = getTemplate(templateName);
        Writer out = null;
        try {
            File file = new File(target);
            if (file.exists()) {
                logger.info(logger + " is exists");
                return;
            }
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            out = new FileWriter(file);
            template.process(parameter, out);
        } catch (Exception e) {
            if (Objects.nonNull(out)) {
                out.flush();
                out.close();
            }
        }
    }

}
