package net.lliira.vpn.site.action.admin;

import java.util.Map;

import net.lliira.vpn.model.product.Product;
import net.lliira.vpn.model.product.ProductFactory;
import net.lliira.vpn.site.Constants;
import net.lliira.vpn.site.action.DefaultAction;

public class SaveProductAction extends DefaultAction {

  /**
   * 
   */
  private static final long serialVersionUID = -4958212090444193403L;

  private ProductFactory productFactory;

  private int productId;
  private int bandwidth;
  private int duration;
  private double price;
  private boolean available;
  private String description;

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
   * @param bandwidth
   *          the bandwidth to set
   */
  public void setBandwidth(int bandwidth) {
    this.bandwidth = bandwidth;
  }

  /**
   * @param duration
   *          the duration to set
   */
  public void setDuration(int duration) {
    this.duration = duration;
  }

  /**
   * @param price
   *          the price to set
   */
  public void setPrice(double price) {
    this.price = price;
  }

  /**
   * @param available
   *          the available to set
   */
  public void setAvailable(boolean available) {
    this.available = available;
  }

  /**
   * @param description
   *          the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * @return the bandwidth
   */
  public int getBandwidth() {
    return bandwidth;
  }

  /**
   * @return the duration
   */
  public int getDuration() {
    return duration;
  }

  /**
   * @return the price
   */
  public double getPrice() {
    return price;
  }

  /**
   * @return the available
   */
  public boolean isAvailable() {
    return available;
  }

  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.opensymphony.xwork2.ActionSupport#validate()
   */
  @Override
  public void validate() {
    Map<String, String> errors =
        productFactory.validateProduct(session, bandwidth, duration, price);
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
    Product product = productFactory.getProduct(session, productId);
    if (product == null) {
      productFactory.addProduct(session, bandwidth, duration, price,
          description, available);
    } else {
      productFactory.updateProduct(session, product, bandwidth, duration,
          price, description, available);
    }
    return Constants.RESULT_SUCCESS;
  }

}
