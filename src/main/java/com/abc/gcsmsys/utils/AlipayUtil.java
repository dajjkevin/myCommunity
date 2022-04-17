//package com.abc.gcsmsys.utils;
//
//import com.abc.gcsmsys.config.AlipayConfig;
//import com.abc.gcsmsys.domain.AlipayBean;
//import com.abc.gcsmsys.domain.CancelOrderDto;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.alipay.api.AlipayApiException;
//import com.alipay.api.request.AlipayTradePagePayRequest;
//import com.alipay.api.request.AlipayTradeRefundRequest;
//import com.alipay.api.response.AlipayTradeRefundResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.math.BigDecimal;
//import java.util.Map;
//
//
// /*
//  * @Description:
//  * @Author  oxl
//  * @Date 2022-4-1 20:38
//  */
//@Component
//public class AlipayUtil {
//
//    @Autowired
//    AlipayConfig alipayConfig;
//
//
//    public  String connect(AlipayBean alipayBean) throws AlipayApiException {
//        //2、设置请求参数
//        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
//        //页面跳转同步通知页面路径
//        alipayRequest.setReturnUrl(alipayConfig.getReturn_url());
//        // 服务器异步通知页面路径
//        alipayRequest.setNotifyUrl(alipayConfig.getNotify_url());
//        //封装参数
//        alipayRequest.setBizContent(JSON.toJSONString(alipayBean));
//
//        //3、请求支付宝进行付款，并获取支付结果
//        String result = alipayConfig.alipay().pageExecute(alipayRequest).getBody();
//
//        //返回付款信息
//        return result;
//    }
//
//    public void check(Map<String, String> params) throws AlipayApiException {
//
//        if (!params.get("app_id").equals(alipayConfig.getApp_id())) {
//            throw new AlipayApiException("app_id不一致");
//        }
//    }
//
//    public Boolean cancelOrder(CancelOrderDto cancelOrder){
//        java.text.DecimalFormat df =new java.text.DecimalFormat("#.00");
//        BigDecimal bigDecimal = BigDecimal.valueOf(Double.parseDouble(df.format(cancelOrder.getAmount())));
//        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
//        JSONObject bizContent = new JSONObject();
//        bizContent.put("out_trade_no", cancelOrder.getorderNo());
//        bizContent.put("refund_amount", bigDecimal);
//        request.setBizContent(bizContent.toString());
//        AlipayTradeRefundResponse response = null;
//        try {
//            response = alipayConfig.alipay().execute(request);
//        } catch (AlipayApiException e) {
//            e.printStackTrace();
//        }
//        if(response.isSuccess()){
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//
//}
