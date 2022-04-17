package com.abc.gcsmsys.service;


import com.abc.gcsmsys.domain.OrdersSearch;
import com.abc.gcsmsys.domain.Serve;
import com.abc.gcsmsys.domain.ServeSearch;
import com.abc.gcsmsys.domain.ServeType;
import com.abc.gcsmsys.utils.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Description
 */
public interface ServeService {

//    服务类型
    List<ServeType> loadServeTypeName();

    Result checkServeType(String name);

    void savaType(ServeType type);

//    服务
    Result savaServe(Serve serve);

    IPage findServeInfo(Integer pageNum, Integer pageSize, ServeSearch search);

//    社区服务订单后台
    IPage serveOrdersPage(Integer pageNum, Integer pageSize, OrdersSearch search);

    Serve serveById(Integer serveId);

    void updateServe(Serve serve);

}
