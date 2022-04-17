package com.abc.gcsmsys.service.impl;

import com.abc.gcsmsys.domain.*;
import com.abc.gcsmsys.mapper.AppealMapper;
import com.abc.gcsmsys.service.AppealService;
import com.abc.gcsmsys.utils.Result;
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
public class AppealServiceImpl implements AppealService {

    @Autowired
    private AppealMapper appealMapper;

    @Override
    public Result userAppeal(Appeal appeal) {
        appealMapper.insert(appeal);
        return Result.success();
    }

    @Override
    public IPage<Appeal> findUserAppeal(Integer pageNum, Integer pageSize, AppealSearch search) {

        IPage<Appeal> userAppeal = appealMapper.findUserAppeal(new Page<>(pageNum, pageSize), search);
        return userAppeal;
    }

    @Override
    public Result appealById(Integer appId) {

        Appeal appeal = appealMapper.selectOne(Wrappers.<Appeal>lambdaQuery().eq(Appeal::getAppealId, appId));
        return Result.success(appeal);
    }

    @Override
    public Result replyAppeal(Appeal appeal) {

        int i = appealMapper.updateById(appeal);
        if (i == 1){
            return Result.success();
        }
        return Result.error("-1","错误！！！！");
    }

    @Override
    public List<Appeal> userAppealById(Integer userId) {

        List<Appeal> appeals = appealMapper.selectList(Wrappers.<Appeal>lambdaQuery().eq(Appeal::getUserId, userId));
        return appeals;
    }
}
