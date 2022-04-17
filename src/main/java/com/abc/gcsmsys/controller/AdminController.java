package com.abc.gcsmsys.controller;

import com.abc.gcsmsys.domain.AddrVo;
import com.abc.gcsmsys.domain.Admin;
import com.abc.gcsmsys.excepetion.GlobalException;
import com.abc.gcsmsys.service.AdminService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;


@Controller
@Api(tags = "管理员管理")
public class AdminController extends BaseController{

    @Autowired
    private AdminService adminService;

    @GetMapping("/gcsm/index")
    public String index() throws Exception{//主页
        return "backstage/index";
    }

    @PostMapping("/login")
    public String login(Admin admin, Model model, HttpSession session) throws Exception{

        try {
            Admin adminInfo = adminService.checkLogin(admin);
            session.setAttribute("adminInfo",adminInfo);//管理员信息存到session中
        }catch (GlobalException e){
            model.addAttribute("err",e.getMessage());
            return "backstage/login";
        }

        return "redirect:/gcsm/index";
    }

    @GetMapping("/logOut")
    public String logOut(HttpSession session) throws Exception{
        session.removeAttribute("adminInfo");
        return "backstage/login";
    }


}
