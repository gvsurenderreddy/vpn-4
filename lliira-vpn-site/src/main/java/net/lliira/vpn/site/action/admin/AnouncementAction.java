/**
 * 
 */
package net.lliira.vpn.site.action.admin;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.lliira.vpn.model.admin.AdminFactory;
import net.lliira.vpn.site.Constants;
import net.lliira.vpn.site.action.DefaultAction;

/**
 * @author jerric
 * 
 */
public class AnouncementAction extends DefaultAction {

  /**
   * 
   */
  private static final long serialVersionUID = -2209601957956465567L;

  private AdminFactory adminFactory;

  private String anouncement;
  private int sentCount;

  private boolean activatedUsers = false;
  private boolean unactivatedUsers = false;
  private boolean activeUsers = false;
  private boolean newUsers = true;
  private boolean customUsers = false;

  /**
   * @param adminFactory
   *          the adminFactory to set
   */
  public void setAdminFactory(AdminFactory adminFactory) {
    this.adminFactory = adminFactory;
  }

  /**
   * @return the activatedUsers
   */
  public boolean isActivatedUsers() {
    return activatedUsers;
  }

  /**
   * @param activatedUsers
   *          the activatedUsers to set
   */
  public void setActivatedUsers(boolean activatedUsers) {
    this.activatedUsers = activatedUsers;
  }

  /**
   * @return the unactivatedUsers
   */
  public boolean isUnactivatedUsers() {
    return unactivatedUsers;
  }

  /**
   * @param unactivatedUsers
   *          the unactivatedUsers to set
   */
  public void setUnactivatedUsers(boolean unactivatedUsers) {
    this.unactivatedUsers = unactivatedUsers;
  }

  /**
   * @return the activeUsers
   */
  public boolean isActiveUsers() {
    return activeUsers;
  }

  /**
   * @param activeUsers
   *          the activeUsers to set
   */
  public void setActiveUsers(boolean activeUsers) {
    this.activeUsers = activeUsers;
  }

  /**
   * @return the newUsers
   */
  public boolean isNewUsers() {
    return newUsers;
  }

  /**
   * @param newUsers
   *          the newUsers to set
   */
  public void setNewUsers(boolean newUsers) {
    this.newUsers = newUsers;
  }
  
  

  /**
   * @return the customUsers
   */
  public boolean isCustomUsers() {
    return customUsers;
  }

  /**
   * @param customUsers the customUsers to set
   */
  public void setCustomUsers(boolean customUsers) {
    this.customUsers = customUsers;
  }


  /**
   * @return the anouncement
   */
  public String getAnouncement() {
    return anouncement;
  }

  /**
   * @param anouncement
   *          the anouncement to set
   */
  public void setAnouncement(String anouncement) {
    this.anouncement = anouncement;
  }

  /**
   * @return the sentCount
   */
  public int getSentCount() {
    return sentCount;
  }

  /**
   * @param sentCount
   *          the sentCount to set
   */
  public void setSentCount(int sentCount) {
    this.sentCount = sentCount;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.opensymphony.xwork2.ActionSupport#validate()
   */
  @Override
  public void validate() {
    Map<String, String> errors = adminFactory.validateAnouncement(anouncement);
    for (String field : errors.keySet()) {
      addFieldError(field, errors.get(field));
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.opensymphony.xwork2.ActionSupport#execute()
   */
  @Override
  public String execute() throws Exception {
    Set<String> emails = new HashSet<String>();
    if (activatedUsers) emails.addAll(adminFactory.getEmails(session, true));
    if (unactivatedUsers)
      emails.addAll(adminFactory.getEmails(session, false));
    if (activeUsers) emails.addAll(adminFactory.getActiveEmails(session));
    if (newUsers) emails.addAll(adminFactory.getNewEmails(session));
    if (customUsers) emails.addAll(adminFactory.getCustomEmails(session));

    StringBuilder errors = new StringBuilder();
    sentCount = adminFactory.sendAnouncement(session, anouncement, emails, errors);
    pageMessage = errors.toString();

    return Constants.RESULT_SUCCESS;
  }

}
