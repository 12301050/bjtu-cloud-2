package com.bjtu.cloud.gate;

import com.bjtu.cloud.common.entity.NodeInfo;
import java.util.List;

/**
 * Created by Kafukaaa on 16/10/26.
 */
public interface NodeService {
  //获取所有节点信息
  List<NodeInfo> getAll() throws Exception;
}
