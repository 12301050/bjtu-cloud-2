package com.bjtu.cloud.repository;

import com.bjtu.cloud.common.entity.TaskInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TaskInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TaskInfo record);

    int insertSelective(TaskInfo record);

    //获取某个节点下某个状态的任务
    List<TaskInfo> getTaskByNode(@Param("nodeId")String nodeId, @Param("status")Integer status);

    //查询某个节点的某个任务的时间信息
    TaskInfo queryTimeInfo(@Param("nodeId") String nodeId, @Param("taskId") Integer taskId);

    //查询某个任务的三个性能数值
    List<TaskInfo> getPerformance(@Param("nodeId") String nodeId, @Param("taskId") Integer taskId);

    TaskInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TaskInfo record);

    int updateByPrimaryKey(TaskInfo record);
}