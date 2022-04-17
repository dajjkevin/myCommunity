package com.abc.gcsmsys.mapper;

import com.abc.gcsmsys.domain.ActSearch;
import com.abc.gcsmsys.domain.Activity;
import com.abc.gcsmsys.domain.Resident;
import com.abc.gcsmsys.domain.ResidentSearch;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Repository;

/**
 * @Description
 */
@Repository
public interface ActivityMapper extends BaseMapper<Activity> {

    IPage<Activity> findUserPageAct(IPage<Activity> iPage, ActSearch actSearch);

}
