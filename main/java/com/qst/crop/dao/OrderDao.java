package com.qst.crop.dao;

import com.qst.crop.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author shitusiren
 * @Date 2024/8/9
 */
@Component
public interface OrderDao {

    // 增加order
    int insert(Order record);

    // 有选择地增加order
    int insertSelective(Order record);

    // 更新order
   // Order selectByPrimaryKey(Integer orderId);

    // 有选择地更新order
    int updateByPrimaryKeySelective(Order record);

    // 删除order
    int deleteByPrimaryKey(Integer orderId);

    // 查询所有商品订单
    List<Order> findAll();

    // 根据关键字查询订单
    List<Order> findByKeys(@Param("keys") String keys);

    // 根据订单ID查询订单
    Order selectByPrimaryKey(Integer orderId);

    // 查询所有货源订单
    List<Order> findAllSource();

    // 根据关键字查询货源订单
    List<Order> findSourceByKeys(@Param("keys") String keys);

    // 根据用户名和订单类型查询订单
    List<Order> selectByUserName(@Param("type") String type, @Param("userName") String username);

    // 根据关键字和用户名查询用户的货源订单
    List<Order> selectMySourceByKeys(@Param("keys") String keys, @Param("userName") String username);

    // 查询所有需求订单
    List<Order> findAllNeeds();

    // 根据关键字查询需求订单
    List<Order> findNeedsByKeys(@Param("keys") String keys);

    // 根据关键字和用户名查询用户的需求订单
    List<Order> selectMyNeedsByKeys(@Param("keys") String keys, @Param("userName") String username);
    List<Order> selectMySourceByKeys(@Param("keys") String keys, @Param("userName") String username,@Param("pageNum") Integer pageNum);


    List<Order> selectByExample(Order order);
    void takeDown(String orderId);

    void takeUp(String orderId);
}
