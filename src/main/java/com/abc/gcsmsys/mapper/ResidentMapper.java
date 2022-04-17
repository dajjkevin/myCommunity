package com.abc.gcsmsys.mapper;

import com.abc.gcsmsys.domain.Resident;
import com.abc.gcsmsys.domain.ResidentSearch;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Repository;

/**
 * @Description
 */
@Repository
public interface ResidentMapper extends BaseMapper<Resident> {

    IPage<Resident> findPageResi(IPage<Resident> iPage, ResidentSearch search);

    Resident residentByPersonId(ResidentSearch upData);
}
