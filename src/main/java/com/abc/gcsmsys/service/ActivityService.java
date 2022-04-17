package com.abc.gcsmsys.service;

import com.abc.gcsmsys.domain.ActSearch;
import com.abc.gcsmsys.domain.Activity;
import com.abc.gcsmsys.domain.AdminSearch;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @Description
 */
public interface ActivityService {

    void savaAct(Activity activity);

    Page findPageAct(Integer pageNum, Integer pageSize, ActSearch actSearch);

    IPage<Activity> findUserPageAct(Integer pageNum, Integer pageSize, ActSearch actSearch);

    Activity actById(Integer actId);

    void updateAct(Activity activity);

    /**是否开放*/
    void changeIsOpen(Integer actId,String isOpen);

}
