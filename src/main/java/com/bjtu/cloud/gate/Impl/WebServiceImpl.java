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

import java.text.SimpleDateFormat;
import java.util.*;
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

  SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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
      for (int i = collects.size() - 1 ; i >= 0 ; i--) {
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
  public List<Food> getPersonal(Integer userId) throws Exception {
    try {
      List<Collect> collects = collectMapper.getCollect(userId);
      List<Food> foods = new ArrayList<Food>();
      List<User> users = new ArrayList<User>();
      List<Integer> foodIds = new ArrayList<Integer>();
      // 初始化Map
      Map<Integer , Integer> map = new HashMap<Integer, Integer>();
      for (int i = 0; i < collects.size(); i++) {
        Integer foodId = collects.get(i).getFoodId();
        foodIds.add(foodId);
        List<Collect> collectByFood = collectMapper.getCollectByFood(foodId);
        for (int j = 0; j < collectByFood.size(); j++) {
          User user = userMapper.selectByPrimaryKey(collectByFood.get(j).getUserId());
          if (!users.contains(user)) {
            users.add(user);
          }
        }
      }

      for (int i = 0; i < users.size(); i++) {
        List<Collect> collectByUser = collectMapper.getCollect(users.get(i).getId());
        for (int j = 0; j < collectByUser.size(); j++) {
          Integer foodId1 = collectByUser.get(j).getFoodId();
          if (!foodIds.contains(foodId1)) {
            if (map.containsKey(foodId1)) {
              map.put(foodId1, map.get(foodId1) + 1);
            } else {
              map.put(foodId1, 1);
            }
          }
        }
      }

      map = sortMap(map);

      int flag = 0;
        Iterator it = map.entrySet().iterator();
        while (it.hasNext() && flag < 10) {
          Map.Entry entry = (Map.Entry) it.next();
          System.out.println(entry.getKey() + ":" + entry.getValue());
          foods.add(foodMapper.selectByPrimaryKey(Integer.valueOf(entry.getKey().toString())));
          flag++;
        }

      return foods;
    }catch (Exception e){
      e.printStackTrace();
      return  null;
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

  @Override
  public Integer doCollect(Integer userId, Integer foodId, Integer type) throws Exception {
    try {
      Integer flag = 0;
      if (type == 1){
        Collect collect = new Collect();
        collect.setUserId(userId);
        collect.setFoodId(foodId);
        collect.setCollectTime(df1.parse(df1.format(new Date())));
        flag = collectMapper.insert(collect);

        //更新food表heat
        Food food = foodMapper.selectByPrimaryKey(foodId);
        food.setHeat(food.getHeat()+1);
        foodMapper.updateByPrimaryKeySelective(food);

        /*//更新user表，用户喜欢的食物类型
        List<Food> foods = getCollect(userId);
        int[] foodTypes = new int[6];
        Integer length = foodTypes.length;
        for (int i = 0; i < length; i++) {
          foodTypes[foods.get(i).getFoodType()]++;
        }
        Arrays.sort(foodTypes);
        String foodType = foodTypes[length-1] + "," + foodTypes[length-2] + "," + foodTypes[length-3];

        User user = userMapper.selectByPrimaryKey(userId);
        user.setFoodType(foodType);
        userMapper.updateByPrimaryKeySelective(user);*/

      }else if (type == 0){
        Collect collect = collectMapper.isCollect(userId, foodId);
        flag = collectMapper.deleteByPrimaryKey(collect.getId());

        //更新food表heat
        Food food = foodMapper.selectByPrimaryKey(foodId);
        food.setHeat(food.getHeat()-1);
        foodMapper.updateByPrimaryKeySelective(food);
      }
      return  flag;
    }catch (Exception e){
      e.printStackTrace();
      return 0;
    }
  }

  public Map sortMap(Map oldMap) {
    ArrayList<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>(oldMap.entrySet());
    Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
      @Override
      public int compare(Map.Entry<Integer, Integer> arg0,
                         Map.Entry<Integer, Integer> arg1) {
        return arg1.getValue() - arg0.getValue();
      }
    });
    Map newMap = new LinkedHashMap();
    for (int i = 0; i < list.size(); i++) {
      newMap.put(list.get(i).getKey(), list.get(i).getValue());
    }
    return newMap;
  }

}

