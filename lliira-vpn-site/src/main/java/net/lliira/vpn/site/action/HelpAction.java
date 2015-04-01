package net.lliira.vpn.site.action;

import net.lliira.vpn.site.Constants;

public class HelpAction extends DefaultAction {

  /**
   * 
   */
  private static final long serialVersionUID = 7585983065526082906L;

  private String os;

  /**
   * @return the os
   */
  public String getOs() {
    return os;
  }

  /**
   * @param os
   *          the os to set
   */
  public void setOs(String os) {
    this.os = os;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.opensymphony.xwork2.ActionSupport#execute()
   */
  @Override
  public String execute() throws Exception {
    return (os == null) ? Constants.RESULT_SUCCESS : Constants.RESULT_OS;
  }

}
