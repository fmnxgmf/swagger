package com.example.swagger.jwt.commons.utils.ResultUtils;

import lombok.Data;

/**
 * @description:
 * @author: gmf
 * @date: Created in 2020/1/23 16:01
 * @version:
 * @modified By:
 */
public enum  ResultStatus {
    /**
     * 成功
     */
    SUCCESS(100, "成功"),

    /**
     * 无法查询到用户信息
     */
    NOT_ACCOUNT(4000, "无法查询到用户信息"),

    ISOK(200, "成功");
    /**
     * 返回码
     */
    private int code;

    /**
     * 返回结果描述
     */
    private String message;
    ResultStatus(int code,String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
