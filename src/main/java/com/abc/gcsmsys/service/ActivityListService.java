package com.abc.gcsmsys.service;

import com.abc.gcsmsys.domain.*;
import com.abc.gcsmsys.utils.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @Description
 */
public interface ActivityListService {

    /**报名活动*/
    Result signUpAct(Integer actId, Integer userId);


//    Page findPageUserAct(Integer pageNum, Integer pageSize, Integer userId);

    /*
     * @Author oxl
     * @Date 2022-4-5 16:02
     * @Description: 前台用户个人活动
     */
    IPage<ActivityList> findUserActList(Integer pageNum, Integer pageSize, Integer userId);


}
