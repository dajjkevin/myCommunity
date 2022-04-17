package com.abc.gcsmsys.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Random;

/**
 * @Description 图片上传工具
 * @Version 1.0
 * @Author  surface
 * @Date 2022-2-22 21:35
 */
@Component
@Slf4j
public class TencentCOSUtil {

    private static final String accessKey = "AKIDuc5HDsGNDm45qqZ1B6j5AzWmlUw70cz5" ;

    private static final String secretKey = "qPilY3IG0TfaDZeU3LM6amhLioc9QP7X";

    private static final String bucket = "ap-nanjing";

    private static final String bucketName = "img022-1306164549";

    private static final String path = "https://img022-1306164549.cos.ap-nanjing.myqcloud.com/";


    private static final String prefix = "oxl/";

    private static COSCredentials credentials = new BasicCOSCredentials(accessKey,secretKey);

    private static ClientConfig clientConfig = new ClientConfig(new Region(bucket));

    public static String uploadfile(MultipartFile file){
        // 创建 COS 客户端连接
        COSClient cosClient = new COSClient(credentials,clientConfig);
        String fileName = file.getOriginalFilename();
        try {
            String substring = fileName.substring(fileName.lastIndexOf("."));
            File localFile = File.createTempFile(String.valueOf(System.currentTimeMillis()),substring);
            file.transferTo(localFile);
            Random random = new Random();
            fileName =prefix+random.nextInt(10000)+System.currentTimeMillis()+substring;
            // 将 文件上传至 COS
            PutObjectRequest objectRequest = new PutObjectRequest(bucketName,fileName,localFile);
            cosClient.putObject(objectRequest);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cosClient.shutdown();
        }
        return path+fileName;
    }


    /**
     *
     * @describe 上传文件的方法
     * @methods uploadObject 方法名
     * @parameter  fileUrl 上传文件地址
     * @parameter  fileKey 文件对象名称
     * @parameter @return 对象列表
     * @return
     */
    public static String uploadObject(String fileUrl, String fileKey) {
        COSClient cosClient = new COSClient(credentials,clientConfig);
        try {
            // 指定要上传的文件
            File localFile = new File(fileUrl);
            // fileKey 指定要上传到 COS 上对象键
            String substring = fileUrl.substring(fileUrl.lastIndexOf("."));
            Random random = new Random();
            fileKey =prefix+random.nextInt(10000)+System.currentTimeMillis()+substring;
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileKey, localFile);
            PutObjectResult  putObjectResult = cosClient.putObject(putObjectRequest);
            return path+fileKey;
        } catch (CosServiceException serverException) {
            log.error(serverException.getErrorMessage());
            throw  new RuntimeException("上传文件平台Server异常"+serverException.getErrorMessage());
        } catch (CosClientException clientException) {
            log.error(clientException.getMessage());
            throw  new RuntimeException("上传文件平台Client异常"+clientException.getMessage());
        }

    }

    public static String uploadObject2(String codeContent, String fileKey) {

        COSClient cosClient = new COSClient(credentials,clientConfig);
        try {
            // 指定要上传的文件
//            File localFile = new File(fileUrl);

            File localFile = QrCodeUtil.generate(codeContent, QrConfig.create().setImg("D:\\xk-idea-Three-stage\\SelfStudy\\GCSM-Sys\\src\\main\\resources\\static\\xtheme\\images\\login-logoCom.png"),FileUtil.file("d:\\qrcode11.jpg"));
            String localFileTost = localFile.toString();

            // fileKey 指定要上传到 COS 上对象键
            String substring = localFileTost.substring(localFileTost.lastIndexOf("."));
            Random random = new Random();
            fileKey =prefix+random.nextInt(10000)+System.currentTimeMillis()+substring;
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileKey, localFile);
            PutObjectResult  putObjectResult = cosClient.putObject(putObjectRequest);
            return path+fileKey;

        } catch (CosServiceException serverException) {
            log.error(serverException.getErrorMessage());
            throw  new RuntimeException("上传文件平台Server异常"+serverException.getErrorMessage());
        } catch (CosClientException clientException) {
            log.error(clientException.getMessage());
            throw  new RuntimeException("上传文件平台Client异常"+clientException.getMessage());
        }

    }

    /**
     * Description: 判断Cos服务文件上传时文件的contentType
     *
     * @param filenameExtension 文件后缀
     * @return String
     */
    public static String getcontentType(String filenameExtension) {
        if (filenameExtension.equalsIgnoreCase("bmp")) {
            return "image/bmp";
        }
        if (filenameExtension.equalsIgnoreCase("gif")) {
            return "image/gif";
        }
        if (filenameExtension.equalsIgnoreCase("jpeg") || filenameExtension.equalsIgnoreCase("jpg")
                || filenameExtension.equalsIgnoreCase("png")) {
            return "image/jpeg";
        }
        if (filenameExtension.equalsIgnoreCase("html")) {
            return "text/html";
        }
        if (filenameExtension.equalsIgnoreCase("txt")) {
            return "text/plain";
        }
        if (filenameExtension.equalsIgnoreCase("vsd")) {
            return "application/vnd.visio";
        }
        if (filenameExtension.equalsIgnoreCase("pptx") || filenameExtension.equalsIgnoreCase("ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (filenameExtension.equalsIgnoreCase("docx") || filenameExtension.equalsIgnoreCase("doc")) {
            return "application/msword";
        }
        if (filenameExtension.equalsIgnoreCase("xml")) {
            return "text/xml";
        }
        return "image/jpeg";
    }
}
