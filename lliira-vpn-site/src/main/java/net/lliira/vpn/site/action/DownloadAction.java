package net.lliira.vpn.site.action;

import net.lliira.vpn.site.Constants;

public class DownloadAction extends DefaultAction {

  /**
   * 
   */
  private static final long serialVersionUID = 1655172382098564739L;

  /*
   * (non-Javadoc)
   * 
   * @see com.opensymphony.xwork2.ActionSupport#execute()
   */
  @Override
  public String execute() throws Exception {
    return Constants.RESULT_SUCCESS;
  }

}
