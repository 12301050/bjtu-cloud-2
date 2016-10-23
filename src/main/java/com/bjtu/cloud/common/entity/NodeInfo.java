package com.bjtu.cloud.common.entity;

public class NodeInfo {
    private Integer id;

    private String nodeName;

    private String nodeId;

    private Integer status;

    private Integer type;

    private Integer taskAmount;

    private Integer historyTaskAmount;

    private Integer cpu;

    private Integer memory;

    private Integer netSpeed;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName == null ? null : nodeName.trim();
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId == null ? null : nodeId.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getTaskAmount() {
        return taskAmount;
    }

    public void setTaskAmount(Integer taskAmount) {
        this.taskAmount = taskAmount;
    }

    public Integer getHistoryTaskAmount() {
        return historyTaskAmount;
    }

    public void setHistoryTaskAmount(Integer historyTaskAmount) {
        this.historyTaskAmount = historyTaskAmount;
    }

    public Integer getCpu() {
        return cpu;
    }

    public void setCpu(Integer cpu) {
        this.cpu = cpu;
    }

    public Integer getMemory() {
        return memory;
    }

    public void setMemory(Integer memory) {
        this.memory = memory;
    }

    public Integer getNetSpeed() {
        return netSpeed;
    }

    public void setNetSpeed(Integer netSpeed) {
        this.netSpeed = netSpeed;
    }
}