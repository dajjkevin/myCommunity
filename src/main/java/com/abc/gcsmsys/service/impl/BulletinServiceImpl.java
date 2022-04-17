package com.abc.gcsmsys.service.impl;

import cn.hutool.core.util.StrUtil;
import com.abc.gcsmsys.domain.Activity;
import com.abc.gcsmsys.domain.Bulletin;
import com.abc.gcsmsys.mapper.BulletinMapper;
import com.abc.gcsmsys.service.BulletinService;
import com.abc.gcsmsys.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description
 */
@Service
public class BulletinServiceImpl implements BulletinService {

    @Autowired
    private BulletinMapper bulletinMapper;

    @Override
    public void addBulletin(Bulletin bulletin) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String insertTime = sdf.format(new Date());
        bulletin.setReleaseTime(insertTime);
        bulletinMapper.insert(bulletin);
    }

    @Override
    public Page findBulletin(Integer pageNum, Integer pageSize, String theme) {

        QueryWrapper<Bulletin> wrapper = new QueryWrapper<>();
        wrapper.eq("view","0");

        if (!StrUtil.isBlank(theme)){
            wrapper.like("bulletin_theme", theme);
        }

        Page<Bulletin> bulletinInfo = bulletinMapper.selectPage(new Page<>(pageNum, pageSize), wrapper.orderByDesc("bulletin_id"));

        return bulletinInfo;
    }

    @Override
    public Result changeBulView(Bulletin bulletin) {
        bulletinMapper.updateById(bulletin);
        return Result.success();
    }

    @Override
    public Bulletin bulById(Integer bulletinId) {
        Bulletin bulletin = bulletinMapper.selectOne(Wrappers.<Bulletin>lambdaQuery().eq(Bulletin::getBulletinId, bulletinId));
        return bulletin;
    }

    @Override
    public void updateBul(Bulletin bulletin) {
        bulletinMapper.updateById(bulletin);
    }
}
