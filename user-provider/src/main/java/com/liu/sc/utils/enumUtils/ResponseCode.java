package com.liu.sc.utils.enumUtils;

public enum  ResponseCode {

    SUCCESS(200,"success"),
    FAIL(300,"FAIL"),
    VALID_FAIL_STAUTS(400,"参数校验失败");

    private final Integer status;
    private final String message;

    ResponseCode(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
