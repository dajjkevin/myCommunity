package com.abc.gcsmsys.service;

import com.abc.gcsmsys.domain.Bulletin;
import com.abc.gcsmsys.utils.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @Description
 */
public interface BulletinService {

    void addBulletin(Bulletin bulletin);

    Page findBulletin(Integer pageNum,Integer pageSize,String theme);

    Result changeBulView(Bulletin bulletin);

    Bulletin bulById(Integer bulltinId);

    void updateBul(Bulletin bulletin);
}
