package com.bjtu.cloud.gate;

import com.bjtu.cloud.common.entity.TaskInfo;
import org.springframework.scheduling.config.Task;

import java.util.List;

/**
 * Created by Kafukaaa on 16/10/26.
 */
public interface TaskService {
  //获取某个节点下某个状态的任务
  List<TaskInfo> getTaskByNode(String nodeId, Integer status) throws Exception;
}
