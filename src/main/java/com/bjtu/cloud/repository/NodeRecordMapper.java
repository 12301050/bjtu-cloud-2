package com.bjtu.cloud.repository;

import com.bjtu.cloud.common.entity.NodeRecord;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface NodeRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NodeRecord record);

    int insertSelective(NodeRecord record);

    NodeRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NodeRecord record);

    int updateByPrimaryKey(NodeRecord record);

    //获取所有任务日志
    List<NodeRecord> getAllNodeRecord();
}