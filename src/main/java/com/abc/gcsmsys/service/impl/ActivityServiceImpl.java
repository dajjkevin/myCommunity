package com.abc.gcsmsys.service.impl;

import cn.hutool.core.util.StrUtil;
import com.abc.gcsmsys.domain.*;
import com.abc.gcsmsys.mapper.ActivityMapper;
import com.abc.gcsmsys.service.ActivityService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public void savaAct(Activity activity) {
        activityMapper.insert(activity);
    }

    @Override
    public Page findPageAct(Integer pageNum, Integer pageSize, ActSearch actSearch) {

        QueryWrapper<Activity> wrapper = new QueryWrapper<>();

        if (!StrUtil.isBlank(actSearch.getSActTheme())){
            wrapper.like("act_theme", actSearch.getSActTheme());
        }else if(!StrUtil.isBlank(actSearch.getActTime())){
            wrapper.like("act_start",actSearch.getActTime());
        }else if (!StrUtil.isBlank(actSearch.getIsOpen())){
            wrapper.eq("is_open",actSearch.getIsOpen());
        }

        Page<Activity> actInfo = activityMapper.selectPage(new Page<>(pageNum, pageSize), wrapper.orderByDesc("act_id"));

        return actInfo;

    }

    @Override
    public IPage<Activity> findUserPageAct(Integer pageNum, Integer pageSize, ActSearch actSearch) {

        IPage<Activity> pageAct = activityMapper.findUserPageAct(new Page<>(pageNum, pageSize), actSearch);

        return pageAct;
    }

    @Override
    public Activity actById(Integer actId) {

        Activity activity = activityMapper.selectOne(Wrappers.<Activity>lambdaQuery().eq(Activity::getActId, actId));
        return activity;
    }

    @Override
    public void updateAct(Activity activity) {
        activityMapper.updateById(activity);
    }

    @Override
    public void changeIsOpen(Integer actId, String isOpen) {

        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("act_id",actId);
        updateWrapper.set("is_open",isOpen);

        activityMapper.update(null,updateWrapper);
    }

}
