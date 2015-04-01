package net.lliira.vpn.model.user;

import java.util.Date;

public class UserLog {

  private int userId;
  private Date logTime;
  private String type;
  private String message;

  /**
   * @return the userId
   */
  public int getUserId() {
    return userId;
  }

  /**
   * @param userId
   *          the userId to set
   */
  public void setUserId(int userId) {
    this.userId = userId;
  }

  /**
   * @return the logTime
   */
  public Date getLogTime() {
    return logTime;
  }

  /**
   * @param logTime
   *          the logTime to set
   */
  public void setLogTime(Date logTime) {
    this.logTime = logTime;
  }

  /**
   * @return the type
   */
  public String getType() {
    return type;
  }

  /**
   * @param type
   *          the type to set
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * @return the message
   */
  public String getMessage() {
    return message;
  }

  /**
   * @param message
   *          the message to set
   */
  public void setMessage(String message) {
    this.message = message;
  }

}
