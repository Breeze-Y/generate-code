package top.breezes.config.scanner;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import top.breezes.commonable.check.CheckAble;
import top.breezes.commonable.log.PrintlnLoggerAble;
import top.breezes.enums.ErrorEnum.ErrorEnum;
import top.breezes.exception.GenerateCodeException;

import java.util.List;

/**
 * @author yuchengxin <breezes_y@163.com>
 * @date 2021/9/1 20:52
 * @description Bean扫描器封装
 */
public class ScannerConfigurer implements PrintlnLoggerAble, CheckAble {

    /**
     * 基础包路径，用于包扫描
     * <p>
     * example: com.breezes.bean
     * </p>
     */
    private String basePackage;

    /**
     * 指定类的全限定名，用来存储指定处理的类
     * <p>
     * example: com.breezes.bean.Hello
     * </p>
     */
    private List<String> beans;

    /**
     * 校验
     */
    @Override
    public void check() {
        if (StringUtils.isBlank(basePackage) && CollectionUtils.isEmpty(beans)) {
            throw new GenerateCodeException(
                    ErrorEnum.PARAMETER_CHECK, "Scanner basePackage and beans cannot be all blank.");
        }
    }

    /**
     * 日志输出
     */
    @Override
    public void println() {
        LOGGER.info("-------------------------------< Scanner >------------------------------");

        if (StringUtils.isNotBlank(this.getBasePackage())) {
            LOGGER.info("[Scanner] base package: " + this.getBasePackage());
        }
        LOGGER.info("");

        if (CollectionUtils.isNotEmpty(this.getBeans())) {
            for (int i = 0; i < this.getBeans().size(); i++) {
                LOGGER.info("[Scanner] beans[" + i + "]: " + this.getBeans().get(i));
            }
        }

        LOGGER.info("");
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public List<String> getBeans() {
        return beans;
    }

    public void setBeans(List<String> beans) {
        this.beans = beans;
    }


}
