package com.abc.gcsmsys.handler;



import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Description
 */
public class StringArrayTypeHandler extends BaseTypeHandler<String[]> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String[] parameter, JdbcType jdbcType) throws SQLException {

        //插入不需要转义 //parameter存储过程过如果有[] 就会执行该方法
        StringBuilder sb = new StringBuilder();
        for(String str:parameter)
            sb.append(str).append("|");

        if(sb.length()>0) sb.deleteCharAt(sb.length()-1);

        ps.setString(i,sb.toString());

        System.out.println("数据库插入| 成功");
    }

    @Override
    public String[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        //取数据库字段名字
        String data = rs.getString(columnName);
        return data.split("\\|");
    }

    @Override
    public String[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        //取下标
        String data = rs.getString(columnIndex);
        return data.split("\\|");
    }

    @Override
    public String[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {

        /**
         * CallableStatement接口可以接受运行时输入参数。

         CallableStatement用于执行数据库存储过程。

         Connection对象也可以创建CallableStatement来调用数据库存储过程。
         */

        String data = cs.getString(columnIndex);
        return data.split("\\|");
    }
}
