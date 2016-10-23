package com.bjtu.cloud.repository;

import com.bjtu.cloud.common.entity.NodeRecord;

public interface NodeRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NodeRecord record);

    int insertSelective(NodeRecord record);

    NodeRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NodeRecord record);

    int updateByPrimaryKey(NodeRecord record);
}