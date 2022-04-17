package com.abc.gcsmsys.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import com.abc.gcsmsys.domain.*;
import com.abc.gcsmsys.mapper.UserMapper;
import com.abc.gcsmsys.service.*;
import com.abc.gcsmsys.utils.Result;
import com.abc.gcsmsys.utils.TencentCOSUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Description 用户
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ServeService serveService;

    @Autowired
    private BulletinService bulletinService;

    @Autowired
    private AppealService appealService;

    @Autowired
    private OrderService orderService;

    @RequestMapping("")
    public String LoginIndex(HttpSession session) throws Exception{//登录主页

        Object userFrontDesk = session.getAttribute("userFrontDesk");
        if (userFrontDesk == null || "".equals(userFrontDesk)){
            session.removeAttribute("userFrontDesk");
        }else if (!userFrontDesk.equals("") || userFrontDesk != null){
            return "redirect:/user/index";
        }
        return "frontdesk/log-in";
    }

    @RequestMapping("/regi")
    public String registerIndex() throws Exception{//注冊
        return "frontdesk/register";
    }

    @RequestMapping("/index")
    public String gcsmIndex(Model model, ServeSearch serveSearch,HttpSession session) throws Exception{//首页

        IPage serveInfo = serveService.findServeInfo(1, 4, serveSearch);
        model.addAttribute("serveInfo",serveInfo);

        Page bulletin = bulletinService.findBulletin(1, 7, "");
        model.addAttribute("bulletin", bulletin);

        AppealSearch appealSearch = new AppealSearch();
        IPage<Appeal> userAppeal = appealService.findUserAppeal(1, 7, appealSearch);
        model.addAttribute("userAppeal",userAppeal);

        return "frontdesk/gcsmPage/index";
    }

    @PostMapping("/login")
    @ResponseBody
    public Result toLogin(@RequestParam("name") String name, @RequestParam("pwd") String pwd, HttpSession session) throws Exception{

        Result result = userService.checkUserNameAndPwd(name, pwd);

        System.out.println(result.getData()+"用户信息");
        if ("0".equals(result.getCode())){
            session.setAttribute("userFrontDesk",result.getData());
        }

        return result;
    }

    @PostMapping("/register")
    @ResponseBody
    public Result register(User user) throws Exception{

        User userIn = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUserName, user.getUserName()));
        if (userIn == null){
            Result register = userService.register(user);
            return Result.success();
        }
        return Result.error("-1","用户名已存在，请重新输入");
    }

//    后台添加用户 ↓

    @RequestMapping("/addUser")
    public String addUser() throws Exception{
        return "backstage/user/create_user";
    }

    @PostMapping("/savaUser")
    @ResponseBody
    public Result addUser(User user, AddrVo addrVo, MultipartFile userImg) throws Exception{

        User userByName = userService.getUserByName(user.getUserName());
        if (userByName != null){
            return Result.error("-1","添加用户名已存在");
        }else{
            if (userImg == null){
                user.setUserAvatar("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fi.qqkou.com%2Fi%2F0a1406481394x2846025398b26.jpg&refer=http%3A%2F%2Fi.qqkou.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1649253947&t=deede54935f706da8c8eee1dccfb345c");
            }else{
                String originalFilename = userImg.getOriginalFilename();

                if (!"".equals(originalFilename)){
                    String uploadfileImg = TencentCOSUtil.uploadfile(userImg);
                    System.out.println(uploadfileImg);
                    user.setUserAvatar(uploadfileImg);
                }
            }
        }

        user.setUserAddr(addrVo.houAddr());
        userService.saveUser(user);
        return Result.success();
    }

    @GetMapping("/loadUsers")
    public String loadGrassRoots(Model model, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                 @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                 UserSearch userSearch) throws Exception{
        //方便回显搜索内容
        model.addAttribute("sStatus",userSearch.getSStatus());

        Page pageUser = userService.findPageUser(pageNum, pageSize, userSearch);
        model.addAttribute("pageUser", pageUser);

        return "backstage/user/load_user";
    }

    @GetMapping("/getByUserId/{userId}")
    @ResponseBody
    public Result getByUserId(@PathVariable Integer userId) throws Exception{
        User byUserId = userService.getByUserId(userId);
        return Result.success(byUserId);
    }

    @GetMapping("/userById")
    public String userById(HttpSession session,Model model) throws Exception{

        User userFrontDesk = (User)session.getAttribute("userFrontDesk");
        Integer userId = userFrontDesk.getUserId();
        User byUserId = userService.getByUserId(userId);
        model.addAttribute("userInfo",byUserId);

        return "frontdesk/gcsmPage/actPage/personalCenter";
    }

    @PostMapping("/upUser")
    @ResponseBody
    public Result updateUser(User user,MultipartFile userImg,AddrVo addrVo) throws Exception{

        if (userImg == null){
            User byUserId = userService.getByUserId(user.getUserId());
            if (StringUtils.isNotBlank(byUserId.getUserAvatar())){
                user.setUserAvatar(byUserId.getUserAvatar());
            }else{
                user.setUserAvatar("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fi.qqkou.com%2Fi%2F0a1406481394x2846025398b26.jpg&refer=http%3A%2F%2Fi.qqkou.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1649253947&t=deede54935f706da8c8eee1dccfb345c");
            }
        }else{
            String originalFilename = userImg.getOriginalFilename();
            if (!"".equals(originalFilename)){
                String uploadfileImg = TencentCOSUtil.uploadfile(userImg);
                System.out.println(uploadfileImg);
                user.setUserAvatar(uploadfileImg);
            }
        }

        user.setUserAddr(addrVo.houAddr());
        userService.updateUser(user);

        return Result.success();
    }

    @GetMapping("/changeUserStatus")
    @ResponseBody
    public Result changeUserStatus(@RequestParam("userId") Integer userId,
                                   @RequestParam("userStatus") String userStatus) throws  Exception{

        userService.changeStatus(userId,userStatus);
        return Result.success();
    }

    @GetMapping("/userLoginOut")
    @ResponseBody
    public Result userLoginOut(HttpSession session) throws Exception{

        session.removeAttribute("userFrontDesk");
        return Result.success();
    }

    @PostMapping("/updatePwd")
    @ResponseBody
    public Result userUpdatePwd(User user) throws Exception{
        Result result = userService.userUpdatePwd(user);
        return result;
    }

}
