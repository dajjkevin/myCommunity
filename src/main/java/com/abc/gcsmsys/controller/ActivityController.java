package com.abc.gcsmsys.controller;

import com.abc.gcsmsys.domain.*;
import com.abc.gcsmsys.service.ActivityListService;
import com.abc.gcsmsys.service.ActivityService;
import com.abc.gcsmsys.service.UserHeartService;
import com.abc.gcsmsys.utils.Result;
import com.abc.gcsmsys.utils.TencentCOSUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;


@Controller
public class ActivityController extends BaseController{

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ActivityListService activityListService;

    @Autowired
    private UserHeartService userHeartService;

    @GetMapping("/gcsm/actIndex")
    public String actIndex() throws Exception{
        return "backstage/activity/create_activity";
    }

    @PostMapping("/gcsm/savaAct")
    @ResponseBody
    public Result savaAct(Activity activity, MultipartFile actPic,String content) throws Exception{

        if (actPic == null){
            activity.setActImg("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpicnew12.photophoto.cn%2F20171202%2Fchuangjianhexieshehui-29233528_1.jpg&refer=http%3A%2F%2Fpicnew12.photophoto.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1649639292&t=4f104a839465f0c86b871a84c1d0e927");
        }else{
            String uploadfileActImg = TencentCOSUtil.uploadfile(actPic);
            System.out.println(uploadfileActImg);
            activity.setActImg(uploadfileActImg);
        }
        activity.setActDtl(content);
        activityService.savaAct(activity);

        return Result.success();
    }

    @GetMapping("/gcsm/loadAct")
    public String loadAct(Model model, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                 @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                 ActSearch actSearch) throws Exception{
        //方便回显搜索内容
        model.addAttribute("isOpen",actSearch.getIsOpen());

        Page pageUser = activityService.findPageAct(pageNum, pageSize, actSearch);
        model.addAttribute("pageAct", pageUser);

        return "backstage/activity/load_activity";
    }

    @GetMapping("/gcsm/actById/{actId}")
    @ResponseBody
    public Result actById(@PathVariable Integer actId) throws Exception{

        Activity activity = activityService.actById(actId);
        return Result.success(activity);
    }

    @GetMapping("/gcsm/updateAct/{actId}")
    public String updateActIndex(@PathVariable Integer actId,Model model) throws Exception{

        Activity activity = activityService.actById(actId);
        model.addAttribute("act",activity);
        return "backstage/activity/update_activity";
    }

    @PostMapping("/gcsm/updateAct")
    @ResponseBody
    public Result updateAct(Activity activity,MultipartFile actPic,String content) throws Exception{

        if (actPic == null){
            Activity act = activityService.actById(activity.getActId());
            activity.setActImg(act.getActImg());
        }else{
            String uploadfileActImg = TencentCOSUtil.uploadfile(actPic);
            System.out.println(uploadfileActImg);
            activity.setActImg(uploadfileActImg);
        }
        activity.setActDtl(content);
        activityService.updateAct(activity);
        return Result.success();
    }

    @GetMapping("/gcsm/changeOpen")
    @ResponseBody
    public Result changeIsOpen(@RequestParam("actId") Integer actId,@RequestParam("isOpen") String isOpen) throws Exception{

        activityService.changeIsOpen(actId,isOpen);
        return Result.success();
    }


//    前台用户

    @GetMapping("/user/showAct")
    public String showAct(Model model, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                 @RequestParam(value = "pageSize",defaultValue = "4") Integer pageSize,
                                 ActSearch actSearch,HttpSession session) throws Exception{

        IPage<Activity> userPageAct = activityService.findUserPageAct(pageNum, pageSize, actSearch);
        model.addAttribute("userPageAct", userPageAct);

        return "frontdesk/gcsmPage/actPage/showAct";
    }

    @PostMapping("/user/signUpAct")
    @ResponseBody
    public Result signUpAct(@RequestParam("actId") Integer actId, HttpServletRequest request) throws Exception{

        Object userFrontDesk = request.getSession().getAttribute("userFrontDesk");

        if (userFrontDesk == null || userFrontDesk.equals("")){
            return Result.error("-1","请先登录，才能进行报名该活动");
        }

        User user  = (User) userFrontDesk;
        Integer userId = user.getUserId();
        Result result = activityListService.signUpAct(actId, userId);

        return result;
    }

    //个人活动中心
    @GetMapping("/user/myActInfo")
    public String myActInfo(Model model, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                          @RequestParam(value = "pageSize",defaultValue = "4") Integer pageSize,
                            HttpServletRequest request) throws Exception{

        User userFrontDesk = (User)request.getSession().getAttribute("userFrontDesk");

        if (userFrontDesk == null || userFrontDesk.equals("")){
            return "frontdesk/log-in";
        }

        Integer userId = userFrontDesk.getUserId();

//        Page pageToUserId = activityListService.findPageUserAct(pageNum, pageSize,userId);
        IPage<ActivityList> userActList = activityListService.findUserActList(pageNum, pageSize, userId);

        model.addAttribute("pageToUserId", userActList);

        return "frontdesk/gcsmPage/actPage/myActInfo";
    }

    @GetMapping("/user/actById/{actId}")
    @ResponseBody
    public Result userActById(@PathVariable Integer actId) throws Exception{

        Activity activity = activityService.actById(actId);
        return Result.success(activity);
    }

    @PostMapping("/user/actMyHeart")
    @ResponseBody
    public Result actMyHeart(UserHeart heart,HttpServletRequest request) throws Exception{

        User userFrontDesk = (User)request.getSession().getAttribute("userFrontDesk");

        if (userFrontDesk == null || userFrontDesk.equals("")){
            return Result.error("-1","请先登录");
        }
        heart.setUserId(userFrontDesk.getUserId());
        Result result = userHeartService.actHeart(heart);
        return result;
    }

    @GetMapping("/user/userHeart")
    public String userHeart(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                          @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize
                            ,HttpSession session,Model model) throws Exception{

        User userFrontDesk = (User)session.getAttribute("userFrontDesk");
        if (userFrontDesk == null || "".equals(userFrontDesk)){
            return "frontdesk/log-in";
        }

        Integer userId = userFrontDesk.getUserId();
        IPage userHeart = userHeartService.findUserHeart(pageNum, pageSize, userId);
        model.addAttribute("userHeart",userHeart);

        return "frontdesk/gcsmPage/actPage/myHeart";
    }

    @GetMapping("/user/cancelHeart/{heartId}")
    @ResponseBody
    public Result cancelHeart(@PathVariable Integer heartId) throws Exception{

        Result result = userHeartService.cancelHeart(heartId);
        return result;
    }

}
