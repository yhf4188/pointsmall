package com.yhf.points.service;

import com.yhf.points.dao.GoodsDao;
import com.yhf.points.dao.OrderDao;
import com.yhf.points.dao.UserDao;
import com.yhf.points.model.Attribute;
import com.yhf.points.model.Goods;
import com.yhf.points.model.Order;
import com.yhf.points.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class GoodsService {
    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisTemplate redisTemplate;
    @Resource
    private RedisService redisService;
    //获取所有

    @Resource
    private ValueOperations<String, Object> valueOperations;
    public List<Goods> getAll()
    {
        return goodsDao.getAll();
    }

    //模糊搜索
    public List<Goods> getFruzz(String name)
    {
        return goodsDao.getFruzz(name);
    }

    //通过积分范围获取商品
    public List<Goods> getByPoints(int min,int max)
    {
        return goodsDao.getByPoints(min,max);
    }

    //下单
    public boolean placeAnOrder(String userName,Integer mallID,Integer addressID) {
        try {
            Attribute user =new Attribute();
            user=userDao.getUserByName(userName);
            Integer userID = userDao.getID(userName);
            valueOperations = redisTemplate.opsForValue();
            Integer num = (Integer)valueOperations.get(String.valueOf(mallID));
            Goods goods = goodsDao.getGood(mallID);
            if(user.getPoints()<goods.getPoints())//判断用户积分是否足够
            {
                return false;
            }
            if (num == null) {
                Goods good = goodsDao.getGood(mallID);
                if(user.getPoints()>= good.getPoints()) {
                    valueOperations.set(Integer.toString(mallID), good.getGoods_num());
                    num = good.getGoods_num();
                    if (num >= 0) {
                        //设置有效期十分钟
                        redisService.expireKey(String.valueOf(mallID), 60 * 10, TimeUnit.SECONDS);
                    }
                }
                else return false;
            }
            if (num < 1) {
                return false;
            }
            long value = valueOperations.increment(Integer.toString(mallID), -1);
            // 库存充足
            if (value >= 0) {
                // update 数据库中商品库存和订单系统下单，单的状态未待支付
                Goods good = new Goods();
                good.setId(mallID);
                good.setGoods_num((int) value);
                int res = goodsDao.updateNum(good);
                int points = goodsDao.getGood(mallID).getPoints();
                if (res > 0) {
                    Order order = new Order(0, mallID, userID, "未发货");
                    orderDao.insertOrder(order.getGoods_id(),order.getUser_id(),order.getGoods_state(),addressID);
                    user.setPoints(user.getPoints()-points);
                    userDao.updatePoints(user);
                }
                return true;
            } else {
                valueOperations.increment(Integer.toString(mallID), 1);// 减了后小小于0 ，如两个人同时买这个商品，导致A人第一步时看到还有10个库存，但是B人买9个先处理完逻辑，导致B人的线程10-9=1, A人的线程1-10=-9，则现在需要增加刚刚减去的库存，让别人可以买1个
                return false;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }
    }
}
