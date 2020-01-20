package com.example.swagger.controller;

import com.example.swagger.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: gmf
 * @date: Created in 2020/1/20 17:57
 * @version:
 * @modified By:
 */
@RestController
@Api("用户管理相关接口")
@RequestMapping("/user")
public class UserController {
    User user = new User();
    @PostMapping("/")
    @ApiOperation("添加用户的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name ="name",value = "用户名",defaultValue = "李四"),
            @ApiImplicitParam(name = "address",value = "用户地址",defaultValue = "北京",required = true)
    })
    public String addUser(String name, @RequestParam(required = true)String address){
        user.setId(1);
        user.setAddress(address);
        user.setUsername(name);
        ObjectMapper mapper = new ObjectMapper();
        if(user != null){
            try {
                String s = mapper.writeValueAsString(user);
                return s;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return "用户添加失败";
    }
    @PutMapping("/{id}")
    @ApiOperation("根据id更新用户的接口")
    @ApiImplicitParam(name = "id",value = "用户id",defaultValue = "2")
    public User updateUser(@RequestBody User user,@PathVariable("id")Integer id){
        user.setId(id);
        user.setAddress("新疆");
        user.setUsername("赵武");
        return user;
    }
}
