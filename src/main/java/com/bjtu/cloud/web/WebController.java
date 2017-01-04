package com.bjtu.cloud.web;

import com.bjtu.cloud.common.entity.Food;
import com.bjtu.cloud.common.entity.User;
import com.bjtu.cloud.common.webDao.RestResult;
import com.bjtu.cloud.gate.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Kafukaaa on 17/1/4.
 */
@RestController
public class WebController {

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

  //获取各个种类的食物,0:所有种类；1:火锅;2:川菜;3:烧烤;4:湘菜;5:自助餐;6:北京菜(按照该种食物的热度排序)
  @RequestMapping(value = "api/food/getFood", method = RequestMethod.GET)
  public RestResult<List<Food>> getFood(Integer foodType) {
    try {
      List<Food> foods = webService.getFood(foodType);
      return RestResult.succ().data(foods).build();
    } catch (Exception e) {
      return null;
    }
  }

  //获取收藏
  @RequestMapping(value = "api/collect/getCollect", method = RequestMethod.GET)
  public RestResult<List<Food>> getCollect(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
    try {
      Integer userId = Integer.valueOf(session.getAttribute("userId").toString());
      List<Food> foods = webService.getCollect(userId);
      return RestResult.succ().data(foods).build();
    } catch (Exception e) {
      return null;
    }
  }

}
