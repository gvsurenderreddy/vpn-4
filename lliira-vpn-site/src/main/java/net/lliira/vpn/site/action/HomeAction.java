/**
 * 
 */
package net.lliira.vpn.site.action;

import net.lliira.vpn.site.Constants;

/**
 * @author jerric
 * 
 *         this
 */
public class HomeAction extends DefaultAction {

  /**
   * 
   */
  private static final long serialVersionUID = -6253879710659173229L;

  private String referral;

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
  public String execute() throws Exception {
    return Constants.RESULT_SUCCESS;
  }

}
