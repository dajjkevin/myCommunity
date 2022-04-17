package com.abc.gcsmsys.service;

import com.abc.gcsmsys.domain.Resident;
import com.abc.gcsmsys.domain.ResidentSearch;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.text.ParseException;
import java.util.List;

/**
 * @Description
 */
public interface ResidentService {

    /**添加居民*/
    void addResident(Resident resident) throws Exception;

    /***
     *
     * @param pageNum
     * @param pageSize
     * @param search 搜索条件
     * @return
     */
    Page findPageResident(Integer pageNum, Integer pageSize, String search); //暂时不用

    IPage<Resident> findPageResi(Integer pageNum, Integer pageSize, ResidentSearch search);

    Resident residentByPersonId(ResidentSearch upData);

    void updateResident(Resident resident);

    List<Resident> loadResident();
}
