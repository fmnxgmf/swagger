package com.example.swagger.jwt.commons.utils.ResultUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: gmf
 * @date: Created in 2020/1/23 15:51
 * @version:
 * @modified By:
 */
@Data
public class ResultModel implements Serializable {
    //定义jackson对象
    private static final ObjectMapper mapper = new ObjectMapper();

    //响应业务状态码
    private Integer code;

    // 响应消息
    private String msg;

    /**
     * 返回数据量
     */
    private Long count;


    // 返回内容
    private Object content;

    public ResultModel(){}

    public ResultModel(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.content = "";
    }

    public ResultModel(Integer code, String msg, Object content) {
        this.code = code;
        this.msg = msg;
        this.content = content;
    }

    public ResultModel(ResultStatus status, Object content){
        this.code = status.getCode();
        this.msg = status.getMessage();
        this.content = content;
    }
    public ResultModel(ResultStatus status, String message) {
        this.code = status.getCode();
        this.msg = message;
    }
    public ResultModel(ResultStatus status) {
        this.code = status.getCode();
        this.msg = status.getMessage();
        this.content = "";
    }
    public ResultModel(ResultStatus status, Object content, Long count) {
        this.code = status.getCode();
        this.msg = status.getMessage();
        this.content = content;
        this.count = count;
    }
    public static ResultModel ok(Object content, Long count) {
        return new ResultModel(ResultStatus.SUCCESS, content, count);
    }
    public static ResultModel ok() {
        return new ResultModel(ResultStatus.SUCCESS);
    }

    public static ResultModel isOk() {
        return new ResultModel(ResultStatus.ISOK);
    }

    public static ResultModel ok(Object content) {
        return new ResultModel(ResultStatus.SUCCESS, content);
    }

    public static ResultModel error(ResultStatus error) {
        return new ResultModel(error);
    }

    public static ResultModel error(String message) {
        return new ResultModel(-100, message);
    }
}
