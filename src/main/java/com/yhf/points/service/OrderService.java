package com.yhf.points.service;


import com.yhf.points.dao.OrderDao;
import com.yhf.points.model.Attribute;
import com.yhf.points.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserService userService;

    public List<Order> getOrderByUser(Attribute user)
    {
        List<Order> orders =new ArrayList<>();
        try
        {
            user=userService.getUser(user);
            orders = orderDao.getAllOrderByUser(user);
        }catch (Exception e)
        {
            throw e;
        }finally {
            return orders;
        }
    }
}
