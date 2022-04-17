package com.abc.gcsmsys.service;

import com.abc.gcsmsys.domain.Admin;
import com.abc.gcsmsys.domain.AdminSearch;
import com.abc.gcsmsys.domain.HouseSearch;
import com.abc.gcsmsys.utils.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @Description
 */
public interface AdminService {

    Admin checkLogin(Admin admin);

    /*发送邮件验证码*/
    Result sendCode4Mail(String email);

//    void create4Email(UserCreateDto createDto);

    void addGrassRoots(Admin admin);

    Page findPageAdmin(Integer pageNum, Integer pageSize, AdminSearch search);

    /**回去修改信息*/
    Admin getByAdminId(Integer adminId);

    void updateAdmin(Admin admin);

    void changeAdminStatus(Integer adminId,String adminStatus);

}
