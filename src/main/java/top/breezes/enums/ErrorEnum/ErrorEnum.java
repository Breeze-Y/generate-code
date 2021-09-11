package top.breezes.enums.ErrorEnum;

/**
 * @author yuchengxin <breezes_y@163.com>
 * @date 2021/9/2 22:33
 * @description
 */
public enum ErrorEnum {

    /**
     * 参数校验
     */
    PARAMETER_CHECK("10000", "Parameter verification failed: {0}"),
    /**
     * 构建错误
     */
    GENERATE_ERROR("10001", "Generate failed: {0}");

    private final String code;

    private final String message;

    ErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
