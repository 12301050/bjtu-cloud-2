package com.bjtu.cloud.gate;

import com.bjtu.cloud.common.entity.City;
import com.bjtu.cloud.common.entity.User;
import com.bjtu.cloud.common.entity.UserInfo;

import java.util.List;

/**
 * Created by Kafukaaa on 16/10/23.
 */
public interface UserService {

  //获取所有用户信息
  List<UserInfo> getAll() throws Exception;

  //获取所有用户信息
  List<City> getNotesByState(String state) throws Exception;

  //用户登录
  User login(String userName, String password) throws Exception;

  //增加节点
  Integer addNodeInUserInfo(String userName, String nodeId) throws Exception;

  //增加评论
  Integer insertOne(City city) throws Exception;
}
