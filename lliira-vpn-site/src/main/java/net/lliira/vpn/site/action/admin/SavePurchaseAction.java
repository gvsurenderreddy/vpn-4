package net.lliira.vpn.site.action.admin;

import java.util.Map;

import net.lliira.vpn.model.product.Product;
import net.lliira.vpn.model.product.ProductFactory;
import net.lliira.vpn.model.product.ProductMap;
import net.lliira.vpn.model.purchase.Purchase;
import net.lliira.vpn.model.purchase.PurchaseFactory;
import net.lliira.vpn.model.user.User;
import net.lliira.vpn.site.Constants;
import net.lliira.vpn.site.action.DefaultAction;

public class SavePurchaseAction extends DefaultAction {

  /**
   * 
   */
  private static final long serialVersionUID = 7973473595760539735L;

  private ProductFactory productFactory;
  private PurchaseFactory purchaseFactory;

  private int purchaseId;
  private String email;
  private int productId;

  private ProductMap productMap;

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
   * @param productFactory
   *          the productFactory to set
   */
  public void setProductFactory(ProductFactory productFactory) {
    this.productFactory = productFactory;
  }

  /**
   * @param purchaseFactory
   *          the purchaseFactory to set
   */
  public void setPurchaseFactory(PurchaseFactory purchaseFactory) {
    this.purchaseFactory = purchaseFactory;
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

  /**
   * @return the productMap
   */
  public ProductMap getProductMap() {
    return productMap;
  }
  

  /*
   * (non-Javadoc)
   * 
   * @see com.opensymphony.xwork2.ActionSupport#validate()
   */
  @Override
  public void validate() {
    Product product = productFactory.getProduct(session, productId);
    Map<String, String> errors =
        purchaseFactory.validateAddPurchase(session, email, product);
    for (String field : errors.keySet()) {
      addFieldError(field, errors.get(field));
    }
    if (errors.size() > 0) {
      productMap = productFactory.getAvailableProductMap(session);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.opensymphony.xwork2.ActionSupport#execute()
   */
  @Override
  public String execute() throws Exception {
    Product product = productFactory.getProduct(session, productId);
    Purchase purchase = purchaseFactory.getPurchase(session, purchaseId);
    if (purchase == null) {
      purchase = purchaseFactory.addPurchase(session, email, product);
    } else {
      purchaseFactory.updatePurchase(session, purchase, product,
          purchase.getPurchaseTime());
    }
    
    // escape email for display purpose
    email = User.unescapeEmail(email);
    pageTitle = "购买记录已保存";
    pageMessage =
        "用户 " + email + " 购买的套餐 :<br />" + product.getBandwidth() + "GB/月 x "
            + product.getDuration() + "月（￥" + product.getPrice()
            + "元）<br /> 记录已保存。";
    return Constants.RESULT_SUCCESS;
  }
}
