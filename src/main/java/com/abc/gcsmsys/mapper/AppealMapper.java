package com.abc.gcsmsys.mapper;

import com.abc.gcsmsys.domain.Appeal;
import com.abc.gcsmsys.domain.AppealSearch;
import com.abc.gcsmsys.domain.Resident;
import com.abc.gcsmsys.domain.ResidentSearch;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Repository;

/**
 * @Description
 */
@Repository
public interface AppealMapper extends BaseMapper<Appeal> {

    IPage<Appeal> findUserAppeal(IPage<Appeal> iPage, AppealSearch search);
}
