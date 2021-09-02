package top.breezes.config.template;

import top.breezes.commonable.check.CheckAble;
import top.breezes.commonable.log.PrintlnLoggerAble;
import top.breezes.config.template.global.GlobalTemplate;
import top.breezes.config.template.normal.DaoTemplate;
import top.breezes.config.template.normal.NormalTemplate;
import top.breezes.config.template.normal.ServiceTemplate;
import top.breezes.enums.ErrorEnum.ErrorEnum;
import top.breezes.exception.GenerateCodeException;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author yuchengxin <breezes_y@163.com>
 * @date 2021/9/1 20:59
 * @description 模板配置封装类
 */
public class TemplateConfigurer implements PrintlnLoggerAble, CheckAble {

    /**
     * 全局配置
     */
    private GlobalTemplate global;
    /**
     * 其他个性配置
     */
    private NormalTemplate normal;

    /**
     * 日志输出
     */
    @Override
    public void println() {
        LOGGER.info("-------------------------------< Template >-----------------------------");

        if (null != this.global) {
            if (null != this.global.getDoc()) {
                LinkedHashMap<String, String> docMap = global.getDoc();
                if (null != docMap && !docMap.isEmpty()) {
                    for (Map.Entry<String, String> entry : docMap.entrySet()) {
                        LOGGER.info("[Template] doc: @" + entry.getKey() + " " + entry.getValue());
                    }
                }
                LOGGER.info("");
            }
        }

        if (null != this.normal) {
            DaoTemplate daoTemplate = this.normal.getDao();
            if (null != daoTemplate) {
                LOGGER.info("[Template] dao generate: " + daoTemplate.getGenerate());
                LOGGER.info("[Template] dao packages: " + daoTemplate.getPackages());
            }
            LOGGER.info("");

            ServiceTemplate service = this.normal.getService();
            if (null != service) {
                LOGGER.info("[Template] service generate: " + service.getGenerate());
                LOGGER.info("[Template] service packages: " + service.getPackages());
                LOGGER.info("[Template] service needInterface: " + service.getNeedInterface());
            }
        }

        LOGGER.info("");
    }

    /**
     * 校验
     */
    @Override
    public void check() {
        if (null == normal) {
            throw new GenerateCodeException(ErrorEnum.PARAMETER_CHECK, "Template normal is null.");
        }
        if (!normal.isDaoEnabled() && !normal.isServiceEnabled()) {
            throw new GenerateCodeException(
                    ErrorEnum.PARAMETER_CHECK, "Template normal dao and service cannot be all invalid.");
        }
    }

    public GlobalTemplate getGlobal() {
        return global;
    }

    public void setGlobal(GlobalTemplate global) {
        this.global = global;
    }

    public NormalTemplate getNormal() {
        return normal;
    }

    public void setNormal(NormalTemplate normal) {
        this.normal = normal;
    }

}
