package com.abc.gcsmsys.mapper;

import com.abc.gcsmsys.domain.Orders;
import com.abc.gcsmsys.domain.OrdersSearch;
import com.abc.gcsmsys.domain.Resident;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Repository;

/**
 * @Description
 */
@Repository
public interface OrderMapper extends BaseMapper<Orders> {

    /**
     * 查找用户订单
     * @param iPage
     * @param userId
     * @param orderName 商品名称
     * @return
     */
    IPage<Orders> findUserOrders(IPage<Orders> iPage,Integer userId,String orderName);


    IPage<Orders> serveOrdersPage(IPage<Orders> iPage, OrdersSearch search);

    Orders orderById(String orderNo);
}
