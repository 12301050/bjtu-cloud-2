package com.bjtu.cloud.gate.Impl;

import com.bjtu.cloud.common.entity.User;
import com.bjtu.cloud.gate.UserService;
import com.bjtu.cloud.repository.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Kafukaaa on 16/10/23.
 */
@Service
public class UserServiceImpl implements UserService{

  @Override
  public List<User> getAll() {
    return null;
  }
}
