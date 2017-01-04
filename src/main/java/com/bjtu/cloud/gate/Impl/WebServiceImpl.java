package com.bjtu.cloud.gate.Impl;


import com.bjtu.cloud.common.entity.web.User;
import com.bjtu.cloud.gate.WebService;
import com.bjtu.cloud.repository.web.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Kafukaaa on 17/1/4.
 */
@Service
public class WebServiceImpl implements WebService {

  @Autowired
  private UserMapper userMapper;

  @Override
  public User login(String userName, String password) throws Exception {
    try {
      User user = userMapper.login(userName,password);
      return user;
    }catch (Exception e){
      return null;
    }
  }
}
