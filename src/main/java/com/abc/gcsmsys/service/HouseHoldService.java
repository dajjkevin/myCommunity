package com.abc.gcsmsys.service;

import com.abc.gcsmsys.domain.HouseHold;
import com.abc.gcsmsys.domain.HouseIdInfoVo;
import com.abc.gcsmsys.domain.HouseSearch;
import com.abc.gcsmsys.domain.Resident;
import com.abc.gcsmsys.utils.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 */
public interface HouseHoldService {

    void saveHouseHold(HouseHold houseHold);

    Page findPageHouseHold(Integer pageNum, Integer pageSize, HouseSearch search);

    /**主要查询到 户主的id和姓名且不是搬迁状态*/
    List<HouseHold> HouseHoldInfo();

    List<Resident> houseById(Integer id);

    /**户主个人信息*/
    HouseHold houseByHouseId(Integer id);

    void updateHouse(HouseHold houseHold);

    void delHouse(Integer houseId,String housingStatus);
}
