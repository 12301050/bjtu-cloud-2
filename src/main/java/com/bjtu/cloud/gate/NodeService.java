package com.bjtu.cloud.gate;

import com.bjtu.cloud.common.entity.NodeInfo;
import java.util.List;

/**
 * Created by Kafukaaa on 16/10/26.
 */
public interface NodeService {
  //获取所有节点信息
  List<NodeInfo> getAll() throws Exception;

  //关闭某个节点
  Integer closeNode(String nodeId) throws Exception;

  //开启某个节点
  Integer startNode(String nodeId) throws Exception;

  //获取某个用户下所有节点
  List<NodeInfo> getNodeByUser(String userName) throws Exception;
}
