package com.abc.gcsmsys.service.impl;

import com.abc.gcsmsys.domain.Evaluate;
import com.abc.gcsmsys.domain.Orders;
import com.abc.gcsmsys.mapper.EvaluateMapper;
import com.abc.gcsmsys.mapper.OrderMapper;
import com.abc.gcsmsys.service.EvaluateService;
import com.abc.gcsmsys.service.OrderService;
import com.abc.gcsmsys.utils.Result;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 */
@Service
public class EvaluateServiceImpl implements EvaluateService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private EvaluateMapper evaluateMapper;

    @Override
    public Orders getEvalByOrderId(Integer orderId) {

        Orders evalByOrderId = evaluateMapper.getEvalByOrderId(orderId);

        return evalByOrderId;
    }

    @Override
    public Result userAddEvaluate(Evaluate evaluate) {
        int insert = evaluateMapper.insert(evaluate);
        if (insert == 1 || "1".equals(insert)){
            UpdateWrapper updateWrapper = new UpdateWrapper();
            updateWrapper.eq("order_id",evaluate.getOrderId());
            updateWrapper.set("eval_status","1");//1标识订单已评价
            orderMapper.update(null,updateWrapper);
        }

        return Result.success();
    }

    @Override
    public Evaluate userEvalById(String eId) {

        Evaluate evaluate = evaluateMapper.selectOne(Wrappers.<Evaluate>lambdaQuery().eq(Evaluate::getEId, eId));
        return evaluate;
    }

    @Override
    public IPage<Evaluate> findUserEvaluate(Integer pageNum, Integer pageSize) {

        IPage<Evaluate> userEvaluate = evaluateMapper.findUserEvaluate(new Page<>(pageNum, pageSize));
        return userEvaluate;
    }

    @Override
    public List<Evaluate> userListEval() {
        List<Evaluate> evaluatesList = evaluateMapper.selectList(null);
        return evaluatesList;
    }
}
