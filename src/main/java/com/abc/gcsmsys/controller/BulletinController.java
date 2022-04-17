package com.abc.gcsmsys.controller;

import com.abc.gcsmsys.domain.Bulletin;
import com.abc.gcsmsys.service.BulletinService;
import com.abc.gcsmsys.utils.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class BulletinController extends BaseController{

    @Autowired
    private BulletinService bulletinService;

    @GetMapping("/gcsm/addBulletinIndex")
    public String addBulletinIndex() throws Exception{

        return "backstage/bulletin/create_bulletin";
    }

    @PostMapping("/gcsm/addBulletin")
    public String addBulletin(Bulletin bulletin,String contents) throws Exception{

        bulletin.setContent(contents);
        bulletinService.addBulletin(bulletin);

        return "backstage/bulletin/create_bulletin";
    }

    @GetMapping("/gcsm/loadBulletin")
    public String loadBulletin(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                               String theme, Model model) throws Exception{

        Page bulletin = bulletinService.findBulletin(pageNum, pageSize, theme);
        model.addAttribute("bulletin", bulletin);

        return "backstage/bulletin/load_bulletin";
    }

    @GetMapping("/gcsm/changeBulView")
    @ResponseBody
    public Result changeBulView(Bulletin bulletin) throws Exception{
        Result result = bulletinService.changeBulView(bulletin);
        return result;
    }

    @GetMapping("/gcsm/upBulltin/{bulltinId}")
    public String upBulltin(@PathVariable Integer bulltinId,Model model) throws Exception{

        Bulletin bulletin = bulletinService.bulById(bulltinId);
        model.addAttribute("bulById",bulletin);

        return "backstage/bulletin/update_bulletin";
    }

    @PostMapping("/gcsm/updateBul")
    public String updateBul(Bulletin bulletin,String contents) throws Exception{

        bulletin.setContent(contents);
        bulletinService.updateBul(bulletin);
        return "redirect:/gcsm/loadBulletin";
    }

    @GetMapping("/gcsm/bulletinClick/{bulId}")
    @ResponseBody
    public Result bulletinClick(@PathVariable Integer bulId) throws Exception{

        Bulletin bulletin = bulletinService.bulById(bulId);
        return Result.success(bulletin);
    }
}
