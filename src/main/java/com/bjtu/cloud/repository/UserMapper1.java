package com.bjtu.cloud.repository;

import com.bjtu.cloud.common.entity.User1;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper1 {
    int deleteByPrimaryKey(Integer id);

    int insert(User1 record);

    int insertSelective(User1 record);

    User1 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User1 record);

    int updateByPrimaryKey(User1 record);

  //用户通过账户名、密码登录
  User1 login(@Param("userName")String userName, @Param("password")String password);
}