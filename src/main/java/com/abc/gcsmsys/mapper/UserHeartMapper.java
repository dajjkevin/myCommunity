package com.abc.gcsmsys.mapper;

import com.abc.gcsmsys.domain.UserHeart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Repository;

/**
 * @Description
 */
@Repository
public interface UserHeartMapper extends BaseMapper<UserHeart> {

    IPage<UserHeart> findUserHeart(IPage<UserHeart> iPage, Integer userId);

}
