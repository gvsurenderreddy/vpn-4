package net.lliira.vpn.site.action.secure;

import net.lliira.vpn.model.user.UserFactory;
import net.lliira.vpn.site.Constants;
import net.lliira.vpn.site.action.DefaultAction;

public class LogoutAction extends DefaultAction {

  /**
   * 
   */
  private static final long serialVersionUID = 3535338803918720820L;

  private UserFactory userFactory;

  /**
   * @param userFactory
   *          the userFactory to set
   */
  public void setUserFactory(UserFactory userFactory) {
    this.userFactory = userFactory;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.opensymphony.xwork2.ActionSupport#execute()
   */
  @Override
  public String execute() throws Exception {
    userFactory.logout(session, user);

    // reset user, UserAware interceptor will reset session
    user = null;
    return Constants.RESULT_SUCCESS;
  }

}
