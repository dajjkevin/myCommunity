package com.abc.gcsmsys.service.impl;

import com.abc.gcsmsys.domain.ActivityList;
import com.abc.gcsmsys.domain.Serve;
import com.abc.gcsmsys.domain.ServeSearch;
import com.abc.gcsmsys.domain.UserHeart;
import com.abc.gcsmsys.mapper.UserHeartMapper;
import com.abc.gcsmsys.service.UserHeartService;
import com.abc.gcsmsys.utils.Result;
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
public class UserHearServiceImpl implements UserHeartService {

    @Autowired
    private UserHeartMapper userHeartMapper;

    @Override
    public Result actHeart(UserHeart heart) {

        UserHeart userHeart = userHeartMapper.selectOne(Wrappers.<UserHeart>lambdaQuery().eq(UserHeart::getActId, heart.getActId()).eq(UserHeart::getUserId, heart.getUserId()));
        if (userHeart == null){

            heart.setMyHeart("1");
            userHeartMapper.insert(heart);
            return Result.success();

        }else if (userHeart != null){

            if (userHeart.getMyHeart().equals("0")){
                UpdateWrapper updateWrapper = new UpdateWrapper();
                updateWrapper.eq("act_id",heart.getActId());
                updateWrapper.eq("user_id",heart.getUserId());
                updateWrapper.set("my_heart","1");
                userHeartMapper.update(null,updateWrapper);
                return Result.success();
            }

        }
        return Result.error("-2","已经收藏过活动");
    }

    @Override
    public IPage<UserHeart> findUserHeart(Integer pageNum, Integer pageSize, Integer userId) {
        IPage<UserHeart> userHeart = userHeartMapper.findUserHeart(new Page<UserHeart>(pageNum, pageSize), userId);
        return userHeart;
    }

    @Override
    public Result cancelHeart(Integer heartId) {

        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("heart_id",heartId);
        updateWrapper.set("my_heart","0");

        userHeartMapper.update(null,updateWrapper);

        return Result.success();
    }


}
