package com.abc.gcsmsys.service.impl;

import com.abc.gcsmsys.config.AlipayConfig;
import com.abc.gcsmsys.domain.Orders;
import com.abc.gcsmsys.domain.Resident;
import com.abc.gcsmsys.domain.ServeType;
import com.abc.gcsmsys.mapper.OrderMapper;
import com.abc.gcsmsys.service.OrderService;
import com.abc.gcsmsys.utils.Result;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @Description
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void addServeOrder(Orders order) {
        orderMapper.insert(order);
    }

    @Override
    public String returnOrder(Map map) {
        System.out.println(map);
        log.info(String.valueOf(map));
        try {
            // 调用SDK验证签名
            boolean signVerified = AlipaySignature.rsaCheckV1(map,AlipayConfig.ALIPAY_PUBLIC_KEY,AlipayConfig.CHARSET,AlipayConfig.SIGN_TYPE);
            if (signVerified) {
                System.out.println("支付宝回调签名认证成功");
                // 按照支付结果异步通知中的描述，对支付结果中的业务内容进行1\2\3\4二次校验，校验成功后在response中返回success，校验失败返回failure
//                alipayUtil.check(map);

                // 另起线程处理业务
                ExecutorService executorService = new ThreadPoolExecutor(4,
                        6,
                        0L,
                        TimeUnit.SECONDS,
                        new LinkedBlockingQueue<>(512),
                        Executors.defaultThreadFactory(),
                        new ThreadPoolExecutor.AbortPolicy());
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        UpdateWrapper orderUpdate  = new UpdateWrapper();
                        orderUpdate.eq("order_no",map.get("out_trade_no"));
                        orderUpdate.set("pay_status","1");
                        orderUpdate.set("pay_time",map.get("gmt_payment"));
                        orderMapper.update(null,orderUpdate);
                    }
                });
                // 如果签名验证正确，立即返回success，后续业务另起线程单独处理
                // 业务处理失败，可查看日志进行补偿，跟支付宝已经没多大关系。
                return "success";//成功返回信息
            } else {
                return "failure";
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return "failure";
        }
    }

    @Override
    public IPage<Orders> findUserOrders(Integer pageNum,Integer pageSize, Integer userId, String orderName) {
        IPage userOrdersPage = orderMapper.findUserOrders(new Page<Orders>(pageNum, pageSize), userId, orderName);
        return userOrdersPage;
    }

    @Override
    public Orders orderById(String orderNo) {

        Orders orders = orderMapper.orderById(orderNo);
        return orders;
    }

    @Override
    public Integer orderCount(Integer userId) {

        Integer count = orderMapper.selectCount(Wrappers.<Orders>lambdaQuery().eq(Orders::getUserId, userId));
        return count;
    }

    @Override
    public Result orderStatus(String method,String orderNo) {

        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("order_no",orderNo);
        if ("finsh".equals(method)){
            updateWrapper.set("order_status","1");
        }
        if ("del".equals(method)){
            updateWrapper.set("order_status","2");
        }

        orderMapper.update(null,updateWrapper);

        return Result.success();
    }


}
