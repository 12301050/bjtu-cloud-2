package com.bjtu.cloud.gate.Impl;


import com.bjtu.cloud.common.entity.Food;
import com.bjtu.cloud.common.entity.User1;
import com.bjtu.cloud.gate.WebService;
import com.bjtu.cloud.repository.FoodMapper;
import com.bjtu.cloud.repository.UserMapper1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kafukaaa on 17/1/4.
 */
@Service
public class WebServiceImpl implements WebService {

  @Autowired
  private UserMapper1 userMapper;

  @Autowired
  private FoodMapper foodMapper;

  @Override
  public User1 login(String userName, String password) throws Exception {
    try {
      User1 user = userMapper.login(userName,password);
      return user;
    }catch (Exception e){
      return null;
    }
  }

  @Override
  public List<Food> getFood(Integer foodType) throws Exception {
    try {
      List<Food> foods = new ArrayList<Food>();
      if (foodType == 0) {
        foods = foodMapper.getAll();
      }else {
        foods = foodMapper.getFood(foodType);
      }
      return foods;
    }catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }
}
