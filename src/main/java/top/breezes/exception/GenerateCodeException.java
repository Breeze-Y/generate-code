package top.breezes.exception;

import top.breezes.enums.ErrorEnum.ErrorEnum;

import java.text.MessageFormat;

/**
 * @author yuchengxin <breezes_y@163.com>
 * @date 2021/9/2 22:28
 * @description 自定义异常
 */
public class GenerateCodeException extends RuntimeException {

    private String code;

    private String message;

    public GenerateCodeException() {
        super();
    }


    public GenerateCodeException(ErrorEnum errorEnum) {
        super(errorEnum.getMessage());
        this.code = errorEnum.getCode();
        this.message = errorEnum.getMessage();
    }

    public GenerateCodeException(ErrorEnum errorEnum, Object... customize) {
        super(MessageFormat.format(errorEnum.getMessage(), customize));
        this.code = errorEnum.getCode();
        this.message = super.getMessage();
    }

    public GenerateCodeException(ErrorEnum errorEnum, Throwable cause) {
        super(errorEnum.getMessage(), cause);
        this.code = errorEnum.getCode();
        this.message = errorEnum.getMessage();
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
