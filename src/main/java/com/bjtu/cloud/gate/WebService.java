package com.bjtu.cloud.gate;



import com.bjtu.cloud.common.entity.Food;
import com.bjtu.cloud.common.entity.User;

import java.util.List;

/**
 * Created by Kafukaaa on 17/1/4.
 */
public interface WebService {

  //用户登录
  User login(String userName, String password) throws Exception;

  //获取食物
  List<Food> getFood(Integer foodType) throws Exception;

  //获取收藏
  List<Food> getCollect(Integer userId) throws Exception;

  //获取个性化推荐
  List<Food> getPersonal(Integer userId) throws Exception;

  //是否收藏
  Integer isCollect(Integer userId, Integer foodId) throws Exception;

  //收藏/取消收藏
  Integer doCollect(Integer userId, Integer foodId, Integer type) throws Exception;

}
