package com.yhf.points.controller;

import com.yhf.points.JsonPackage.Message;
import com.yhf.points.JsonPackage.MessageCode;
import com.yhf.points.model.Goods;
import com.yhf.points.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Author：yhf
 */
@Slf4j
@RestController
@RequestMapping("/pointsGoods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 获取所有商品数据
     */
    @PostMapping("/getAll")
    public Message getAll()
    {
        try {
            List<Goods> goods = new ArrayList<>();
            goods = goodsService.getAll();
            Message message = new Message();
            Map<String, Object> map = goods.stream().collect(Collectors.toMap(Goods::getName, Function.identity(), (key1, key2) -> key2));
            message.getData().putAll(map);
            message.setMessage(MessageCode.SUCCESS, "查找成功");
            return message;
        }
        catch (Exception e)
        {
            Message message=new Message();
            log.error(e.getMessage());
            message.setMessage(MessageCode.ERROR, "查找异常").getData().put("Exception",e.getMessage());
            return message;
        }
    }
}
