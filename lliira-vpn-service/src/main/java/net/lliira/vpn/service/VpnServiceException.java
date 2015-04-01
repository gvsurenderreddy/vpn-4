package net.lliira.vpn.service;

public class VpnServiceException extends Exception {

  /**
   * 
   */
  private static final long serialVersionUID = -1898926525702549320L;

  public VpnServiceException() {}

  public VpnServiceException(String message) {
    super(message);
  }

  public VpnServiceException(Throwable cause) {
    super(cause);
  }

  public VpnServiceException(String message, Throwable cause) {
    super(message, cause);
  }

  public VpnServiceException(String message, Throwable cause,
      boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}
