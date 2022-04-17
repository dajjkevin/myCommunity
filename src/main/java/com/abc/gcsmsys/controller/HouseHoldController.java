package com.abc.gcsmsys.controller;

import com.abc.gcsmsys.domain.*;
import com.abc.gcsmsys.service.HouseHoldService;
import com.abc.gcsmsys.utils.GetAge;
import com.abc.gcsmsys.utils.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@Slf4j
public class HouseHoldController extends BaseController{

    @Autowired
    private HouseHoldService houseHoldService;

    @RequestMapping("/gcsm/addHouse")
    private String createHouse() throws Exception{ //添加
        return "backstage/household/createHouse";
    }

    @PostMapping("/HouseHold")
    public String savaHouseHold(HouseHold houseHold, AddrVo addrVo) throws Exception{

        houseHold.setHouseAddr(addrVo.houAddr());
        houseHoldService.saveHouseHold(houseHold);

        return "redirect:/gcsm/household";
    }


    @GetMapping("/gcsm/household")
    public String loadHouse(Model model, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                            @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                            HouseSearch search) throws Exception{

        //方便回显搜索内容
        model.addAttribute("sGender",search.getSGender());
        model.addAttribute("sProvince",search.getSProvince());

        Page pageHouseHold = houseHoldService.findPageHouseHold(pageNum, pageSize, search);
        model.addAttribute("pageHouse", pageHouseHold);

        return "backstage/household/loadHouse";

    }

    @GetMapping("/gcsm/houseById/{id}")
    @ResponseBody
    public Result houseById(@PathVariable Integer id) throws Exception{ //查看户籍下的人员信息
        List<Resident> residents = houseHoldService.houseById(id);
        return Result.success(residents);
    }

    @GetMapping("/gcsm/houseByHouseId/{houseId}")
    @ResponseBody
    public Result houseByHouseId(@PathVariable Integer houseId) throws Exception{

        HouseHold houseHold = houseHoldService.houseByHouseId(houseId);
        return Result.success(houseHold);
    }

    @PostMapping("/gcsm/updateHouse")
    public String updateHouse(HouseHold houseHold, AddrVo addrVo) throws Exception{

        houseHold.setHouseAddr(addrVo.houAddr());
        houseHoldService.updateHouse(houseHold);
        return "redirect:/gcsm/household";
    }

    @GetMapping("/gcsm/delHouse/{houseId}/{housingStatus}")
    @ResponseBody
    public Result delHouse(@PathVariable Integer houseId,@PathVariable String housingStatus) throws Exception{
        houseHoldService.delHouse(houseId,housingStatus);
        return Result.success();
    }

}
