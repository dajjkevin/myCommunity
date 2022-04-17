package com.abc.gcsmsys.controller;

import com.abc.gcsmsys.domain.Evaluate;
import com.abc.gcsmsys.domain.Orders;
import com.abc.gcsmsys.domain.Serve;
import com.abc.gcsmsys.service.EvaluateService;
import com.abc.gcsmsys.service.ServeService;
import com.abc.gcsmsys.utils.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/user")
public class EvaluateController extends BaseController{

    @Autowired
    private EvaluateService evaluateService;


    @GetMapping("/evaluateIndex/{orderId}")
    public String evaluateIndex(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize",defaultValue = "3") Integer pageSize,
                                @PathVariable Integer orderId, Model model) throws Exception{

        Orders evalByOrderId = evaluateService.getEvalByOrderId(orderId);
        model.addAttribute("evalByOrderId",evalByOrderId);

        IPage<Evaluate> allEvaluate = evaluateService.findUserEvaluate(pageNum, pageSize);
        model.addAttribute("allEvaluate",allEvaluate);

        return "frontdesk/gcsmPage/actPage/orderEvaluate";
    }


    @PostMapping("/userAddEvaluate")
    @ResponseBody
    public Result userAddEvaluate(Evaluate evaluate) throws Exception{

        Result result = evaluateService.userAddEvaluate(evaluate);
        return result;
    }

    @GetMapping("/userEvalById")
    @ResponseBody
    public Result userEvalById(String eId) throws Exception{
        Evaluate evaluate = evaluateService.userEvalById(eId);
        return Result.success(evaluate);
    }

    @GetMapping("/userListEval")
    @ResponseBody
    public Result userListEval() throws Exception{

        List<Evaluate> evaluates = evaluateService.userListEval();
        return Result.success(evaluates);
    }
}
