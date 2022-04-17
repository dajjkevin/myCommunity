package com.abc.gcsmsys.service;

import com.abc.gcsmsys.domain.AdminSearch;
import com.abc.gcsmsys.domain.User;
import com.abc.gcsmsys.domain.UserSearch;
import com.abc.gcsmsys.utils.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.org.apache.regexp.internal.RE;

/**
 * @Description
 */
public interface UserService {

    Result checkUserNameAndPwd(String name, String Pwd);

    Result register(User user);

    Result userUpdatePwd(User user);


//    后天对用户的CRUD
    void saveUser(User user);

    User getUserByName(String userName);

    Page findPageUser(Integer pageNum, Integer pageSize, UserSearch search);

    User getByUserId(Integer userId);

    void updateUser(User user);

    void changeStatus(Integer userId,String userStatus);
}
