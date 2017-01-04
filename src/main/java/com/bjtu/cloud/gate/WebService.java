package com.bjtu.cloud.gate;



import com.bjtu.cloud.common.entity.Food;
import com.bjtu.cloud.common.entity.User1;

import java.util.List;

/**
 * Created by Kafukaaa on 17/1/4.
 */
public interface WebService {

  //用户登录
  User1 login(String userName, String password) throws Exception;

  //获取食物
  List<Food> getFood(Integer foodType) throws Exception;

}
