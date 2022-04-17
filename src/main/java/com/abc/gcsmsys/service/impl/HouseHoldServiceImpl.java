package com.abc.gcsmsys.service.impl;

import cn.hutool.core.util.StrUtil;
import com.abc.gcsmsys.domain.HouseHold;
import com.abc.gcsmsys.domain.HouseIdInfoVo;
import com.abc.gcsmsys.domain.HouseSearch;
import com.abc.gcsmsys.domain.Resident;
import com.abc.gcsmsys.mapper.HouseHoldMapper;
import com.abc.gcsmsys.mapper.ResidentMapper;
import com.abc.gcsmsys.service.HouseHoldService;
import com.abc.gcsmsys.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 */
@Service
public class HouseHoldServiceImpl implements HouseHoldService {

    @Autowired
    private HouseHoldMapper houseHoldMapper;

    @Autowired
    private ResidentMapper residentMapper;


    @Override
    public void saveHouseHold(HouseHold houseHold) {
        houseHoldMapper.insert(houseHold);
    }

    @Override
    public Page findPageHouseHold(Integer pageNum, Integer pageSize, HouseSearch search) {

        QueryWrapper<HouseHold> wrapper = new QueryWrapper<>();

        if (!StrUtil.isBlank(search.getSName()) || !StrUtil.isBlank(search.getSProvince())
            || !StrUtil.isBlank(search.getSGender()) ){ //不等于null才进行查找
            wrapper.like("house_name", search.getSName()).like("house_addr",search.getSProvince())
                    .like("gender",search.getSGender());
        }

        Page<HouseHold> houseInfo = houseHoldMapper.selectPage(new Page<>(pageNum, pageSize), wrapper.orderByDesc("house_Id"));

        return houseInfo;
    }

    @Override
    public List<HouseHold> HouseHoldInfo() {
        return houseHoldMapper.HouseHoldInfo();
    }

    @Override
    public List<Resident> houseById(Integer id) {

        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("house_id",id);

        List list = residentMapper.selectList(queryWrapper);

        return list;
    }

    @Override
    public HouseHold houseByHouseId(Integer id) {

        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("house_id",id);

        HouseHold houseHold = houseHoldMapper.selectOne(queryWrapper);

        return houseHold;
    }

    @Override
    public void updateHouse(HouseHold houseHold) {

        houseHoldMapper.updateById(houseHold);
    }

    @Override
    public void delHouse(Integer houseId,String housingStatus) {

        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("house_Id",houseId);
        updateWrapper.set("housing_status",housingStatus);

        houseHoldMapper.update(null,updateWrapper);
    }


}
