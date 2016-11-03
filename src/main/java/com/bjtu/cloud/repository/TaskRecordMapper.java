package com.bjtu.cloud.repository;

import com.bjtu.cloud.common.entity.TaskRecord;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TaskRecordMapper {
  int deleteByPrimaryKey(Integer id);

  int insert(TaskRecord record);

  int insertSelective(TaskRecord record);

  TaskRecord selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(TaskRecord record);

  int updateByPrimaryKey(TaskRecord record);

  //获取所有任务日志
  List<TaskRecord> getAllTaskRecord();
}