package top.breezes.config.output;

import org.apache.commons.lang3.StringUtils;
import top.breezes.commonable.check.CheckAble;
import top.breezes.commonable.log.PrintlnLoggerAble;
import top.breezes.enums.ErrorEnum.ErrorEnum;
import top.breezes.exception.GenerateCodeException;

/**
 * @author yuchengxin <breezes_y@163.com>
 * @date 2021/9/1 21:05
 * @description 输出配置封装
 */
public class OutputConfigurer implements PrintlnLoggerAble, CheckAble {

    /**
     * 指定生成的代码的输出目录
     * <p>
     * example: D:/generate-code/temp/
     * </p>
     */
    private String baseDir;

    /**
     * 校验
     */
    @Override
    public void check() {
        if (StringUtils.isBlank(baseDir)) {
            throw new GenerateCodeException(
                    ErrorEnum.PARAMETER_CHECK, "Output baseDir cannot be blank.");
        }
    }

    /**
     * 日志输出
     */
    @Override
    public void println() {
        LOGGER.info("--------------------------------< Output >------------------------------");

        if (StringUtils.isNotBlank(this.baseDir)) {
            LOGGER.info("[Output] base dir: " + this.baseDir);
        }

        LOGGER.info("");
    }

    public String getBaseDir() {
        return baseDir;
    }

    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
    }
}
