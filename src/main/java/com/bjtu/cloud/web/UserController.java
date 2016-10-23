package com.bjtu.cloud.web;

import com.bjtu.cloud.gate.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Kafukaaa on 16/10/23.
 */
@RestController
public class UserController {

  @Autowired
  private  UserService userService;
}
