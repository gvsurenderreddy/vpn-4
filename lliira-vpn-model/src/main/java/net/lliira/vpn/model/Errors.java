package net.lliira.vpn.model;

public interface Errors {

  // ======================== Authentication errors ========================
  public static final String EMAIL_INVALID = "email-invalid";
  public static final String EMAIL_DUPLICATED = "email-duplicated";
  public static final String EMAIL_NOT_FOUND = "email-not-found";
  public static final String PASSWORD_INVALID = "password-invalid";
  public static final String PASSWORD_NOT_MATCH = "password-not-match";
  public static final String PASSWORD_OLD_INVALID = "password-old-invalid";
  public static final String REFERRAL_INVALID = "referral-invalid";
  public static final String ACTIVATION_CODE_INVALID =
      "activation-code-invalid";
  public static final String LOGIN_INVALID = "login-invalid";

  // ======================== User errors ========================
  public static final String USER_NAME_INVALID = "user-name-invalid";
  public static final String USER_ACTIVATED = "user-activated";
  public static final String USER_NOT_ACTIVATED = "user-not-activated";
  
  // ======================== User errors ========================
  public static final String BANDWIDTH_INVALID = "bandwidth-invalid";
  public static final String DURATION_INVALID = "duration-invalid";
  public static final String PRICE_INVALID = "price-invalid";
  
  // ======================== Admin errors ========================
  public static final String ANOUNCEMENT_INVALID = "anouncement-invalid";

}
