package net.lliira.vpn.model.radius;

public class EmailMap {

  private String oldEmail;
  private String newEmail;

  public EmailMap() {}

  public EmailMap(String oldEmail, String newEmail) {
    super();
    this.oldEmail = oldEmail;
    this.newEmail = newEmail;
  }

  /**
   * @return the oldEmail
   */
  public String getOldEmail() {
    return oldEmail;
  }

  /**
   * @param oldEmail
   *          the oldEmail to set
   */
  public void setOldEmail(String oldEmail) {
    this.oldEmail = oldEmail;
  }

  /**
   * @return the newEmail
   */
  public String getNewEmail() {
    return newEmail;
  }

  /**
   * @param newEmail
   *          the newEmail to set
   */
  public void setNewEmail(String newEmail) {
    this.newEmail = newEmail;
  }

}
