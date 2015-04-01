package net.lliira.vpn.model.user;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class User {

  public static String unescapeEmail(String escaped) {
    return escaped.replace(" at ", "@").replace(" dot ", ".").trim();
  }

  private int id;

  private String email;
  private String password;
  private String signature;
  private String activationCode;
  private boolean activated;
  private Date registeredTime;
  private Date activatedTime;
  private Date lastLoginTime;
  private int referral;
  private boolean blocked;

  private final Set<String> roles = new HashSet<String>();

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
   * @return the signature
   */
  public String getSignature() {
    return signature;
  }

  /**
   * @param signature
   *          the signature to set
   */
  public void setSignature(String signature) {
    this.signature = signature;
  }

  /**
   * @return the registeredTime
   */
  public Date getRegisteredTime() {
    return registeredTime;
  }

  /**
   * @param registeredTime
   *          the registeredTime to set
   */
  public void setRegisteredTime(Date registeredTime) {
    this.registeredTime = registeredTime;
  }

  /**
   * @return the lastLoginTime
   */
  public Date getLastLoginTime() {
    return lastLoginTime;
  }

  /**
   * @param lastLoginTime
   *          the lastLoginTime to set
   */
  public void setLastLoginTime(Date lastLoginTime) {
    this.lastLoginTime = lastLoginTime;
  }

  /**
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  public String getEmailEscaped() {
    return email.replace("@", " at ").replace(".", " dot ");
  }

  /**
   * @param email
   *          the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * @return the activatedTime
   */
  public Date getActivatedTime() {
    return activatedTime;
  }

  /**
   * @param activatedTime
   *          the activatedTime to set
   */
  public void setActivatedTime(Date activatedTime) {
    this.activatedTime = activatedTime;
  }

  /**
   * @return the activationCode
   */
  public String getActivationCode() {
    return activationCode;
  }

  /**
   * @param activationCode
   *          the activationCode to set
   */
  public void setActivationCode(String activationCode) {
    this.activationCode = activationCode;
  }

  /**
   * @return the activated
   */
  public boolean isActivated() {
    return activated;
  }

  /**
   * @param activated
   *          the activated to set
   */
  public void setActivated(boolean activated) {
    this.activated = activated;
  }

  /**
   * @return the referral
   */
  public int getReferral() {
    return referral;
  }

  /**
   * @param referral
   *          the referral to set
   */
  public void setReferral(int referral) {
    this.referral = referral;
  }

  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * @param password
   *          the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }

  public Collection<String> getRoles() {
    return roles;
  }

  public void addRole(String role) {
    roles.add(role);
  }

  public void removeRole(String role) {
    roles.remove(role);
  }

  public void setRoles(Collection<String> roles) {
    this.roles.clear();
    if (roles != null) this.roles.addAll(roles);
  }

  /**
   * @return the blocked
   */
  public boolean isBlocked() {
    return blocked;
  }

  /**
   * @param blocked the blocked to set
   */
  public void setBlocked(boolean blocked) {
    this.blocked = blocked;
  }
  
  
}
