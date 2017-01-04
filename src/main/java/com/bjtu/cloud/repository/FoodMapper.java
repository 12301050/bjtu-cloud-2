package com.bjtu.cloud.repository;

import com.bjtu.cloud.common.entity.Food;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodMapper {

    //获取所有种类的食物
    List<Food> getAll();

    //获取各个种类的食物
    List<Food> getFood(Integer foodType);

    int deleteByPrimaryKey(Integer id);

    int insert(Food record);

    int insertSelective(Food record);

    Food selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Food record);

    int updateByPrimaryKey(Food record);
}