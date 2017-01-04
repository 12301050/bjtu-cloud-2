package com.bjtu.cloud.repository;

import com.bjtu.cloud.common.entity.City;
import com.bjtu.cloud.common.entity.UserInfo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Kafukaaa on 2016/11/28.
 */
@Component
public interface CityMapper {
  //获取所有用户所在区评论信息
  List<City> getNotesByState(String state);

  //插入一条评论信息
  int insert(City city);
}
