package com.abc.gcsmsys.controller;

import com.abc.gcsmsys.domain.Appeal;
import com.abc.gcsmsys.domain.AppealSearch;
import com.abc.gcsmsys.domain.User;
import com.abc.gcsmsys.service.AppealService;
import com.abc.gcsmsys.utils.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class AppealController extends BaseController{

    @Autowired
    private AppealService appealService;

    @PostMapping("/user/userAppeal")
    @ResponseBody
    public Result userAppeal(Appeal appeal,String details) throws Exception{

        appeal.setDetail(details);
        Result result = appealService.userAppeal(appeal);
        return result;
    }

    @GetMapping("/gcsm/loadAppeal")
    public String loadAppeal(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                             AppealSearch appealSearch,Model model) throws Exception{

        IPage<Appeal> userAppeal = appealService.findUserAppeal(pageNum, pageSize, appealSearch);

        model.addAttribute("replyStatus",appealSearch.getReplyStatus());
        model.addAttribute("userAppeal",userAppeal);

        return "backstage/appeal/load_appeal";
    }

    @GetMapping("/user/appealById/{appId}")
    @ResponseBody
    public Result appealById(@PathVariable Integer appId) throws Exception{
        Result result = appealService.appealById(appId);
        return result;
    }

    @PostMapping("/gcsm/replyAppeal")
    @ResponseBody
    public Result replyAppeal(Appeal appeal,String content) throws Exception{

        appeal.setDealWith(content);
        appeal.setReplyStatus("1");
        Result result = appealService.replyAppeal(appeal);

        return result;
    }

    @GetMapping("/user/userAppealInfo")
    public String userAppeal(Model model, HttpSession session) throws Exception{

        User userFrontDesk = (User)session.getAttribute("userFrontDesk");
        Integer userId = userFrontDesk.getUserId();
        List<Appeal> appeals = appealService.userAppealById(userId);

        model.addAttribute("myAppealById",appeals);
        return "frontdesk/gcsmPage/actPage/myAppeal";
    }

}
