package net.lliira.vpn.site.action.secure;

import java.util.List;
import java.util.Map;

import net.lliira.vpn.model.account.Account;
import net.lliira.vpn.model.account.AccountFactory;
import net.lliira.vpn.model.pref.PreferenceFactory;
import net.lliira.vpn.model.purchase.Purchase;
import net.lliira.vpn.model.purchase.PurchaseFactory;
import net.lliira.vpn.site.Constants;
import net.lliira.vpn.site.action.DefaultAction;

public class AccountAction extends DefaultAction {

  /**
   * 
   */
  private static final long serialVersionUID = -2646770497808872357L;

  private PreferenceFactory preferenceFactory;
  private AccountFactory accountFactory;
  private PurchaseFactory purchaseFactory;

  private Account account;
  private Map<String, String> preferences;
  private List<Purchase> purchases;

  /**
   * @param accountFactory
   *          the accountFactory to set
   */
  public void setAccountFactory(AccountFactory accountFactory) {
    this.accountFactory = accountFactory;
  }

  /**
   * @param preferenceFactory
   *          the preferenceFactory to set
   */
  public void setPreferenceFactory(PreferenceFactory preferenceFactory) {
    this.preferenceFactory = preferenceFactory;
  }

  /**
   * @param purchaseFactory
   *          the purchaseFactory to set
   */
  public void setPurchaseFactory(PurchaseFactory purchaseFactory) {
    this.purchaseFactory = purchaseFactory;
  }
  

  /**
   * @return the preferences
   */
  public Map<String, String> getPreferences() {
    return preferences;
  }

  /**
   * @return the account
   */
  public Account getAccount() {
    return account;
  }

  /**
   * @return the purchases
   */
  public List<Purchase> getPurchases() {
    return purchases;
  }

  @Override
  public String execute() throws Exception {
    preferences = preferenceFactory.getPreferences(session);
    account = accountFactory.getAccount(session, user);
    purchases = purchaseFactory.getPurchases(session, user.getEmail());

    return Constants.RESULT_SUCCESS;
  }
}
