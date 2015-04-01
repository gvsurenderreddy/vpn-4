package net.lliira.vpn.site.action;

import java.util.Map;

import net.lliira.vpn.model.user.UserFactory;
import net.lliira.vpn.site.Constants;

public class SendActivationCodeAction extends DefaultAction {
  /**
   * 
   */
  private static final long serialVersionUID = 1551448588247320668L;

  private String email;

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

  /*
   * (non-Javadoc)
   * 
   * @see com.opensymphony.xwork2.ActionSupport#validate()
   */
  @Override
  public void validate() {
    Map<String, String> errors =
        userFactory.validateSendActivationEmail(session, email);
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
    userFactory.sendActivationEmail(session, email);
    pageTitle = "激活信息已发送";
    pageMessage = "您的帐户激活信息已发送至您的注册信箱中，请注意查收。";
    return Constants.RESULT_SUCCESS;
  }
}
