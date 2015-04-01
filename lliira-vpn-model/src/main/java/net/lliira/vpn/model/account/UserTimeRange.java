package net.lliira.vpn.model.account;


public class UserTimeRange extends TimeRange {

  private int userId;

  /**
   * @param userId
   *          the userId to set
   */
  public void setUserId(int userId) {
    this.userId = userId;
  }

  /**
   * @return the userId
   */
  public int getUserId() {
    return userId;
  }

}
