package com.abc.gcsmsys.service.impl;

import com.abc.gcsmsys.domain.*;
import com.abc.gcsmsys.mapper.OrderMapper;
import com.abc.gcsmsys.mapper.ServeMapper;
import com.abc.gcsmsys.mapper.ServeTypeMapper;
import com.abc.gcsmsys.service.ServeService;
import com.abc.gcsmsys.utils.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 */
@Service
public class ServeServiceImpl implements ServeService {

    @Autowired
    private ServeTypeMapper ServeTypeMapper;

    @Autowired
    private ServeMapper serveMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<ServeType> loadServeTypeName() {
        List<ServeType> serveTypes = ServeTypeMapper.selectList(null);
        return serveTypes;
    }

    @Override
    public Result checkServeType(String name) {
        ServeType serveType = ServeTypeMapper.selectOne(Wrappers.<ServeType>lambdaQuery().eq(ServeType::getTypeName, name));
        if (serveType != null){
            return Result.error("-1","该类型已经存在");
        }
        return Result.success();
    }

    @Override
    public void savaType(ServeType type) {
        ServeTypeMapper.insert(type);
    }

    @Override
    public Result savaServe(Serve serve) {
        serveMapper.insert(serve);
        return Result.success();
    }

    @Override
    public IPage<Serve> findServeInfo(Integer pageNum, Integer pageSize, ServeSearch search) {

        IPage pageData = serveMapper.findServeInfo(new Page<Serve>(pageNum, pageSize), search);
        return pageData;
    }

    @Override
    public IPage serveOrdersPage(Integer pageNum, Integer pageSize, OrdersSearch search) {
        IPage pageData = orderMapper.serveOrdersPage(new Page<Orders>(pageNum, pageSize), search);
        return pageData;
    }

    @Override
    public Serve serveById(Integer serveId) {
        Serve serve = serveMapper.serveById(serveId);
        return serve;
    }

    @Override
    public void updateServe(Serve serve) {
        serveMapper.updateById(serve);
    }


}
