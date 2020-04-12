package com.yhf.points.service;

import com.yhf.points.dao.GoodsDao;
import com.yhf.points.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {
    @Autowired
    private GoodsDao goodsDao;

    public List<Goods> getAll()
    {
        return goodsDao.getAll();
    }

    public List<Goods> getFruzz(String name)
    {
        return goodsDao.getFruzz(name);
    }

    public List<Goods> getByPoints(int min,int max)
    {
        return goodsDao.getByPoints(min,max);
    }
}
