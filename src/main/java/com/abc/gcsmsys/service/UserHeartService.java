package com.abc.gcsmsys.service;

import com.abc.gcsmsys.domain.ActivityList;
import com.abc.gcsmsys.domain.ServeSearch;
import com.abc.gcsmsys.domain.UserHeart;
import com.abc.gcsmsys.utils.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * @Description
 */
public interface UserHeartService {

    Result actHeart(UserHeart heart);

    IPage findUserHeart(Integer pageNum, Integer pageSize, Integer userId);

    Result cancelHeart(Integer heartId);
}
