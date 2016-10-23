package com.bjtu.cloud.repository;

import com.bjtu.cloud.common.entity.NodeInfo;

public interface NodeInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NodeInfo record);

    int insertSelective(NodeInfo record);

    NodeInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NodeInfo record);

    int updateByPrimaryKey(NodeInfo record);
}