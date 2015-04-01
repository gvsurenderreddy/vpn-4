package net.lliira.vpn.site.action.admin;

import net.lliira.vpn.model.product.Product;
import net.lliira.vpn.model.product.ProductFactory;
import net.lliira.vpn.site.Constants;
import net.lliira.vpn.site.action.DefaultAction;

public class ShowProductAction extends DefaultAction {

  /**
   * 
   */
  private static final long serialVersionUID = 7471852988147323086L;

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
   * @return the bandwidth
   */
  public int getBandwidth() {
    return bandwidth;
  }

  /**
   * @param bandwidth
   *          the bandwidth to set
   */
  public void setBandwidth(int bandwidth) {
    this.bandwidth = bandwidth;
  }

  /**
   * @return the duration
   */
  public int getDuration() {
    return duration;
  }

  /**
   * @param duration
   *          the duration to set
   */
  public void setDuration(int duration) {
    this.duration = duration;
  }

  /**
   * @return the price
   */
  public double getPrice() {
    return price;
  }

  /**
   * @param price
   *          the price to set
   */
  public void setPrice(double price) {
    this.price = price;
  }

  /**
   * @return the available
   */
  public boolean isAvailable() {
    return available;
  }

  /**
   * @param available
   *          the available to set
   */
  public void setAvailable(boolean available) {
    this.available = available;
  }

  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param description
   *          the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.opensymphony.xwork2.ActionSupport#execute()
   */
  @Override
  public String execute() throws Exception {
    Product product = productFactory.getProduct(session, productId);
    if (product != null) {
      productId = product.getId();
      bandwidth = product.getBandwidth();
      duration = product.getDuration();
      price = product.getPrice();
      available = product.isAvailable();
      description = product.getDescription();
    }
    return Constants.RESULT_SUCCESS;
  }

}
