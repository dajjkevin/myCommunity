package com.abc.gcsmsys.controller;

import com.abc.gcsmsys.domain.*;
import com.abc.gcsmsys.service.EvaluateService;
import com.abc.gcsmsys.service.ServeService;
import com.abc.gcsmsys.utils.Result;
import com.abc.gcsmsys.utils.TencentCOSUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.DecimalFormat;

/**
 * @Description 社区服务和服务订单
 */
@Controller
public class ServeController extends BaseController{

    @Autowired
    private ServeService serveService;

    @Autowired
    private EvaluateService evaluateService;

    @GetMapping("/gcsm/addServeIndex")
    public String addServeIndex(Model model) throws Exception{
        model.addAttribute("serveType",serveService.loadServeTypeName());
        return "backstage/serve/create_serve";
    }

    @GetMapping("/gcsm/checkServeType/{typeName}")
    @ResponseBody
    public Result checkServeType(@PathVariable String typeName) throws Exception{
        Result result = serveService.checkServeType(typeName);
        return result;
    }

    @PostMapping("/gcsm/savaType/{typeName}")
    @ResponseBody
    public Result savaType(ServeType typeInfo) throws Exception{
        serveService.savaType(typeInfo);
        return Result.success();
    }

    @PostMapping("/gcsm/savaServe")
    @ResponseBody
    public Result savaServe(Serve serve, String content, MultipartFile serPic) throws Exception{

        if (serPic == null){
            serve.setServeImg("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpicnew12.photophoto.cn%2F20171202%2Fchuangjianhexieshehui-29233528_1.jpg&refer=http%3A%2F%2Fpicnew12.photophoto.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1649639292&t=4f104a839465f0c86b871a84c1d0e927");
        }else{
            String uploadfileSerPic = TencentCOSUtil.uploadfile(serPic);
            System.out.println(uploadfileSerPic);
            serve.setServeImg(uploadfileSerPic);
        }
        DecimalFormat df = new DecimalFormat("0.00");
        serve.setServeMoney(df.format(Double.parseDouble(serve.getServeMoney())));
        serve.setServeInt(content);

        serveService.savaServe(serve);

        return Result.success();
    }

    @GetMapping("/gcsm/loadServe")
    public String loadGrassRoots(Model model, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                 @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                 ServeSearch serveSearch) throws Exception{

        IPage serveInfo = serveService.findServeInfo(pageNum, pageSize, serveSearch);

        //方便回显搜索内容
        model.addAttribute("typeName",serveSearch.getTypeName());
        model.addAttribute("serveInfo", serveInfo);
        model.addAttribute("serveType",serveService.loadServeTypeName());

        return "backstage/serve/load_serve";
    }

    @GetMapping("/gcsm/updateServe/{serveId}")
    public String updateServeIndex(@PathVariable Integer serveId,Model model) throws Exception{

        Serve serve = serveService.serveById(serveId);

        model.addAttribute("serveType",serveService.loadServeTypeName());
        model.addAttribute("upServe",serve);

        return "backstage/serve/update_serve";
    }

    @PostMapping("/gcsm/updateServe")
    @ResponseBody
    public Result updateServe(Serve serve, String content,MultipartFile serPic) throws Exception{

        if (serPic != null){
            String uploadfileSerPic = TencentCOSUtil.uploadfile(serPic);
            System.out.println(uploadfileSerPic);
            serve.setServeImg(uploadfileSerPic);
        }
        DecimalFormat df = new DecimalFormat("0.00");
        serve.setServeMoney(df.format(Double.parseDouble(serve.getServeMoney())));
        serve.setServeInt(content);
        serveService.updateServe(serve);

        return Result.success();
    }

    @GetMapping("/gcsm/serveIntClick/{serveId}")
    @ResponseBody
    public Result ServeInt(@PathVariable Integer serveId) throws Exception{

        Serve serve = serveService.serveById(serveId);
        return Result.success(serve);
    }

    @GetMapping("/gcsm/serveOrders")
    public String serveOrdersPage(Model model, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                 @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                 OrdersSearch ordersSearch) throws Exception{

        model.addAttribute("payStatus",ordersSearch.getPayStatus());
        IPage serveOrdersPage = serveService.serveOrdersPage(pageNum, pageSize, ordersSearch);
        model.addAttribute("serveOrdersPage",serveOrdersPage);

        return "backstage/serve/serveOrders";
    }

//    前台用户

    @GetMapping("/user/showServe")
    public String serveInfo(Model model, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                            @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                            ServeSearch serveSearch) throws Exception{

        IPage serveInfo = serveService.findServeInfo(pageNum, pageSize, serveSearch);

        //方便回显搜索内容
        model.addAttribute("serveInfo", serveInfo);
        model.addAttribute("typeName",serveSearch.getTypeName());
        model.addAttribute("serveType",serveService.loadServeTypeName());

        return "frontdesk/gcsmPage/actPage/showServe";
    }

    @GetMapping("/user/serveDtl/{serveId}")
    public String serveDtl(Model model,@PathVariable Integer serveId,
                           @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize",defaultValue = "3") Integer pageSize) throws Exception{

        Serve serveDtl = serveService.serveById(serveId);
        model.addAttribute("serveDtl",serveDtl);

        IPage<Evaluate> allEvaluate = evaluateService.findUserEvaluate(pageNum, pageSize);
        model.addAttribute("allEvaluate",allEvaluate);

        return "frontdesk/gcsmPage/actPage/serveDtl";
    }

    @GetMapping("/user/serveDtlModal/{serveId}")
    @ResponseBody
    public Result<Serve> serveDtlModal(Model model, @PathVariable Integer serveId) throws Exception{

        Serve serveDtl = serveService.serveById(serveId);
        return Result.success(serveDtl);
    }

}
