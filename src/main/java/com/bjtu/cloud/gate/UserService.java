package com.bjtu.cloud.gate;

import com.bjtu.cloud.common.entity.User;

import java.util.List;

/**
 * Created by Kafukaaa on 16/10/23.
 */
public interface UserService {

  List<User> getAll();
}
