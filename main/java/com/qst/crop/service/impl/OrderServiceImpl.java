package com.qst.crop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qst.crop.dao.OrderDao;
import com.qst.crop.entity.Order;
import com.qst.crop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shitusiren
 * @Date 2024/8/9
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;

    public int addOrder(Order order) {
        return orderDao.insert(order);
    }
    public int deleteOrder(Integer orderId){return orderDao.deleteByPrimaryKey(orderId);}
    public int updateByPrimaryKey(Order order) {
        return orderDao.updateByPrimaryKeySelective(order);
    }
    @Override
    public PageInfo<Order> findAll(Integer pageNum) {
        //PageHelper.startPage方法会将分页信息放入ThreadLocal中，但是 PageHelper 方法调用后紧跟查询方法，就是安全的，因为查询方法后清除了Threadlocal里的值。
        PageHelper.startPage(pageNum,30);
        List<Order> orders =  orderDao.findAll();
        PageInfo<Order> orderPageInfo = new PageInfo<>(orders);
        //调用dao进行查询
        return orderPageInfo;
    }


    // 根据订单ID查找订单
    @Override
    public Order findById(Integer id) {
        return orderDao.selectByPrimaryKey(id);
    }

    // 根据关键字搜索订单，支持分页
    @Override
    public PageInfo<Order> findByKeys(String keys, Integer pageNum) {
        PageHelper.startPage(pageNum, 30);
        List<Order> orders = orderDao.findByKeys(keys);
        return new PageInfo<>(orders);
    }

    // 根据用户名和订单类型查询订单，支持分页
    @Override
    public PageInfo<Order> findByUserName(String type, String userName, Integer pageNum) {
        PageHelper.startPage(pageNum,10);
        List<Order> orders = orderDao.selectByUserName(type, userName);
        return new PageInfo<>(orders);
    }

    // 查询所有货源订单，支持分页
    @Override
    public PageInfo<Order> findAllSource(Integer pageNum) {
        PageHelper.startPage(pageNum, 30);
        List<Order> orders = orderDao.findAllSource();
        return new PageInfo<>(orders);
    }

    // 根据关键字搜索货源订单，支持分页
    @Override
    public PageInfo<Order> findSourceByKeys(String keys, Integer pageNum) {
        PageHelper.startPage(pageNum, 30);
        List<Order> orders = orderDao.findSourceByKeys(keys);
        return new PageInfo<>(orders);
    }

    // 根据关键字和用户名搜索用户的货源订单，支持分页
    @Override
    public PageInfo<Order> findMySourceByKeys(String keys, String username, Integer pageNum) {
        PageHelper.startPage(pageNum, 30);
        List<Order> orders = orderDao.selectMySourceByKeys(keys, username);
        return new PageInfo<>(orders);
    }

    // 查询所有需求订单，支持分页
    @Override
    public PageInfo<Order> findAllNeeds(Integer pageNum) {
        PageHelper.startPage(pageNum, 30);
        List<Order> orders = orderDao.findAllNeeds();
        return new PageInfo<>(orders);
    }

    // 根据关键字搜索需求订单，支持分页
    @Override
    public PageInfo<Order> findNeedsByKeys(String keys, Integer pageNum) {
        PageHelper.startPage(pageNum, 30);
        List<Order> orders = orderDao.findNeedsByKeys(keys);
        return new PageInfo<>(orders);
    }

    // 根据关键字和用户名搜索用户的需求订单，支持分页
    @Override
    public PageInfo<Order> findMyNeedsByKeys(String keys, String username, Integer pageNum) {
        PageHelper.startPage(pageNum, 30);
        List<Order> orders = orderDao.selectMyNeedsByKeys(keys, username);
        return new PageInfo<>(orders);
    }

    // 有选择地插入订单
    @Override
    public int addSelective(Order record) {
        return orderDao.insertSelective(record);
    }

    // 有选择地更新订单
    @Override
    public int updateSelective(Order record) {
        return orderDao.updateByPrimaryKeySelective(record);
    }

    // 根据订单ID删除订单
    @Override
    public int delById(Integer orderId) {
        return orderDao.deleteByPrimaryKey(orderId);
    }
    @Override
    public void takeDown(String orderId) {
        orderDao.takeDown(orderId);
    }

    @Override
    public void takeUp(String orderId) {
        orderDao.takeUp(orderId);
    }


}

