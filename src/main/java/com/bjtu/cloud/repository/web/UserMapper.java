package com.bjtu.cloud.repository.web;

import com.bjtu.cloud.common.entity.web.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

  //用户通过账户名、密码登录
  User login(@Param("userName")String userName, @Param("password")String password);
}