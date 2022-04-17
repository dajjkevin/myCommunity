package com.abc.gcsmsys.mapper;

import com.abc.gcsmsys.domain.HouseHold;
import com.abc.gcsmsys.domain.HouseIdInfoVo;
import com.abc.gcsmsys.utils.Result;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 */
@Repository
public interface HouseHoldMapper extends BaseMapper<HouseHold> {

    /**主要查询到 户主的id和姓名且不是搬迁状态*/
    List<HouseHold> HouseHoldInfo();

    /**查看详细*/
    List<HouseIdInfoVo> houseById(Integer id);
}
