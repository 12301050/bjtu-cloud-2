package com.bjtu.cloud.web;

import com.bjtu.cloud.common.RestResult;
import com.bjtu.cloud.common.entity.NodeInfo;
import com.bjtu.cloud.common.entity.TaskInfo;
import com.bjtu.cloud.gate.NodeService;
import com.bjtu.cloud.gate.TaskService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
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

  @Autowired
  private TaskService taskService;

  //获取所有用户信息
  @RequestMapping(value = "api/node/getAllNode", method = RequestMethod.GET)
  public RestResult<List<NodeInfo>> getAll() {
    try {
      List<NodeInfo> nodeInfos = nodeService.getAll();
      return RestResult.succ().data(nodeInfos).build();
    } catch (Exception e) {
      e.printStackTrace();
      return RestResult.fail().msg(e.toString()).build();
    }
  }
  //获取某个用户下所有节点
  @RequestMapping(value = "api/node/getNodeByUser", method = RequestMethod.GET)
  public RestResult<List<NodeInfo>> getAllNodeByUserName(String userName) {
    try{
      List<NodeInfo> nodeInfos = nodeService.getNodeByUser(userName);
      return  RestResult.succ().data(nodeInfos).build();
    }catch (Exception e){
      e.printStackTrace();
      return RestResult.fail().msg(e.toString()).build();
    }
  }

  //获取某节点下的某个状态的任务
  @RequestMapping(value = "api/node/getTaskByNode", method = RequestMethod.GET)
  public RestResult<List<TaskInfo>> getTaskByNode(String nodeId, Integer status) {
    try{
      List<TaskInfo> taskInfos = taskService.getTaskByNode(nodeId, status);
      return  RestResult.succ().data(taskInfos).build();
    }catch (Exception e){
      e.printStackTrace();
      return RestResult.fail().msg(e.toString()).build();
    }
  }

  //关闭某个节点
  @RequestMapping(value = "api/node/closeNode", method = RequestMethod.POST)
  public RestResult<List<NodeInfo>> closeNode(String nodeId) {
    try{
      Integer flag = nodeService.closeNode(nodeId);
      if (flag == 1){
        List<NodeInfo> nodeInfos = nodeService.getAll();
        return RestResult.succ().data(nodeInfos).build();
      }else{
        return RestResult.fail().build();
      }
    }catch (Exception e){
      e.printStackTrace();
      return RestResult.fail().msg(e.toString()).build();
    }
  }
}
