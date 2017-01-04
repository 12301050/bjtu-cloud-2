package com.bjtu.cloud.repository;

import com.bjtu.cloud.common.entity.Collect;
import com.bjtu.cloud.common.entity.Food;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectMapper {

    //获取收藏
    List<Collect> getCollect(Integer userId);

    //是否收藏
    Collect isCollect(@Param("userId")Integer userId, @Param("foodId")Integer foodId);

    int deleteByPrimaryKey(Integer id);

    int insert(Collect record);

    int insertSelective(Collect record);

    Collect selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);
}