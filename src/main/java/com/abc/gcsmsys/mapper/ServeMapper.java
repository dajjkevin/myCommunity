package com.abc.gcsmsys.mapper;

import com.abc.gcsmsys.domain.Resident;
import com.abc.gcsmsys.domain.ResidentSearch;
import com.abc.gcsmsys.domain.Serve;
import com.abc.gcsmsys.domain.ServeSearch;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Repository;

/**
 * @Description
 */
@Repository
public interface ServeMapper extends BaseMapper<Serve> {

    IPage<Serve>  findServeInfo(IPage<Serve> iPage, ServeSearch search);

    Serve serveById(Integer serveId);
}
