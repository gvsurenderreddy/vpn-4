package net.lliira.vpn.site.action;

import java.util.Map;

import net.lliira.vpn.model.user.UserFactory;
import net.lliira.vpn.site.Constants;

public class RegisterAction extends DefaultAction {

  /**
   * 
   */
  private static final long serialVersionUID = 5126392012670845950L;

  private String email;
  private String password;
  private String passwordConfirm;
  private String referral;

  private UserFactory userFactory;


  /**
   * @param userFactory
   *          the userFactory to set
   */
  public void setUserFactory(UserFactory userFactory) {
    this.userFactory = userFactory;
  }

  /**
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * @param email
   *          the email to set
   */
  public void setEmail(String email) {
    this.email = email;
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

  /**
   * @return the referral
   */
  public String getReferral() {
    return referral;
  }

  /**
   * @param referral
   *          the referral to set
   */
  public void setReferral(String referral) {
    this.referral = referral;
  }

  @Override
  public void validate() {
    Map<String, String> errors =
        userFactory.validateRegister(session, email, password, passwordConfirm,
            referral);
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
    // register user
    userFactory.register(session, email, password, referral);
    
    // logout current user.
    user = null;

    return Constants.RESULT_SUCCESS;
  }
}
