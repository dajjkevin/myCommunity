package com.abc.gcsmsys.service;

import com.abc.gcsmsys.domain.Evaluate;
import com.abc.gcsmsys.domain.Orders;
import com.abc.gcsmsys.utils.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Description
 */
public interface EvaluateService {

    Orders getEvalByOrderId(Integer orderId);

    Result userAddEvaluate(Evaluate evaluate);

    Evaluate userEvalById(String eId);

    IPage<Evaluate> findUserEvaluate(Integer pageNum, Integer pageSize);

    List<Evaluate> userListEval();
}
