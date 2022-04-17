package com.abc.gcsmsys.utils;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author surface
 * @Date 2022-1-24 17:52
 * @Description  时间自动化填充处理
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    //插入操作
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill....");
//       源码 String fieldName 字段名, Object fieldVal 插入的字段, MetaObject metaObject要给哪个数据处理
        this.setFieldValByName("registDate",new Date(),metaObject);//登记时间
        this.setFieldValByName("appTime",new Date(),metaObject);//述求/建议创建时间
        this.setFieldValByName("createTime",new Date(),metaObject);//订单创建时间
        this.setFieldValByName("eTime",new Date(),metaObject);//评价时间
    }

    //修改操作
    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
