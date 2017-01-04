package com.bjtu.cloud.gate;



import com.bjtu.cloud.common.entity.web.User;

import java.util.List;

/**
 * Created by Kafukaaa on 17/1/4.
 */
public interface WebService {

  //用户登录
  User login(String userName, String password) throws Exception;

}
