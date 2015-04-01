package net.lliira.vpn.site.action.secure;

import java.util.Map;

import net.lliira.vpn.model.user.UserFactory;
import net.lliira.vpn.site.Constants;
import net.lliira.vpn.site.action.DefaultAction;

public class UpdateProfileAction extends DefaultAction {

  /**
   * 
   */
  private static final long serialVersionUID = 8796253668283975542L;

  private String email;
  private String emailConfirm;

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
   * @return the emailConfirm
   */
  public String getEmailConfirm() {
    return emailConfirm;
  }

  /**
   * @param emailConfirm
   *          the emailConfirm to set
   */
  public void setEmailConfirm(String emailConfirm) {
    this.emailConfirm = emailConfirm;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.opensymphony.xwork2.ActionSupport#validate()
   */
  @Override
  public void validate() {
    // validate email
    Map<String, String> errors =
        userFactory.validateUpdateEmail(session, user, email);
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
    // update email
    userFactory.updateEmail(session, user, email);
    user.setEmail(email);
    return Constants.RESULT_SUCCESS;
  }

}
