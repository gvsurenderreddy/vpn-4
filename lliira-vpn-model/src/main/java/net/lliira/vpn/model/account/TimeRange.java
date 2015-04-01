package net.lliira.vpn.model.account;

import java.util.Calendar;
import java.util.Date;

public class TimeRange {

  private Date from;
  private Date to;

  /**
   * @return the from, inclusive
   */
  public Date getFrom() {
    return from;
  }

  public String getFromMonth() {
    Calendar date = Calendar.getInstance();
    date.setTime(from);
    int year = date.get(Calendar.YEAR);
    int month = date.get(Calendar.MONTH) + 1;
    return year + "_" + month;
  }

  /**
   * @param from
   *          the from to set
   */
  public void setFrom(Date from) {
    this.from = from;
  }

  /**
   * @return the to, exclusive
   */
  public Date getTo() {
    return to;
  }

  /**
   * @param to
   *          the to to set
   */
  public void setTo(Date to) {
    this.to = to;
  }

}
