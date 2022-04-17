package com.abc.gcsmsys.utils;

import cn.hutool.extra.qrcode.QrCodeUtil;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @Author surface
 * @Date 2022-2-14 20:56
 * @Description
 */
@Slf4j
public class test {


    public static  void main(String[] args) throws Exception {
//        String defaultPwd = SecureUtil.md5("123");
//        System.out.println(defaultPwd);
//
//        DecimalFormat df = new DecimalFormat("0.00");
//        System.out.println(df.format(5));
//
//        System.out.println(String.format("%.2f", 5));
//        System.out.println(3.5);

//        String orderNo  = IdUtil.objectId();//hutool生成订单id
//        System.out.println(orderNo);

        BufferedImage generate = QrCodeUtil.generate("123456", 300, 300);
        System.out.println(generate);

//        System.out.println(generate);
//        String s = generate.toString();
//
//        String decode = QrCodeUtil.decode(generate);
//        System.out.println(decode);
//
//        String putObjectResult = TencentCOSUtil.uploadObject(s, "qrcode");
//        System.out.println(putObjectResult);
//        log.info(putObjectResult);


        String putObjectResult = TencentCOSUtil.uploadObject2("我是陈雅红大傻逼，我爱吃屎", "");
        log.info(putObjectResult);
        System.out.println(putObjectResult);

    }




}
