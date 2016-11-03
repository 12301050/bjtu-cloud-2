package com.bjtu.cloud.common.webDao;

import java.io.Serializable;

/**
 * rest 返回值
 */
public class RestResult<T> implements Serializable {

  public static final String CODE_SUCCESS = "0";
  public static final String CODE_FAIL = "-1";
  public static final String MSG_SUCCESS = "success";
  public static final String MSG_FAIL = "fail";

  /**
   * ret:(返回码).
   */
  private String ret;

  /**
   * sub:(返回子码).
   */
  private String sub;

  /**
   * msg:(文本消息.
   */
  private String msg;

  /**
   * data:(数据).
   */
  private T data;

  private RestResult(Builder<T> builder) {
    setRet(builder.ret);
    setSub(builder.sub);
    setMsg(builder.msg);
    setData(builder.data);
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public static Builder succ() {
    return new Builder().ret(CODE_SUCCESS).sub(CODE_SUCCESS).msg(MSG_SUCCESS);
  }

  public static Builder fail() {
    return new Builder().ret(CODE_FAIL).sub(CODE_FAIL).msg(MSG_FAIL);
  }

  public String getRet() {
    return ret;
  }

  public void setRet(String ret) {
    this.ret = ret;
  }

  public String getSub() {
    return sub;
  }

  public void setSub(String sub) {
    this.sub = sub;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public static final class Builder<T> {
    private String ret;
    private String sub;
    private String msg;
    private T data;

    private Builder() {
    }

    public Builder ret(String val) {
      ret = val;
      return this;
    }

    public Builder sub(String val) {
      sub = val;
      return this;
    }

    public Builder msg(String val) {
      msg = val;
      return this;
    }

    public Builder data(T val) {
      data = val;
      return this;
    }

    public RestResult build() {
      return new RestResult(this);
    }
  }
}
