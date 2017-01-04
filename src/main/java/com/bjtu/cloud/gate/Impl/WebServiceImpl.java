package com.bjtu.cloud.gate.Impl;


import com.bjtu.cloud.common.entity.Collect;
import com.bjtu.cloud.common.entity.Food;
import com.bjtu.cloud.common.entity.User;
import com.bjtu.cloud.gate.WebService;
import com.bjtu.cloud.repository.CollectMapper;
import com.bjtu.cloud.repository.FoodMapper;
import com.bjtu.cloud.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Kafukaaa on 17/1/4.
 */
@Service
public class WebServiceImpl implements WebService {

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private FoodMapper foodMapper;

  @Autowired
  private CollectMapper collectMapper;

  @Override
  public User login(String userName, String password) throws Exception {
    try {
      User user = userMapper.login(userName,password);
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

  @Override
  public List<Food> getCollect(Integer userId) throws Exception {
    try {
      List<Collect> collects = collectMapper.getCollect(userId);
      List<Food> foods = new ArrayList<Food>();
      for (int i = 0; i < collects.size(); i++) {
        Integer foodId = collects.get(i).getFoodId();
        Food food = foodMapper.selectByPrimaryKey(foodId);
        foods.add(food);
      }
      return foods;
    }catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public Integer isCollect(Integer userId, Integer foodId) throws Exception {
    try {
      Collect collect = collectMapper.isCollect(userId, foodId);
      if (collect != null) {
        return 1;
      }else {
        return 0;
      }
    }catch (Exception e){
      e.printStackTrace();
      return 0;
    }
  }
}
