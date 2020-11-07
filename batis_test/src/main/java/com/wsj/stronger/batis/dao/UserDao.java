package com.wsj.stronger.batis.dao;

import com.wsj.stronger.batis.domain.UserDO;

import java.util.List;

/**
 *
 *
 * @Author jiahao
 * @Date 2020/11/7 11:40
 */
public interface UserDao {
    UserDO queryByCondition(UserDO userDO);

    List<UserDO> findAll();

}
