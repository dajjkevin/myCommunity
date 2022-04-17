package com.abc.gcsmsys.controller;

import com.abc.gcsmsys.domain.*;
import com.abc.gcsmsys.service.AdminService;
import com.abc.gcsmsys.utils.Result;
import com.abc.gcsmsys.utils.TencentCOSUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class GrassRootsController extends BaseController{

    @Autowired
    private AdminService adminService;

    @GetMapping("/gcsm/addGrassRoots")
    public String grassRoots() throws Exception{
        return "backstage/grassRoots/create_grassRoots";
    }

    @PostMapping("/gcsm/createGrassRoots")
    public String createGrassRoots(Admin admin, MultipartFile adminImg, AddrVo addrVo) throws Exception{

        if (adminImg != null){
            admin.setAdminAvatar("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F0165cb5d14565ca8012155290a6d86.png%402o.png&refer=http%3A%2F%2Fimg.zcool.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1648213883&t=ae062e4657838e19ca98f0d8999e7456");
        }else{
            String uploadfileImg = TencentCOSUtil.uploadfile(adminImg);
            System.out.println(uploadfileImg);
            admin.setAdminAvatar(uploadfileImg);
        }

        admin.setAddr(addrVo.houAddr());
        adminService.addGrassRoots(admin);

        return "redirect:/gcsm/loadGrassRoots";
    }

    @GetMapping("/gcsm/loadGrassRoots")
    public String loadGrassRoots(Model model, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                 @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                 AdminSearch adminSearch) throws Exception{
        //方便回显搜索内容
        model.addAttribute("sPoliticalIdentity",adminSearch.getSPoliticalIdentity());
        model.addAttribute("sStatus",adminSearch.getSStatus());

        Page pageAdmin = adminService.findPageAdmin(pageNum, pageSize, adminSearch);
        model.addAttribute("pageAdmin", pageAdmin);

        return "backstage/grassRoots/load_grassRoots";
    }

    @GetMapping("/gcsm/getByAdminId/{adminId}")
    @ResponseBody
    public Result getByAdminId(@PathVariable Integer adminId) throws Exception{

        Admin byAdminId = adminService.getByAdminId(adminId);
        return Result.success(byAdminId);
    }

    @PostMapping("/gcsm/updateGrassRoots")
    public String updateGrassRoots(Admin admin,AddrVo addrVo,MultipartFile adminImg) throws Exception{

        //看看文件名是否等于空，为空才更新头像
        String originalFilename = adminImg.getOriginalFilename();

        if (!"".equals(originalFilename)){
            String uploadfileImg = TencentCOSUtil.uploadfile(adminImg);
            System.out.println(uploadfileImg);
            admin.setAdminAvatar(uploadfileImg);
        }

        admin.setAddr(addrVo.houAddr());
        adminService.updateAdmin(admin);
        return "redirect:/gcsm/loadGrassRoots";
    }

    @GetMapping("/gcsm/changgeAdminStatus")
    @ResponseBody
    public Result changgeAdminStatus(@RequestParam("adminId") Integer adminId,
                                     @RequestParam("adminStatus") String adminStatus) throws Exception{

        adminService.changeAdminStatus(adminId,adminStatus);
        return Result.success();
    }



}
