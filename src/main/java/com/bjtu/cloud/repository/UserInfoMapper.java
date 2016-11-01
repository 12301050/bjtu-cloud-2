package com.bjtu.cloud.repository;

import com.bjtu.cloud.common.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    //获取所有用户信息
    List<UserInfo> getAllUserInfo();

    //获取某个用户信息
    UserInfo getUserInfoByUserName(String userName);

    //增加节点
    Integer addNode(@Param("userName")String userName, @Param("nodeId")String nodeId);


    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}