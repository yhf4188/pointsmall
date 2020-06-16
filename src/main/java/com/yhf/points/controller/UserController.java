package com.yhf.points.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhf.points.JsonPackage.Message;
import com.yhf.points.JsonPackage.MessageCode;
import com.yhf.points.dao.AddressDao;
import com.yhf.points.model.Address;
import com.yhf.points.model.Attribute;
import com.yhf.points.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/pointsmall")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AddressDao addressDao;

    /**
     * 判断用户绑定输入的正确性，并返回带有积分的用户数据
     * @param map
     * @return
     */
//    @RequestMapping(value = "/getUser",method = RequestMethod.POST)
    @PostMapping(value = "/getUser",produces = {"application/json;charset=UTF-8"})
    public Message getUser(@RequestBody JSONObject map)
    {
        Attribute user=map.getObject("user",Attribute.class);
        Message message=new Message();
        try{
            Attribute arr=userService.getUser(user);
            if(arr!=null)
            {
                if(map.containsKey("address"))
                {
                    Address address = new Address();
                    address.setAddress(map.getString("address"));
                    address.setAttribute_id(arr.getId());
                    System.out.println(address);
                    addressDao.setAddress(address);
                    address=addressDao.getByAttrIDAndAddress(address);
                    message.getData().put("address",address);
                }
                else {
                    if (map.containsKey("address_id")) {
                        addressDao.setDefault(map.getInteger("address_id"), arr.getId());
                        addressDao.setUnDefault(map.getInteger("address_id"), arr.getId());
                        arr = userService.getUser(user);
                    }
                    message.getData().put("user",arr);
                }
                message.setMessage(MessageCode.SUCCESS,"该用户信息正确");
            }
            else message.setMessage(MessageCode.FAILURE,"该用户信息错误");
        } catch (Exception e)
        {
            log.error(e.getMessage());
            e.printStackTrace();
            message.setMessage(MessageCode.ERROR,"查询异常");
        }
        finally {
            return message;
        }
    }
}
