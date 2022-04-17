package com.abc.gcsmsys.utils;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

 /*
  * @Description:
  * @Author  oxl
  * @Date 2022-4-1 19:19
  */
@Component
public class MapCovert {

    public Map<String, String> toMap(HttpServletRequest request){
        Map<String, String> returnMap = new HashMap<>();
        //获取支付宝POST过来反馈信息转换为Entry
        Set<Map.Entry<String, String[]>> entries = request.getParameterMap().entrySet();
        // 遍历
        for (Map.Entry<String, String[]> entry : entries) {
            String key = entry.getKey();
            StringBuffer value = new StringBuffer("");
            String[] val = entry.getValue();
            if (null != val && val.length > 0) {
                for (String v : val) {
                    value.append(v);
                }
            }
            returnMap.put(key, value.toString());
        }
        return returnMap;
    }
}
