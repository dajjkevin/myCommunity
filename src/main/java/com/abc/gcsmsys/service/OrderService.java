package com.abc.gcsmsys.service;

import com.abc.gcsmsys.domain.Orders;
import com.abc.gcsmsys.domain.Resident;
import com.abc.gcsmsys.domain.ResidentSearch;
import com.abc.gcsmsys.utils.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.Map;

/**
 * @Description
 */
public interface OrderService {

    void addServeOrder(Orders order);

    /**回调函数是否支付成功状态*/
    String returnOrder(Map map);

    IPage<Orders> findUserOrders(Integer pageNum,Integer pageSize,Integer userId,String orderName);

    Orders orderById(String orderNo);

    Integer orderCount(Integer userId);

    Result orderStatus(String method,String orderNo);
}
