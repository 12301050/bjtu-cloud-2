package com.bjtu.cloud.repository;

import com.bjtu.cloud.common.entity.Collect;
import com.bjtu.cloud.common.entity.Food;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectMapper {

    //获取收藏
    List<Collect> getCollect(Integer userId);

    int deleteByPrimaryKey(Integer id);

    int insert(Collect record);

    int insertSelective(Collect record);

    Collect selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);
}