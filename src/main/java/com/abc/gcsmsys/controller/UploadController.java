package com.abc.gcsmsys.controller;


import com.abc.gcsmsys.domain.ApiReturnUtil;
import com.abc.gcsmsys.utils.TencentCOSUtil;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

 /**
  * @Description:wangEditor富文本
  **/
@RestController
@RequestMapping("/upload")
public class UploadController {
//    //这个注入配置文件，主要是因为本地的路径和服务器url路径需要动态配置，可以自己写死，也可以动态获取
//    @Autowired
//    AppConfig appConfig;

    @RequestMapping("/editor")
    @ResponseBody
    public Object editor(@RequestParam("file") MultipartFile file) {
        String fileName ="";
        String wangEditorupLoad ="";
        if(!file.isEmpty()){
            //返回的是字节长度,1M=1024k=1048576字节 也就是if(fileSize<5*1048576)
            if(file.getSize()>(1048576*5)){
                return ApiReturnUtil.error("文件太大，请上传小于5MB的");
            }
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            if(StringUtils.isBlank(suffix)){
                return ApiReturnUtil.error("上传文件没有后缀，无法识别");
            }

            fileName = System.currentTimeMillis()+suffix;
            wangEditorupLoad = TencentCOSUtil.uploadfile(file);
        }else {
            return new WangEditorResponse("1","上传出错");
        }

        String imgUrl= wangEditorupLoad;
        System.out.println("图片上传地址"+imgUrl);
        return new WangEditorResponse("0",imgUrl);
    }

    @Data
    private class WangEditorResponse{
        String errno;
        List<String> data;
        public WangEditorResponse(String errno,List<String> data){
            this.errno=errno;
            this.data=data;
        }
        public WangEditorResponse(String errno,String data){
            this.errno=errno;
            this.data=new ArrayList<>();
            this.data.add(data);
        }
    }
}
