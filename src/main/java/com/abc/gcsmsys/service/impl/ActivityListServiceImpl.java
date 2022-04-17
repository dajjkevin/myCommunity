package com.abc.gcsmsys.service.impl;

import cn.hutool.core.util.StrUtil;
import com.abc.gcsmsys.domain.ActSearch;
import com.abc.gcsmsys.domain.Activity;
import com.abc.gcsmsys.domain.ActivityList;
import com.abc.gcsmsys.domain.Resident;
import com.abc.gcsmsys.mapper.ActivityListMapper;
import com.abc.gcsmsys.mapper.ActivityMapper;
import com.abc.gcsmsys.service.ActivityListService;
import com.abc.gcsmsys.service.ActivityService;
import com.abc.gcsmsys.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 */
@Service
public class ActivityListServiceImpl implements ActivityListService {

    @Autowired
    private ActivityListMapper activityListMapper;

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public Result signUpAct(Integer actId, Integer userId) {

        ActivityList activityList = activityListMapper.selectOne(Wrappers.<ActivityList>lambdaQuery().eq(ActivityList::getActId, actId).eq(ActivityList::getUserId, userId));
        if (activityList == null){

            ActivityList addActList = new ActivityList();
            addActList.setActId(actId);
            addActList.setUserId(userId);

            int insert = activityListMapper.insert(addActList);

            if (insert == 1){

                Activity activity = activityMapper.selectOne(Wrappers.<Activity>lambdaQuery().eq(Activity::getActId, actId));

                UpdateWrapper updateWrapper = new UpdateWrapper();
                updateWrapper.eq("act_id",actId);
                updateWrapper.set("act_num",Integer.parseInt(activity.getActNum())-1);
                updateWrapper.set("act_join_num",activity.getActJoinNum()+1);

                activityMapper.update(null,updateWrapper);//报名成功-1   参与人数+1

                return Result.success();
            }
        }

        return Result.error("-2","该活动，您已经报名");
    }

//    @Override
//    public Page  findPageUserAct(Integer pageNum, Integer pageSize, Integer userId) {
//
//        List<Integer> actIds = new ArrayList<>();
//        List<ActivityList> activityLists = activityListMapper.selectList(Wrappers.<ActivityList>lambdaQuery().eq(ActivityList::getUserId, userId));
//
//        for (ActivityList a:activityLists){
//            actIds.add(a.getActId());
//        }
//
//        for (Integer act:actIds){
//            System.out.print(act+"---多个id--");
//        }
//
//        QueryWrapper<Activity> wrapper = new QueryWrapper<>();
//        wrapper.in("act_id",actIds);
//
//        Page<Activity> actToUser = activityMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
//
//        return actToUser;
//    }

    @Override
    public IPage<ActivityList> findUserActList(Integer pageNum, Integer pageSize, Integer userId) {
        IPage<ActivityList> userActList = activityListMapper.findUserActList(new Page<>(pageNum, pageSize), userId);
        return userActList;
    }

}
