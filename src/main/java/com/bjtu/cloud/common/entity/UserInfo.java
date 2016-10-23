package com.bjtu.cloud.common.entity;

public class UserInfo {
    private Integer id;

    private String userName;

    private Integer nodeAmount;

    private String nodeIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getNodeAmount() {
        return nodeAmount;
    }

    public void setNodeAmount(Integer nodeAmount) {
        this.nodeAmount = nodeAmount;
    }

    public String getNodeIds() {
        return nodeIds;
    }

    public void setNodeIds(String nodeIds) {
        this.nodeIds = nodeIds == null ? null : nodeIds.trim();
    }
}