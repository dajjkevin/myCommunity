package com.abc.gcsmsys.service.impl;

import cn.hutool.core.util.StrUtil;
import com.abc.gcsmsys.domain.HouseHold;
import com.abc.gcsmsys.domain.Resident;
import com.abc.gcsmsys.domain.ResidentSearch;
import com.abc.gcsmsys.mapper.HouseHoldMapper;
import com.abc.gcsmsys.mapper.ResidentMapper;
import com.abc.gcsmsys.service.ResidentService;
import com.abc.gcsmsys.utils.GetAge;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Description
 */
@Service
public class ResidentServiceImpl implements ResidentService {

    @Autowired
    private ResidentMapper residentMapper;

    @Autowired
    private HouseHoldMapper houseHoldMapper;

    @Override
    public void addResident(Resident resident) throws Exception {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date bithday = format.parse(resident.getBirthday());//根据生日算年龄
        int residentAge = GetAge.GetAge1(bithday);
        resident.setAge(residentAge);

        QueryWrapper<HouseHold> wrapper = new QueryWrapper<>();

        Integer houseId = resident.getHouseId();
        HouseHold house_id = houseHoldMapper.selectOne(wrapper.eq("house_id", houseId));

        if (house_id !=null){
            HouseHold houseHold = new HouseHold();
            houseHold.setHouseId(house_id.getHouseId());
            houseHold.setHouseCnt(house_id.getHouseCnt()+1); //对已有的户主总人数进行更新操作
            houseHoldMapper.updateById(houseHold);
        }

        residentMapper.insert(resident);
    }

    @Override
    public Page findPageResident(Integer pageNum, Integer pageSize, String search) {

        QueryWrapper<Resident> wrapper = new QueryWrapper<>();

        if (!StrUtil.isBlank(search)){ //不等于null才进行查找
            wrapper.like("name", search);
        }

//        new Page<>(pageNum, pageSize).var;
        Page<Resident> residentPage = residentMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);

        return residentPage;
    }

    @Override
    public IPage<Resident> findPageResi(Integer pageNum, Integer pageSize, ResidentSearch search) {


        IPage pageData = residentMapper.findPageResi(new Page<Resident>(pageNum, pageSize), search);

        return pageData;
    }

    @Override
    public Resident residentByPersonId(ResidentSearch upData) {

        Resident resident = residentMapper.residentByPersonId(upData);
        return resident;
    }

    @Override
    public void updateResident(Resident resident) {

        Integer newHouseId = resident.getHouseId();//检查当前修改的 houseId 新/增 1

        QueryWrapper<Resident> wrapperResident = new QueryWrapper<>();
        Resident residentNew = residentMapper.selectOne(wrapperResident.eq("person_id", resident.getPersonId()));
        Integer houseIdR = residentNew.getHouseId(); //居民的表  3

        if (newHouseId != null && residentNew.getHouseId() ==null){

            QueryWrapper<HouseHold> wrapper = new QueryWrapper<>();
            HouseHold house_id = houseHoldMapper.selectOne(wrapper.eq("house_id", newHouseId));

            if (house_id !=null){
                HouseHold houseHold = new HouseHold();
                houseHold.setHouseId(house_id.getHouseId());
                houseHold.setHouseCnt(house_id.getHouseCnt()+1); //对已有的户主总人数进行更新操作
                houseHoldMapper.updateById(houseHold);
            }

        }else if (newHouseId != houseIdR && newHouseId != null && houseIdR != null){ //house_id不同进行操作

            QueryWrapper<HouseHold> wrapperHouse = new QueryWrapper<>();
            HouseHold house = houseHoldMapper.selectOne(wrapperHouse.eq("house_Id", houseIdR));//3

            UpdateWrapper updateWrapper = new UpdateWrapper();
            updateWrapper.eq("house_Id",houseIdR);
            updateWrapper.set("house_cnt",house.getHouseCnt()-1);
            houseHoldMapper.update(null,updateWrapper);

            QueryWrapper<HouseHold> wrapperHouseNew = new QueryWrapper<>();
            HouseHold houseNew = houseHoldMapper.selectOne(wrapperHouseNew.eq("house_Id", newHouseId));
            UpdateWrapper updateWrapperNew = new UpdateWrapper();
            updateWrapperNew.eq("house_Id",newHouseId);
            updateWrapperNew.set("house_cnt",houseNew.getHouseCnt()+1);
            houseHoldMapper.update(null,updateWrapperNew);

        }
        else if(newHouseId == null){
            QueryWrapper<HouseHold> wrapperHouseNew = new QueryWrapper<>();
            HouseHold houseOld = houseHoldMapper.selectOne(wrapperHouseNew.eq("house_Id", residentNew.getHouseId()));
            UpdateWrapper updateWrapper = new UpdateWrapper();
            updateWrapper.eq("house_Id",residentNew.getHouseId());
            updateWrapper.set("house_cnt",houseOld.getHouseCnt()-1);
            houseHoldMapper.update(null,updateWrapper);
            resident.setHouseId(null);
        }

        residentMapper.updateById(resident);

    }

    @Override
    public List<Resident> loadResident() {

        QueryWrapper<Resident> userQueryWrapper = Wrappers.query();
        List<Resident> residents = residentMapper.selectList(null);

        return residents;
    }

}
