package com.bjtu.cloud.gate.Impl;

import com.bjtu.cloud.common.entity.User;
import com.bjtu.cloud.common.entity.UserInfo;
import com.bjtu.cloud.gate.UserService;
import com.bjtu.cloud.repository.UserInfoMapper;
import com.bjtu.cloud.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Kafukaaa on 16/10/23.
 */
@Service
public class UserServiceImpl implements UserService{

  @Autowired
  private UserInfoMapper userInfoMapper;

  @Autowired
  private UserMapper userMapper;

  //获取所有用户信息
  @Override
  public List<UserInfo> getAll() throws Exception{
    try {
      List<UserInfo> userInfos = userInfoMapper.getAllUserInfo();
      return userInfos;
    }catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public User login(String userName, String password) throws Exception {
    try {
      User user = userMapper.login(userName,password);
      return user;
    }catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public Integer addNode(String userName, Integer type) throws Exception {
    try {
      //TODO docker上的增加
      Integer flag = userInfoMapper.addNode(userName, type);
      return flag;
    }catch (Exception e){
      e.printStackTrace();
      return 0;
    }
  }
}
