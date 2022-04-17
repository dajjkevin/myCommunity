package com.abc.gcsmsys.mapper;

import com.abc.gcsmsys.domain.*;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 */
@Repository
public interface ActivityListMapper extends BaseMapper<ActivityList> {

    IPage<ActivityList> findUserActList(IPage<ActivityList> iPage, Integer userId);
}
