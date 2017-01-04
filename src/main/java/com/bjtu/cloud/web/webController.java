package com.bjtu.cloud.web;

import com.bjtu.cloud.common.entity.web.User;
import com.bjtu.cloud.common.webDao.RestResult;
import com.bjtu.cloud.gate.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Kafukaaa on 17/1/4.
 */
@RestController
public class webController {

  @Autowired
  private WebService webService;


  SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  SimpleDateFormat df2 = new SimpleDateFormat("HHmmss");

  //用户登录
  @RequestMapping(value = "api/user/login", method = RequestMethod.GET)
  public RestResult<String> login(HttpServletRequest request, HttpServletResponse response, HttpSession session,
                                         String userName, String password) {
    try {
      User user = webService.login(userName, password);
      if (user != null) {
        session.setAttribute("userId", user.getId());
        session.setMaxInactiveInterval(3600);//给session设置3600秒
        return RestResult.succ().data("Login success!").build();
      }else {
        return RestResult.fail().data("Login Failed!").build();
      }
    }catch (Exception e){
      return null;
    }
  }

  //用户登出
  @RequestMapping(value = "api/user/logout", method = RequestMethod.GET)
  public RestResult<String> logout(HttpSession session) {
    try {
      session.invalidate();//让session失效，做访问控制
      return RestResult.succ().data("Logout success!").build();
    } catch (Exception e) {
      return null;
    }
  }

  //获取各个种类的食物

}
