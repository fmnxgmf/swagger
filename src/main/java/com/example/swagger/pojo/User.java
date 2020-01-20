package com.example.swagger.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description:
 * @author: gmf
 * @date: Created in 2020/1/20 18:00
 * @version:
 * @modified By:
 */
@Data
@ApiModel
public class User {
    @ApiModelProperty(value = "用户id")
    private Integer id;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty("用户地址")
    private String address;
}
