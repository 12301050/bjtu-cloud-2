package com.bjtu.cloud.web;

import com.bjtu.cloud.common.RestResult;
import com.bjtu.cloud.common.entity.NodeInfo;
import com.bjtu.cloud.gate.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Kafukaaa on 16/10/23.
 */
@RestController
public class NodeController {

  @Autowired
  private NodeService nodeService;

  //获取所有用户信息
  @RequestMapping(value = "api/node/getAllNode", method = RequestMethod.GET)
  public RestResult<List<NodeInfo>> modifyStatus() {
    try {
      List<NodeInfo> nodeInfos = nodeService.getAll();
      return  RestResult.succ().data(nodeInfos).build();
    }catch (Exception e) {
      e.printStackTrace();
      return RestResult.fail().msg(e.toString()).build();
    }
  }
}
