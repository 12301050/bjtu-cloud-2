package com.bjtu.cloud.gate.Impl;

import com.bjtu.cloud.common.entity.NodeInfo;
import com.bjtu.cloud.common.entity.UserInfo;
import com.bjtu.cloud.gate.NodeService;
import com.bjtu.cloud.repository.NodeInfoMapper;
import com.bjtu.cloud.repository.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kafukaaa on 16/10/26.
 */
@Service
public class NodeServiceImpl implements NodeService {

  @Autowired
  private NodeInfoMapper nodeInfoMapper;

  @Autowired
  private UserInfoMapper userInfoMapper;

  @Override
  public List<NodeInfo> getAll() throws Exception {
    try {
      List<NodeInfo> nodeInfos = nodeInfoMapper.getAllNodeInfo();
      return nodeInfos;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public Integer closeNode(String nodeId) throws Exception {
    try {
      //TODO 对docker上的节点进行关闭
      Integer flag = nodeInfoMapper.closeNode(nodeId);
      return flag;
    }catch (Exception e){
      e.printStackTrace();
      return 0;
    }
  }

  @Override
  public Integer startNode(String nodeId) throws Exception {
    try {
      //TODO 对docker上的节点进行关闭
      Integer flag = nodeInfoMapper.startNode(nodeId);
      return flag;
    }catch (Exception e){
      e.printStackTrace();
      return 0;
    }
  }

  @Override
  public String addNodeInNodeInfo(Integer type) throws Exception {
    String nodeId = "005";
    try {
      //TODO docker上进行节点创建

      NodeInfo nodeInfo = new NodeInfo();
      if(!nodeId.isEmpty()){
        nodeInfo.setNodeName(nodeId);
        nodeInfo.setNodeId(nodeId);
        nodeInfo.setStatus(0);
        nodeInfo.setType(type);
        nodeInfo.setTaskAmount(0);
        nodeInfo.setHistoryTaskAmount(0);
        Integer flag = nodeInfoMapper.addNodeInNodeInfo(nodeInfo);
        if(flag != 0){
          return nodeId;
        }else {
          return nodeId;
        }
      }else {
        return nodeId;
      }
    }catch (Exception e){
      e.printStackTrace();
      return nodeId;
    }
  }
  @Override
  public List<NodeInfo> getNodeByUser(String userName) throws Exception {
    List<NodeInfo> nodeInfos = new ArrayList<NodeInfo>();
    try {
      UserInfo userInfo = userInfoMapper.getUserInfoByUserName(userName);
      String[] nodeIds = userInfo.getNodeIds().split(",");
      for (int i = 0; i < nodeIds.length; i++) {
        NodeInfo nodeInfo = nodeInfoMapper.getNodeByNodeId(nodeIds[i]);
        nodeInfos.add(nodeInfo);
      }
      return nodeInfos;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}