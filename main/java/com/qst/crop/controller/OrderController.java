package com.qst.crop.controller;

import com.github.pagehelper.PageInfo;
import com.qst.crop.common.Result;
import com.qst.crop.common.StatusCode;
import com.qst.crop.entity.Order;
import com.qst.crop.security.entity.JwtUser;
import com.qst.crop.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author shitusiren
 * @Date 2024/8/9
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    //分页获取所有的商品
    @RequestMapping("/All/{pageNum}")
    public Result<PageInfo> getOrderAll(@PathVariable("pageNum")Integer pageNum){
        PageInfo<Order> pageInfo = orderService.findAll(pageNum);
        return new Result<>(true, StatusCode.OK,"查询成功",pageInfo);
    }
////所有货源 findById
    @RequestMapping("/selectById/{orderId}")
    public Result<Order> getOrderById(@PathVariable("orderId")Integer orderId){
        Order order = orderService.findById(orderId);
        return new Result<>(true,StatusCode.OK,"查询成功",order);
    }
////个人货源 findByUserName
    @GetMapping("/search/{ctype}/{pageNum}")
    public Result<PageInfo<Order>> getOrderByUserName(@PathVariable("ctype")String type, @PathVariable("pageNum")Integer pageNum){
        JwtUser principal = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getUsername();
        PageInfo<Order> pageInfo = orderService.findByUserName(type,username,pageNum);
        return new Result<>(true,StatusCode.OK,"查询成功",pageInfo);
    }

      //分页查询所有商品货源
    @RequestMapping("/goods/{pageNum}")
    public Result<PageInfo> getSourceAll(@PathVariable("pageNum")Integer pageNum){
        PageInfo<Order> pageInfo = orderService.findAllSource(pageNum);
        return new Result<>(true,StatusCode.OK,"查询成功",pageInfo);
    }
////平台首页查询
    @RequestMapping("/searchAllByKeys/{keys}/{pageNum}")
    public Result<PageInfo> getOrderSearch(@PathVariable("keys")String keys, @PathVariable("pageNum")Integer pageNum){
        PageInfo<Order> pageInfo = orderService.findByKeys(keys,pageNum);
        return new Result<>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    @RequestMapping("/searchGoodsByKeys/{keys}/{pageNum}")
    public Result<PageInfo> getSourceSearch(@PathVariable("keys")String keys, @PathVariable("pageNum")Integer pageNum){
        PageInfo<Order> pageInfo = orderService.findSourceByKeys(keys,pageNum);
        return new Result<>(true,StatusCode.OK,"查询成功",pageInfo);
    }


    @RequestMapping("/searchMyGoodsByKeys/{keys}/{pageNum}")
    public Result<PageInfo> getMySourceSearch(@PathVariable("keys")String keys, @PathVariable("pageNum")Integer pageNum){
        JwtUser principal = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getUsername();
        PageInfo<Order> pageInfo = orderService.findMySourceByKeys(keys,username,pageNum);
        return new Result<>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    //需求
    @RequestMapping("/needs/{pageNum}")
    public Result<PageInfo> getNeedsAll(@PathVariable("pageNum")Integer pageNum){
        PageInfo<Order> pageInfo = orderService.findAllNeeds(pageNum);
        return new Result<>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    @RequestMapping("/searchNeedsByKeys/{keys}/{pageNum}")
    public Result<PageInfo> getNeedsSearch(@PathVariable("keys")String keys, @PathVariable("pageNum")Integer pageNum){
        PageInfo<Order> pageInfo = orderService.findNeedsByKeys(keys,pageNum);
        return new Result<>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    @RequestMapping("/searchMyNeedsByKeys/{keys}/{pageNum}")
    public Result<PageInfo> getMyNeedsSearch(@PathVariable("keys")String keys, @PathVariable("pageNum")Integer pageNum){
        JwtUser principal = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getUsername();
        PageInfo<Order> pageInfo = orderService.findMyNeedsByKeys(keys,username,pageNum);
        return new Result<>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    //增加order
    @PostMapping
    public Result<Integer> addOrder(@RequestBody Order order){
        JwtUser principal = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getUsername();
        order.setOwnName(username);
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        return new Result<>(true,StatusCode.OK,"添加成功", orderService.addSelective(order));
    }

    //修改order
    @PutMapping("{orderId}")
    public Result<Integer> updateOrder(@RequestBody Order order, @PathVariable("orderId")Integer orderId){
        order.setOrderId(orderId);
        return new Result<>(true,StatusCode.OK,"修改成功", orderService.updateSelective(order));
    }

   //删除order
    @DeleteMapping("{orderId}")
    public Result<Integer> DeleteOrder(@PathVariable("orderId")Integer orderId){
        return new Result<>(true,StatusCode.OK,"删除成功",orderService.delById(orderId));
    }




    @PutMapping("/update/{orderId}")
    public String updateByPrimaryKey(@RequestBody Order order, @PathVariable("orderId")Integer orderId) {
        order.setOrderId(orderId);
        int result = orderService.updateByPrimaryKey(order);
        return result > 0 ? "Order updated successfully" : "Failed to update order";
    }

    @DeleteMapping("/delete/{orderId}")
    public String deleteOrder(@PathVariable("orderId") Integer orderId)
    {
        int result=orderService.deleteOrder(orderId);
        return result > 0 ? "Order deleted successfully" : "Failed to deleted order";
    }
    //商品下架
    @PutMapping("/takeDownOrder/{orderId}")
    public Result takeDownOrder(@PathVariable("orderId") String orderId) {
        orderService.takeDown(orderId);
        return new Result<PageInfo>(true, StatusCode.OK, "下架成功");
    }
    //商品上架
    @PutMapping("/takeUpOrder/{orderId}")
    public Result takeUpOrder(@PathVariable("orderId") String orderId) {
        orderService.takeUp(orderId);
        return new Result<PageInfo>(true, StatusCode.OK, "上架成功");
    }

}
