package com.bjtu.cloud.gate.Impl;

import com.bjtu.cloud.common.entity.TaskInfo;
import com.bjtu.cloud.common.entity.TaskRecord;
import com.bjtu.cloud.gate.TaskService;
import com.bjtu.cloud.repository.TaskInfoMapper;
import com.bjtu.cloud.repository.TaskRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Kafukaaa on 16/10/26.
 */
@Service
public class TaskServiceImpl implements TaskService{

  @Autowired
  private TaskInfoMapper taskInfoMapper;

  @Autowired
  private TaskRecordMapper taskRecordMapper;

  @Override
  public List<TaskInfo> getTaskByNode(String nodeId, Integer status) throws Exception {
    try {
      List<TaskInfo> taskInfos = taskInfoMapper.getTaskByNode(nodeId, status);
      return taskInfos;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public List<TaskInfo> getTaskByUserName(String userName, Integer status) throws Exception {
    try {
      List<TaskInfo> taskInfos = taskInfoMapper.getTaskByUserName(userName, status);
      return taskInfos;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public List<TaskRecord> getAllTaskRecord() throws Exception {
    try {
      List<TaskRecord> taskRecords = taskRecordMapper.getAllTaskRecord();
      return taskRecords;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public TaskInfo queryTimeInfo(String nodeId, Integer taskId) throws Exception {
    try {
      TaskInfo taskInfo = taskInfoMapper.queryTimeInfo(nodeId, taskId);
      return taskInfo;
    }catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public List<TaskInfo> getPerformance(String nodeId, Integer taskId) throws Exception {
    try {
      List<TaskInfo> taskInfos = taskInfoMapper.getPerformance(nodeId, taskId);
      return taskInfos;
    }catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
