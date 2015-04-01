package net.lliira.vpn.model.account;

import java.util.List;

import net.lliira.vpn.model.user.User;

public class Account {

  private final User user;
  private long effectiveBandwidth;
  private int effectiveReferrals;
  private int totalReferrals;
  private Usage currentUsage;
  private List<Usage> previousUsages;

  public Account(User user) {
    this.user = user;
  }

  /**
   * @return the user
   */
  public User getUser() {
    return user;
  }

  /**
   * @return the totalReferrals
   */
  public int getTotalReferrals() {
    return totalReferrals;
  }

  /**
   * @param totalReferrals
   *          the totalReferrals to set
   */
  public void setTotalReferrals(int totalReferrals) {
    this.totalReferrals = totalReferrals;
  }

  /**
   * @return the effectiveBandwidth
   */
  public long getEffectiveBandwidth() {
    return effectiveBandwidth;
  }

  public String getEffectiveBandwidthFormat() {
    return Usage.formatBandwidth(effectiveBandwidth);
  }

  /**
   * @param effectiveBandwidth
   *          the effectiveBandwidth to set
   */
  public void setEffectiveBandwidth(long effectiveBandwidth) {
    this.effectiveBandwidth = effectiveBandwidth;
  }

  public String getRemainBandwidthFormat() {
    long used = currentUsage.getUsedBandwidth();
    long remain = Math.max(0, effectiveBandwidth - used);
    return Usage.formatBandwidth(remain);
  }

  /**
   * @return the effectiveReferrals
   */
  public int getEffectiveReferrals() {
    return effectiveReferrals;
  }

  /**
   * @param effectiveReferrals
   *          the effectiveReferrals to set
   */
  public void setEffectiveReferrals(int effectiveReferrals) {
    this.effectiveReferrals = effectiveReferrals;
  }

  /**
   * @return the currentUsage
   */
  public Usage getCurrentUsage() {
    return currentUsage;
  }

  /**
   * @param currentUsage
   *          the currentUsage to set
   */
  public void setCurrentUsage(Usage currentUsage) {
    this.currentUsage = currentUsage;
  }

  /**
   * @return the previousUsages
   */
  public List<Usage> getPreviousUsages() {
    return previousUsages;
  }

  /**
   * @param previousUsages
   *          the previousUsages to set
   */
  public void setPreviousUsages(List<Usage> previousUsages) {
    this.previousUsages = previousUsages;
  }

}
