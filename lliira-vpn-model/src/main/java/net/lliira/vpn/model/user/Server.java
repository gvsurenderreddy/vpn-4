package net.lliira.vpn.model.user;

public class Server {

  private int id;
  private String ip;
  private boolean forPublic;
  private boolean forRegisteredUsers;
  private boolean forPurchasedUsers;
  private String description;

  public Server(int id) {
    this.id = id;
  }

  /**
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * @param id
   *          the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * @return the ip
   */
  public String getIp() {
    return ip;
  }

  /**
   * @param ip
   *          the ip to set
   */
  public void setIp(String ip) {
    this.ip = ip;
  }

  /**
   * @return the forPublic
   */
  public boolean isForPublic() {
    return forPublic;
  }

  /**
   * @param forPublic
   *          the forPublic to set
   */
  public void setForPublic(boolean forPublic) {
    this.forPublic = forPublic;
  }

  /**
   * @return the forRegisteredUsers
   */
  public boolean isForRegisteredUsers() {
    return forRegisteredUsers;
  }

  /**
   * @param forRegisteredUsers
   *          the forRegisteredUsers to set
   */
  public void setForRegisteredUsers(boolean forRegisteredUsers) {
    this.forRegisteredUsers = forRegisteredUsers;
  }

  /**
   * @return the forPurchasedUsers
   */
  public boolean isForPurchasedUsers() {
    return forPurchasedUsers;
  }

  /**
   * @param forPurchasedUsers
   *          the forPurchasedUsers to set
   */
  public void setForPurchasedUsers(boolean forPurchasedUsers) {
    this.forPurchasedUsers = forPurchasedUsers;
  }

  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param description
   *          the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

}
