package com.abc.gcsmsys.controller;

import cn.hutool.core.util.IdUtil;
import com.abc.gcsmsys.domain.AddrVo;
import com.abc.gcsmsys.domain.AlipayBean;
import com.abc.gcsmsys.domain.Orders;
import com.abc.gcsmsys.domain.User;
import com.abc.gcsmsys.service.AlipayService;
import com.abc.gcsmsys.service.OrderService;
import com.abc.gcsmsys.utils.MapCovert;
import com.abc.gcsmsys.utils.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;


@Controller
@RequestMapping("/user")
public class OrderController extends BaseController{

    @Autowired
    private AlipayService alipayService;

    @Autowired
    private OrderService orderService;

    @Autowired
    MapCovert mapCovert;



    //    @GetMapping("/ali")
//    public String toAli() throws Exception{
//        return "order/orderTest";
//    }

    @RequestMapping("/pay")
    @ResponseBody
    public Result payController(@RequestParam("typeName") String typeName, Model model, Orders order, AddrVo addrVo, HttpSession session) throws Exception {

        String orderNo  = IdUtil.objectId();//hutool生成订单id
        Double payMoney = Double.valueOf(order.getMoney());
        order.setOrderNo(orderNo);
        order.setOrderAddr(addrVo.houAddr());
        order.setOrderName(typeName);

        orderService.addServeOrder(order);
        AlipayBean alipayBean = new AlipayBean();
        alipayBean.setOrderNo(orderNo);
        alipayBean.setTypeName(typeName);
        alipayBean.setPayMoney(payMoney);

        session.setAttribute("alipayBean",alipayBean);

        return Result.success();
    }

    @RequestMapping("/pay/index")
    public String payIndex(Model model,HttpSession session) throws Exception{

        AlipayBean alipayBean = (AlipayBean)session.getAttribute("alipayBean");
        String orderNo = alipayBean.getOrderNo();
        String typeName = alipayBean.getTypeName();
        Double payMoney = alipayBean.getPayMoney();

        String pays = alipayService.webPagePay(orderNo, payMoney, typeName);
        model.addAttribute("pays",pays);
        return "frontdesk/gcsmPage/actPage/payOrder";
    }

    @RequestMapping("/returnNotifyUrl")
    public void returnOrder(HttpServletRequest request)  {
        Map map=mapCovert.toMap(request);
        orderService.returnOrder(map);
    }

    @RequestMapping("/returnUserOrders")
    public String returnUserOrders(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                   @RequestParam(value = "pageSize",defaultValue = "4") Integer pageSize,
                                   @RequestParam(value = "orderName",defaultValue = "") String orderName ,
                                   HttpSession session,Model model) throws Exception{

        User userFrontDesk = (User)session.getAttribute("userFrontDesk");
        if (userFrontDesk==null || userFrontDesk.equals("")){
            return "redirect:/user";
        }
        Integer userId = userFrontDesk.getUserId();

        IPage<Orders> userOrders = orderService.findUserOrders(pageNum, pageSize, userId, orderName);
        model.addAttribute("userOrders",userOrders);

        return "frontdesk/gcsmPage/actPage/myOrder";
    }

    @GetMapping("/userOrderDtl/{orderNo}")
    @ResponseBody
    public Result userOrderDtl(@PathVariable String orderNo) throws Exception{

        Orders orders = orderService.orderById(orderNo);
        return Result.success(orders);
    }

    @RequestMapping("/pay/resPay")
    @ResponseBody
    public Result resPay(Model model,
                         @RequestParam("orderNo") String orderNo,
                         @RequestParam("orderName") String orderName,
                         @RequestParam("money") String money,HttpSession session,AlipayBean alipayBean) throws Exception{

        alipayBean.setOrderNo(orderNo);
        alipayBean.setTypeName(orderName);
        alipayBean.setPayMoney(Double.parseDouble(money));
        session.setAttribute("alipayBean",alipayBean);
        return Result.success();
    }

    @GetMapping("/userOrdersCount")
    @ResponseBody
    public Result userOrdersCount(@RequestParam("sesUserId") Integer userId) throws Exception{

        Integer integer = orderService.orderCount(userId);
        return Result.success(integer);
    }

    @GetMapping("/orderStatus")
    @ResponseBody
    public Result orderFinsh(@RequestParam("method") String method,
                             @RequestParam("orderNo") String orderNo) throws Exception{

        Result result = orderService.orderStatus(method,orderNo);
        return result;
    }




}
