package net.lliira.vpn.site.action.secure;

import java.util.Map;

import net.lliira.vpn.model.user.UserFactory;
import net.lliira.vpn.site.Constants;
import net.lliira.vpn.site.action.DefaultAction;

public class UpdatePasswordAction extends DefaultAction {

  /**
   * 
   */
  private static final long serialVersionUID = -3200664769762838334L;

  private String oldPassword;
  private String password;
  private String passwordConfirm;

  private UserFactory userFactory;

  /**
   * @param userFactory
   *          the userFactory to set
   */
  public void setUserFactory(UserFactory userFactory) {
    this.userFactory = userFactory;
  }

  /**
   * @return the oldPassword
   */
  public String getOldPassword() {
    return oldPassword;
  }

  /**
   * @param oldPassword
   *          the oldPassword to set
   */
  public void setOldPassword(String oldPassword) {
    this.oldPassword = oldPassword;
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

  /**
   * @return the passwordConfirm
   */
  public String getPasswordConfirm() {
    return passwordConfirm;
  }

  /**
   * @param passwordConfirm
   *          the passwordConfirm to set
   */
  public void setPasswordConfirm(String passwordConfirm) {
    this.passwordConfirm = passwordConfirm;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.opensymphony.xwork2.ActionSupport#validate()
   */
  @Override
  public void validate() {
    // validate password
    Map<String, String> errors =
        userFactory.validateUpdatePassword(session, user, oldPassword,
            password, passwordConfirm);
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
    // update password
    userFactory.updatePassword(session, user, password);
    return Constants.RESULT_SUCCESS;
  }

}
