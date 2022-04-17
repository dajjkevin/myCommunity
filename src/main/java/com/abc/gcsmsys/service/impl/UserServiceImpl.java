package com.abc.gcsmsys.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.abc.gcsmsys.domain.Admin;
import com.abc.gcsmsys.domain.User;
import com.abc.gcsmsys.domain.UserSearch;
import com.abc.gcsmsys.mapper.UserMapper;
import com.abc.gcsmsys.service.UserService;
import com.abc.gcsmsys.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Result checkUserNameAndPwd(String name, String Pwd) {

        String jmPwd = SecureUtil.md5(Pwd);//加密进行查询比较

        User user = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUserName, name));

        if (user == null){
            return Result.error("-1","用户名不存在，请检查！");
        } else if(user.getUserStatus() == "1"){
            return Result.error("-1","账户存在异常，请及时联系管理员!");
        } else if (!jmPwd.equals(user.getUserPwd())){
            System.out.println(jmPwd+"用户输入的密码");
            System.out.println(user.getUserPwd()+"系统的密码");
            return Result.error("-1","账户名存在，但是密码错误");
        }

        return Result.success(user);
    }

    @Override
    public Result register(User user) {

        String jmPwd = SecureUtil.md5(user.getUserPwd());
        user.setUserPwd(jmPwd);
        userMapper.insert(user);

        return Result.success();
    }

    @Override
    public Result userUpdatePwd(User user) {

        String jmPwd = SecureUtil.md5(user.getUserPwd());
        user.setUserPwd(jmPwd);
        userMapper.updateById(user);
        return Result.success();
    }

    @Override
    public void saveUser(User user) {
        user.setUserPwd(SecureUtil.md5("123456"));
        userMapper.insert(user);
    }

    @Override
    public User getUserByName(String userName) {
        User user = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUserName, userName));
        if (user == null){
            return null;
        }
        return user;
    }

    @Override
    public Page findPageUser(Integer pageNum, Integer pageSize, UserSearch search) {

        QueryWrapper<User> wrapper = new QueryWrapper<>();

        if (!StrUtil.isBlank(search.getSName())){
            wrapper.like("user_name", search.getSName());
        }else if(!StrUtil.isBlank(search.getSPhone())){
            wrapper.like("phone",search.getSPhone());
        }else if (!StrUtil.isBlank(search.getSStatus())){
            wrapper.like("user_status",search.getSStatus());
        }

        Page<User> userInfo = userMapper.selectPage(new Page<>(pageNum, pageSize), wrapper.orderByDesc("user_id"));

        return userInfo;
    }

    @Override
    public User getByUserId(Integer userId) {

        User user = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUserId, userId));
        return user;
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateById(user);
    }

    @Override
    public void changeStatus(Integer userId, String userStatus) {

        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("user_id",userId);
        updateWrapper.set("user_status",userStatus);
        userMapper.update(null,updateWrapper);
    }


}
