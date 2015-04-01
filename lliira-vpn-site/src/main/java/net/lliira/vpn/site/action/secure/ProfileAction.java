package net.lliira.vpn.site.action.secure;

import net.lliira.vpn.model.user.UserFactory;
import net.lliira.vpn.site.Constants;
import net.lliira.vpn.site.action.DefaultAction;

public class ProfileAction extends DefaultAction {

  /**
   * 
   */
  private static final long serialVersionUID = 8796253668283975542L;


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
    // load email for the user

    return Constants.RESULT_SUCCESS;
  }

}
