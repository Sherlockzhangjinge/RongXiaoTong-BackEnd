package com.qst.crop.service;

import com.github.pagehelper.PageInfo;
import com.qst.crop.entity.Order;


/**
 * @author shitusiren
 * @Date 2024/8/9
 */
public interface OrderService {

    // 查询所有订单，支持分页
    PageInfo<Order> findAll(Integer pageNum);

    int addOrder(Order order); //add order
    int deleteOrder(Integer orderId);//delete order
    int updateByPrimaryKey(Order order);//update order
    // 根据订单ID查询订单
    Order findById(Integer id);

    // 根据关键字查询订单，支持分页
    PageInfo<Order> findByKeys(String keys, Integer pageNum);

    // 根据用户名和订单类型查询订单，支持分页
    PageInfo<Order> findByUserName(String type, String username, Integer pageNum);

    // 查询所有货源订单，支持分页
    PageInfo<Order> findAllSource(Integer pageNum);

    // 根据关键字查询货源订单，支持分页
    PageInfo<Order> findSourceByKeys(String keys, Integer pageNum);

    // 根据关键字和用户名查询用户的货源订单，支持分页
    PageInfo<Order> findMySourceByKeys(String keys, String username, Integer pageNum);

    // 查询所有需求订单，支持分页
    PageInfo<Order> findAllNeeds(Integer pageNum);

    // 根据关键字查询需求订单，支持分页
    PageInfo<Order> findNeedsByKeys(String keys, Integer pageNum);

    // 根据关键字和用户名查询用户的需求订单，支持分页
    PageInfo<Order> findMyNeedsByKeys(String keys, String username, Integer pageNum);

    // 添加订单，返回影响的行数
    int addSelective(Order record);

    // 更新订单，返回影响的行数
    int updateSelective(Order record);

    // 根据订单ID删除订单，返回影响的行数
    int delById(Integer orderId);
    void takeDown(String orderId);

    void takeUp(String orderId);

}
