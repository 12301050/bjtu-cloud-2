package com.bjtu.cloud.common.entity;

import java.util.Date;

/**
 * Created by Kafukaaa on 2016/11/28.
 */
public class City {
  private Integer id;

  private String state;

  private String notes;

  private String path;

  private Date time;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }
}
