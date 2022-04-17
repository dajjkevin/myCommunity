package com.abc.gcsmsys.mapper;

import com.abc.gcsmsys.domain.Evaluate;
import com.abc.gcsmsys.domain.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Repository;

/**
 * @Description
 */
@Repository
public interface EvaluateMapper extends BaseMapper<Evaluate> {

    Orders getEvalByOrderId(Integer orderId);

    IPage<Evaluate> findUserEvaluate(IPage<Evaluate> iPage);
}
