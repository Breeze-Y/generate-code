package top.breezes.config.log;

import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;

/**
 * @author yuchengxin <breezes_y@163.com>
 * @date 2021/9/1 21:54
 * @description
 */
public interface PrintlnLoggerAble {

    static final Log LOGGER = new SystemStreamLog();

    /**
     * 日志输出
     */
    void println();
}
