package com.example.swagger.jwt.commons.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: gmf
 * @date: Created in 2020/1/23 12:16
 * @version:
 * @modified By:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private Long id;
    private String username;
}
