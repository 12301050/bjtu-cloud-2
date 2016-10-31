package com.bjtu.cloud.repository;

import com.bjtu.cloud.common.entity.User;
import com.bjtu.cloud.common.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {
  int deleteByPrimaryKey(Integer id);

  int insert(User record);

  int insertSelective(User record);

  User selectByPrimaryKey(Integer id);

  //用户通过账户名、密码登录
  User login(@Param("userName")String userName, @Param("password")String password);

  int updateByPrimaryKeySelective(User record);

  int updateByPrimaryKey(User record);
}