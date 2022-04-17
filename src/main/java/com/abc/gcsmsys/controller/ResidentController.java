package com.abc.gcsmsys.controller;

import com.abc.gcsmsys.domain.AddrVo;
import com.abc.gcsmsys.domain.HouseHold;
import com.abc.gcsmsys.domain.Resident;
import com.abc.gcsmsys.domain.ResidentSearch;
import com.abc.gcsmsys.mapper.ResidentMapper;
import com.abc.gcsmsys.service.HouseHoldService;
import com.abc.gcsmsys.service.ResidentService;
import com.abc.gcsmsys.utils.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ResidentController extends BaseController{

    @Autowired
    private ResidentService residentService;

    @Autowired
    private HouseHoldService houseHoldService;

    @RequestMapping("/gcsm/addResident")
    private String createHouse(Model model) throws Exception{ //添加

        List<HouseHold> houseHolds = houseHoldService.HouseHoldInfo();
        model.addAttribute("houseHoldInfo",houseHolds);

        return "backstage/resident/createResident";
    }

    @PostMapping("/savaResident")
    private String savaResident(Resident resident, AddrVo addrVo) throws Exception{

        resident.setHouseAddr(addrVo.houAddr());
        residentService.addResident(resident);

        return "redirect:/gcsm/residentInfo";
    }

    @GetMapping("/gcsm/residentInfo")
    public String loadResident(Model model, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                               ResidentSearch search) throws Exception{

        IPage<Resident> pageResi = residentService.findPageResi(pageNum, pageSize, search);
        model.addAttribute("sJudge",search.getSJudge());
        model.addAttribute("pageResident", pageResi);

        return "backstage/resident/loadResident";

    }

    @GetMapping("/gcsm/residentByPersonId")
    @ResponseBody
    public Result residentByPersonId(ResidentSearch upData,Model model) throws Exception{

        System.out.println(upData);
        Resident resident = residentService.residentByPersonId(upData);
        List<HouseHold> houseHolds = houseHoldService.HouseHoldInfo();

        return Result.success(resident,houseHolds);
    }

    @PostMapping("/gcsm/updateResindent")
    public String updateResindent(Resident resident,AddrVo addrVo) throws Exception{

        resident.setHouseAddr(addrVo.houAddr());
        residentService.updateResident(resident);
        return "redirect:/gcsm/residentInfo";
    }

}
