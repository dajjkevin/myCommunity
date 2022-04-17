package com.abc.gcsmsys.controller;


import com.abc.gcsmsys.utils.Result;
import com.abc.gcsmsys.utils.TencentCOSUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/uploadImg")
//@Api(tags = "文件上传接口")
public class CosController {

//    @ApiOperation(value = "图片上传到腾讯云对象存储",notes = "")
//    @ApiImplicitParam(name = "img",value = "批量签名文件导入",required = true,dataType="MultipartFile",allowMultiple = true,paramType = "form")
    @PostMapping("/img")
    @ResponseBody
    public Result upload(MultipartFile img){

        if (img == null){
            return Result.error("-1","上传的文件不为空！");
        }
        String uploadfile = TencentCOSUtil.uploadfile(img);
        System.out.println(uploadfile);
        return Result.success(uploadfile);
    }

    @GetMapping("/test")
    public String test() {

        return "backstage/grassRoots/test";
    }
}
