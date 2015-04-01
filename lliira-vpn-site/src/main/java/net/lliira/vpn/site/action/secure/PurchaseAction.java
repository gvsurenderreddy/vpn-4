package net.lliira.vpn.site.action.secure;

import java.net.URLEncoder;

import net.lliira.vpn.model.product.Product;
import net.lliira.vpn.model.product.ProductFactory;
import net.lliira.vpn.site.Constants;
import net.lliira.vpn.site.action.DefaultAction;

public class PurchaseAction extends DefaultAction {

  /**
   * 
   */
  private static final long serialVersionUID = -1260704332468821294L;

  private static final String ALIPAY_URL =
      "https://shenghuo.alipay.com/send/payment/fill.htm";
  private static final String ALIPAY_ACCOUNT = "iqlinkvpn@yahoo.com";

  private ProductFactory productFactory;

  private int productId;

  private Product product;
  private String paymentUrl;

  /**
   * @param productFactory
   *          the productFactory to set
   */
  public void setProductFactory(ProductFactory productFactory) {
    this.productFactory = productFactory;
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
   * @return the product
   */
  public Product getProduct() {
    return product;
  }

  /**
   * @return the paymentUrl
   */
  public String getPaymentUrl() {
    return paymentUrl;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.opensymphony.xwork2.ActionSupport#execute()
   */
  @Override
  public String execute() throws Exception {
    product = productFactory.getProduct(session, productId);

    int duration = product.getDuration();
    int bandwidth = product.getBandwidth();
    String title =
        URLEncoder.encode(duration + " months " + bandwidth + "GB", "UTF-8");
    String memo = URLEncoder.encode(user.getEmailEscaped(), "UTF-8");
    // construct the payment url
    StringBuilder buffer = new StringBuilder(ALIPAY_URL);
    buffer.append("?payAmount=").append(product.getPrice());
    buffer.append("&optEmail=").append(ALIPAY_ACCOUNT);
    buffer.append("&title=").append(title).append("&memo=").append(memo);

    paymentUrl = buffer.toString();

    return Constants.RESULT_SUCCESS;
  }
}
