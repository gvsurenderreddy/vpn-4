package net.lliira.vpn.model.purchase;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Purchase {

  private final DateFormat dateFormat = new SimpleDateFormat(
      "yyyy年M月d日 HH:mm:ss");
  private final NumberFormat priceFormat = new DecimalFormat("#,###.##");

  private int id;
  private int userId;
  private int productId;
  private int bandwidth;
  private int duration;
  private double price;
  private Date purchaseTime;

  /**
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * @param id
   *          the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * @return the userId
   */
  public int getUserId() {
    return userId;
  }

  /**
   * @param userId
   *          the userId to set
   */
  public void setUserId(int userId) {
    this.userId = userId;
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
   * @return the purchaseTime
   */
  public Date getPurchaseTime() {
    return purchaseTime;
  }

  /**
   * @param purchaseTime
   *          the purchaseTime to set
   */
  public void setPurchaseTime(Date purchaseTime) {
    this.purchaseTime = purchaseTime;
  }

  public String getPurchaseTimeFormat() {
    return dateFormat.format(purchaseTime);
  }

  public String getExpirationTimeFormat() {
    Calendar time = Calendar.getInstance();
    time.setTime(purchaseTime);
    time.add(Calendar.MONTH, duration);
    return dateFormat.format(time.getTime());
  }

  public String getPriceFormat() {
    return "￥" + priceFormat.format(price) + "元";
  }
}
