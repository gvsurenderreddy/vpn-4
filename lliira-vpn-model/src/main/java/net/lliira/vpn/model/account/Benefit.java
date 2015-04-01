package net.lliira.vpn.model.account;

public class Benefit {

  private long freeBandwidth;
  private int referralLevel;
  private long referralBandwidth;
  private int referralMax;

  /**
   * @return the freeBandwidth
   */
  public long getFreeBandwidth() {
    return freeBandwidth;
  }

  /**
   * @param freeBandwidth
   *          the freeBandwidth to set
   */
  public void setFreeBandwidth(long freeBandwidth) {
    this.freeBandwidth = freeBandwidth;
  }

  /**
   * @return the referralLevel
   */
  public int getReferralLevel() {
    return referralLevel;
  }

  /**
   * @param referralLevel
   *          the referralLevel to set
   */
  public void setReferralLevel(int referralLevel) {
    this.referralLevel = referralLevel;
  }

  /**
   * @return the referralBandwidth
   */
  public long getReferralBandwidth() {
    return referralBandwidth;
  }

  /**
   * @param referralBandwidth
   *          the referralBandwidth to set
   */
  public void setReferralBandwidth(long referralBandwidth) {
    this.referralBandwidth = referralBandwidth;
  }

  /**
   * @return the referralMax
   */
  public int getReferralMax() {
    return referralMax;
  }

  /**
   * @param referralMax
   *          the referralMax to set
   */
  public void setReferralMax(int referralMax) {
    this.referralMax = referralMax;
  }

}
