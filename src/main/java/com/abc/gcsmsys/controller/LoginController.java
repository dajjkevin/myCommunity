package com.abc.gcsmsys.controller;

import com.abc.gcsmsys.service.AdminService;
import com.abc.gcsmsys.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@Api(tags = "登录接口")
public class LoginController {

    @Autowired
    private AdminService adminService;

    @ApiOperation("登录")
    @GetMapping("/tologin")
    public String toLogin(HttpSession session) throws Exception{
        Object adminInfo = session.getAttribute("adminInfo");
        if (adminInfo != null || adminInfo == ""){
            return "redirect:/gcsm/index";
        }
        return "backstage/login";
    }

    @GetMapping("/admin/forget")
    public String forgetAdmin() throws Exception{
        return "backstage/forgetAdmin";
    }

    @ApiOperation("发送邮箱")
    @GetMapping("/create4Email/{email}")
    @ResponseBody
    public Result create4Email(@PathVariable  String email,HttpSession session){

        Result result = adminService.sendCode4Mail(email);

        session.setAttribute("emailCode",result.getData());

        return result;
    }

    /**
     *
     * @param adminEmail 管理员输入的邮箱
     * @param emailCode 验证码
     * @param session 取session中的验证码
     * @param model
     * @return
     * @throws Exception
     */
    @PostMapping("/admin/forgetPwd")
    public String forgetPwd(String adminEmail, String emailCode, Model model, HttpSession session) throws Exception{

        System.out.println(emailCode+"前端的code");
        System.out.println(adminEmail);

        Object sessCode = session.getAttribute("emailCode");

        if (sessCode==null){
            model.addAttribute("err1","前先获取验证码");
        } else if (!sessCode.equals(emailCode)){
            model.addAttribute("err1","验证码错误");
        }else {
            model.addAttribute("success","修改成功");
        }

        return "backstage/forgetAdmin";
    }

}
