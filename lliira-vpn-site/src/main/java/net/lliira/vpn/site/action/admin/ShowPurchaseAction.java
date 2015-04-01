package net.lliira.vpn.site.action.admin;

import net.lliira.vpn.model.product.ProductFactory;
import net.lliira.vpn.model.product.ProductMap;
import net.lliira.vpn.model.purchase.Purchase;
import net.lliira.vpn.model.purchase.PurchaseFactory;
import net.lliira.vpn.model.user.User;
import net.lliira.vpn.model.user.UserFactory;
import net.lliira.vpn.site.Constants;
import net.lliira.vpn.site.action.DefaultAction;

public class ShowPurchaseAction extends DefaultAction {

  /**
   * 
   */
  private static final long serialVersionUID = -4759933612258502511L;

  private ProductFactory productFactory;
  private PurchaseFactory purchaseFactory;
  private UserFactory userFactory;

  private ProductMap productMap;

  private int purchaseId;
  private String email;
  private int productId;

  /**
   * @param productFactory
   *          the productFactory to set
   */
  public void setProductFactory(ProductFactory productFactory) {
    this.productFactory = productFactory;
  }

  /**
   * @param userFactory
   *          the userFactory to set
   */
  public void setUserFactory(UserFactory userFactory) {
    this.userFactory = userFactory;
  }

  /**
   * @param purchaseFactory
   *          the purchaseFactory to set
   */
  public void setPurchaseFactory(PurchaseFactory purchaseFactory) {
    this.purchaseFactory = purchaseFactory;
  }

  /**
   * @return the purchaseId
   */
  public int getPurchaseId() {
    return purchaseId;
  }

  /**
   * @param purchaseId
   *          the purchaseId to set
   */
  public void setPurchaseId(int purchaseId) {
    this.purchaseId = purchaseId;
  }

  /**
   * @return the productMap
   */
  public ProductMap getProductMap() {
    return productMap;
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
   * @return the productId
   */
  public int getProductId() {
    return productId;
  }

  /**
   * @param productId
   *          the productId to set
   */
  public void setProductId(int productId) {
    this.productId = productId;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.opensymphony.xwork2.ActionSupport#execute()
   */
  @Override
  public String execute() throws Exception {
    productMap = productFactory.getAvailableProductMap(session);
    Purchase purchase = purchaseFactory.getPurchase(session, purchaseId);
    if (purchase != null) {
      User user = userFactory.getUser(session, purchase.getUserId());
      email = user.getEmail();
      productId = purchase.getProductId();
    }
    return Constants.RESULT_SUCCESS;
  }
}
