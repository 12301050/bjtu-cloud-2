package com.bjtu.cloud.repository;

import com.bjtu.cloud.common.entity.NodeInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface NodeInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NodeInfo record);

    int insertSelective(NodeInfo record);

    //获取所有节点信息
    List<NodeInfo> getAllNodeInfo();

    NodeInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NodeInfo record);

    int updateByPrimaryKey(NodeInfo record);
}