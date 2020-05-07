package com.yhf.points.service;

import com.yhf.points.dao.UserDao;
import com.yhf.points.model.Attribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public Attribute getUser(Attribute attribute)
    {
        try {
            return userDao.getUser(attribute);
        } catch (Exception e)
        {
            throw e;
        }
    }
}
