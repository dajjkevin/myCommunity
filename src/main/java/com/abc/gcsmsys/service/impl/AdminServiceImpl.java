package com.abc.gcsmsys.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.abc.gcsmsys.domain.Admin;
import com.abc.gcsmsys.domain.AdminSearch;
import com.abc.gcsmsys.domain.HouseHold;
import com.abc.gcsmsys.domain.HouseSearch;
import com.abc.gcsmsys.excepetion.GlobalException;
import com.abc.gcsmsys.mapper.AdminMapper;
import com.abc.gcsmsys.service.AdminService;
import com.abc.gcsmsys.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


import java.util.Random;

/**
 * @Description
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public Admin checkLogin(Admin admin) {

        //校验用户名是不是邮箱格式，如果是就通过邮箱进行查找
        if (admin.getAdminName().matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")){
            admin.setAdminEmail(admin.getAdminName());
        }

        //可以通过账户名/邮箱方式进行登录
        Admin adminInfo = new Admin();
        if ("admin".equals(admin.getAdminName())){
             adminInfo = adminMapper.selectOne(Wrappers.<Admin>lambdaQuery().eq(Admin::getAdminId,admin.getAdminId()).
                    or().eq(Admin::getAdminEmail,admin.getAdminEmail()).or().eq(Admin::getAdminName,"admin"));
        }else{
            adminInfo = adminMapper.selectOne(Wrappers.<Admin>lambdaQuery().eq(Admin::getAdminId,admin.getAdminName()).
                    or().eq(Admin::getAdminEmail,admin.getAdminEmail()));
        }

        if (adminInfo == null){
            throw  new GlobalException("登录名不存在");
        }else if (!adminInfo.getAdminPwd().equals(SecureUtil.md5(admin.getAdminPwd()))){
            throw new GlobalException("工号存在，密码不正确");
        }

        return adminInfo;

    }

    @Override
    public Result sendCode4Mail(String email) {

        Admin admin = adminMapper.selectOne(Wrappers.<Admin>lambdaQuery().eq(Admin::getAdminEmail, email));

        if (admin == null){
            return Result.error("-1","该邮箱暂未绑定过，请联系管理员");
        }

        // 构建一个邮件对象
        SimpleMailMessage message = new SimpleMailMessage();
        // 设置邮件主题
        message.setSubject("基层社区管理服务平台！");
        // 设置邮件发送者，这个跟application.yml中设置的要一致
        message.setFrom("oxl100@qq.com");
        // 设置邮件接收者，可以有多个接收者，中间用逗号隔开，以下类似
        message.setTo(email);
//        message.setTo("10*****16@qq.com");
//        // 设置邮件抄送人，可以有多个抄送人
//        message.setCc("12****32*qq.com");
//        // 设置隐秘抄送人，可以有多个
//        message.setBcc("7******9@qq.com");
//        // 设置邮件发送日期
//        message.setSentDate(new Date());
        // 设置邮件的正文

        Random rnd = new Random();

        Random random = new Random();
        String result="";
        for (int i=0;i<6;i++)
        {
            result+=random.nextInt(10);
        }
        message.setText("您好，您正在操作找回密码服务！\n" +
                "\n" +
                "邮箱验证码为:" + result +"\n"
                + "如果不是本人操作，请忽略。");
        // 发送邮件
        javaMailSender.send(message);

        return Result.success(result);//成功的话把验证码返回

    }

    @Override
    public void addGrassRoots(Admin admin) {

        String defaultPwd = SecureUtil.md5("123456");
        admin.setAdminPwd(defaultPwd);
        adminMapper.insert(admin);
    }

    @Override
    public Page findPageAdmin(Integer pageNum, Integer pageSize, AdminSearch search) {

        QueryWrapper<Admin> wrapper = new QueryWrapper<>();

        if (!StrUtil.isBlank(search.getSName()) || !StrUtil.isBlank(search.getSPoliticalIdentity())
                || !StrUtil.isBlank(search.getSStatus()) ){ //不等于null才进行查找
            wrapper.like("admin_name", search.getSName()).like("political_identity",search.getSPoliticalIdentity())
                    .like("admin_status",search.getSStatus());
        }

        Page<Admin> adminInfo = adminMapper.selectPage(new Page<>(pageNum, pageSize), wrapper.orderByDesc("admin_id"));

        return adminInfo;
    }

    @Override
    public Admin getByAdminId(Integer adminId) {

        Admin adminInfo = adminMapper.selectOne(Wrappers.<Admin>lambdaQuery().eq(Admin::getAdminId, adminId));
        return adminInfo;
    }

    @Override
    public void updateAdmin(Admin admin) {

        adminMapper.updateById(admin);
    }

    @Override
    public void changeAdminStatus(Integer adminId, String adminStatus) {

        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("admin_id",adminId);
        updateWrapper.set("admin_status",adminStatus);
        adminMapper.update(null,updateWrapper);
    }
}
