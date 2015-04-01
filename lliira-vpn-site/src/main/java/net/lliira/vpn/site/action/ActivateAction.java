/**
 * 
 */
package net.lliira.vpn.site.action;

import net.lliira.vpn.model.user.UserFactory;
import net.lliira.vpn.site.Constants;

/**
 * @author jerric
 * 
 */
public class ActivateAction extends DefaultAction {

  /**
   * 
   */
  private static final long serialVersionUID = -1397592343799963121L;

  private UserFactory userFactory;

  private String email;
  private String activationCode;
  

  /**
   * @param userFactory the userFactory to set
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

  /*
   * (non-Javadoc)
   * 
   * @see com.opensymphony.xwork2.ActionSupport#execute()
   */
  @Override
  public String execute() throws Exception {
    // activate user
    userFactory.activate(session, email, activationCode);
    
    // logout current user.
    user = null;
    
    return Constants.RESULT_SUCCESS;
  }

}
