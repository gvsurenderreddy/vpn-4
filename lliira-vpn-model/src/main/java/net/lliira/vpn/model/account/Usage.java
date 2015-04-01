package net.lliira.vpn.model.account;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;

public class Usage {

  public static String formatBandwidth(long bandwidth) {
    NumberFormat numberFormat = new DecimalFormat("#,###.##");
    double value;
    String suffix;
    if (bandwidth >= 1000000000) {
      value = bandwidth / 1000000000.0;
      suffix = " GB";
    } else if (bandwidth >= 1000000) {
      value = bandwidth / 1000000.0;
      suffix = " MB";
    } else if (bandwidth >= 1000) {
      value = bandwidth / 1000.0;
      suffix = " KB";
    } else {
      value = bandwidth;
      suffix = " Bytes";
    }
    return numberFormat.format(value) + suffix;
  }

  public static String formatTime(long time) {
    String format = "";
    long hour = time / 3600;
    if (hour >= 24) {
      long day = hour / 24;
      hour = hour % 24;
      format = day + "天 ";
    }
    long remain = time % 3600;
    String minute = Long.toString(remain / 60);
    String second = Long.toString(remain % 60);
    if (hour < 10) format += "0";
    if (minute.length() == 1) minute = "0" + minute;
    if (second.length() == 1) second = "0" + second;
    format += hour + ":" + minute + ":" + second;
    return format;
  }

  private final int userId;
  private int year;
  private int month;
  private Date startTime;
  private long sessionTime;
  private long upload;
  private long download;

  public Usage(int userId) {
    this.userId = userId;
  }

  /**
   * @return the userId
   */
  public int getUserId() {
    return userId;
  }

  /**
   * @return the year
   */
  public int getYear() {
    return year;
  }

  /**
   * @param year
   *          the year to set
   */
  public void setYear(int year) {
    this.year = year;
  }

  /**
   * @return the month
   */
  public int getMonth() {
    return month;
  }

  /**
   * @param month
   *          the month to set
   */
  public void setMonth(int month) {
    this.month = month;
  }

  public String getPeriod() {
    return Integer.toString(year) + "年" + month + "月";
  }

  /**
   * @return the sessionTime
   */
  public long getSessionTime() {
    return sessionTime;
  }

  public String getSessionTimeFormat() {
    return formatTime(sessionTime);
  }

  /**
   * @param sessionTime
   *          the sessionTime to set
   */
  public void setSessionTime(long sessionTime) {
    this.sessionTime = sessionTime;
  }

  /**
   * @return the upload
   */
  public long getUpload() {
    return upload;
  }

  public String getUploadFormat() {
    return formatBandwidth(upload);
  }

  /**
   * @param upload
   *          the upload to set
   */
  public void setUpload(long upload) {
    this.upload = upload;
  }

  /**
   * @return the download
   */
  public long getDownload() {
    return download;
  }

  public String getDownloadFormat() {
    return formatBandwidth(download);
  }

  /**
   * @param download
   *          the download to set
   */
  public void setDownload(long download) {
    this.download = download;
  }

  public long getUsedBandwidth() {
    return upload + download;
  }

  public String getUsedBandwidthFormat() {
    return formatBandwidth(getUsedBandwidth());
  }

  /**
   * @return the startTime
   */
  public Date getStartTime() {
    return startTime;
  }

  /**
   * @param startTime
   *          the startTime to set
   */
  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

}
