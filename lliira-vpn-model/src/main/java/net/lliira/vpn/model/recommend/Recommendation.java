package net.lliira.vpn.model.recommend;

import java.util.Date;

public class Recommendation {

  private final int id;
  private int userId;
  private String url;
  private String description;
  private Date postedTime;
  private int likes;
  private int dislikes;
  
  public Recommendation(int id) {
    this.id = id;
  }

  /**
   * @return the url
   */
  public String getUrl() {
    return url;
  }

  /**
   * @param url
   *          the url to set
   */
  public void setUrl(String url) {
    this.url = url;
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
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * @return the userId
   */
  public int getUserId() {
    return userId;
  }

  /**
   * @param userId the userId to set
   */
  public void setUserId(int userId) {
    this.userId = userId;
  }

  /**
   * @return the postedTime
   */
  public Date getPostedTime() {
    return postedTime;
  }

  /**
   * @param postedTime the postedTime to set
   */
  public void setPostedTime(Date postedTime) {
    this.postedTime = postedTime;
  }

  /**
   * @return the likes
   */
  public int getLikes() {
    return likes;
  }

  /**
   * @param likes the likes to set
   */
  public void setLikes(int likes) {
    this.likes = likes;
  }

  /**
   * @return the dislikes
   */
  public int getDislikes() {
    return dislikes;
  }

  /**
   * @param dislikes the dislikes to set
   */
  public void setDislikes(int dislikes) {
    this.dislikes = dislikes;
  }

}
