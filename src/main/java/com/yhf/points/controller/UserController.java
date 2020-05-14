package com.yhf.points.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhf.points.JsonPackage.Message;
import com.yhf.points.JsonPackage.MessageCode;
import com.yhf.points.model.Attribute;
import com.yhf.points.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/pointsmall")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 判断用户绑定输入的正确性，并返回带有积分的用户数据
     * @param map
     * @return
     */
//    @RequestMapping(value = "/getUser",method = RequestMethod.POST)
    @GetMapping("/getUser")
    public Message getUser(@RequestBody JSONObject map)
    {
        System.out.println(map.toString());
        Attribute user=map.getObject("user",Attribute.class);
        System.out.println(user.toString());
        Message message=new Message();
        try{
            Attribute arr=userService.getUser(user);
            if(arr!=null)
            {
                message.setMessage(MessageCode.SUCCESS,"该用户信息正确");
                message.getData().put("user",arr);
            }
            else message.setMessage(MessageCode.FAILURE,"该用户信息错误");
        } catch (Exception e)
        {
            log.error(e.getMessage());
            message.setMessage(MessageCode.ERROR,"查询异常");
        }
        finally {
            return message;
        }
    }
}
