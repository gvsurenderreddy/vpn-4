package net.lliira.vpn.site.action;

import java.util.Map;

import org.apache.log4j.Logger;

import net.lliira.vpn.model.user.UserFactory;
import net.lliira.vpn.site.Constants;

public class LoginAction extends DefaultAction {

  /**
   * 
   */
  private static final long serialVersionUID = 6937525475607408691L;

  private static final Logger logger = Logger.getLogger(LoginAction.class);

  private String email;
  private String password;
  private boolean saveLogin;

  private UserFactory userFactory;

  /**
   * @return the userFactory
   */
  public UserFactory getUserFactory() {
    return userFactory;
  }

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
   * @return the saveLogin
   */
  public boolean isSaveLogin() {
    return saveLogin;
  }

  /**
   * @param saveLogin
   *          the saveLogin to set
   */
  public void setSaveLogin(boolean saveLogin) {
    this.saveLogin = saveLogin;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.opensymphony.xwork2.ActionSupport#validate()
   */
  @Override
  public void validate() {
    logger.debug("Validating login - email=" + email);
    logger.debug("userFactory: " + userFactory);
    Map<String, String> errors =
        userFactory.validateLogin(session, email, password);
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
    // load user, the user aware interceptor will put it back to session
    user = userFactory.login(session, email, password);

    return Constants.RESULT_SUCCESS;
  }
}
