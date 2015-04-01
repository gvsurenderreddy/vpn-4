package net.lliira.vpn.model.product;

import java.io.Serializable;

public class Product implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 5437193460282262522L;

  private int id;
  private int duration;
  private int bandwidth;
  private boolean available;
  private String description;
  private double price;

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

}
