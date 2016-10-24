package com.bjtu.cloud.repository;

import com.bjtu.cloud.common.entity.User;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {
  int deleteByPrimaryKey(Integer id);

  int insert(User record);

  int insertSelective(User record);

  User selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(User record);

  int updateByPrimaryKey(User record);
}