package com.abc.gcsmsys.service;

import com.abc.gcsmsys.domain.Appeal;
import com.abc.gcsmsys.domain.AppealSearch;
import com.abc.gcsmsys.utils.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * @Description
 */
public interface AppealService {

    Result userAppeal(Appeal appeal);

    IPage<Appeal> findUserAppeal(Integer pageNum, Integer pageSize, AppealSearch search);

    Result appealById(Integer appId);

    Result replyAppeal(Appeal appeal);

    List<Appeal> userAppealById(Integer userId);
}
