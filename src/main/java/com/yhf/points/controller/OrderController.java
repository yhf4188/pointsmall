package com.yhf.points.controller;

import com.alibaba.fastjson.JSONObject;
import com.yhf.points.JsonPackage.Message;
import com.yhf.points.JsonPackage.MessageCode;
import com.yhf.points.model.Attribute;
import com.yhf.points.model.Order;
import com.yhf.points.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    public OrderService orderService;

    @RequestMapping(value = "/getOrderByUser",method= RequestMethod.POST)
    public Message getOrderByUser(@RequestBody JSONObject jsonObject)
    {
        Message message=new Message();
        try
        {
            Attribute attribute=jsonObject.getObject("user",Attribute.class);
            List<Order> list=orderService.getOrderByUser(attribute);
            message.setMessage(MessageCode.SUCCESS,"获取成功");
            message.getData().put("order",list);
        }catch (Exception e)
        {
            e.printStackTrace();
            log.error(e.getMessage());
            message.setMessage(MessageCode.ERROR,"系统异常："+e.getMessage());
        }finally {
            return message;
        }
    }

}
