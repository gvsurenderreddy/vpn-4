package net.lliira.vpn.model.user;

import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import net.lliira.vpn.model.Factory;
import net.lliira.vpn.model.TextFactory;
import net.lliira.vpn.model.dao.DaoSession;
import net.lliira.vpn.model.email.EmailFactory;

public interface UserFactory extends Factory {

  static final String FIELD_EMAIL = "email";
  static final String FIELD_REFERRAL = "referral";
  static final String FIELD_PASSWORD = "password";
  static final String FIELD_NEW_PASSWORD = "newPassword";
  static final String FIELD_PASSWORD_CONFIRM = "passwordConfirm";

  List<User> getUsers(DaoSession session);
  
  List<User> getUsers(DaoSession session, boolean activated);
  
  User getUser(DaoSession session, int id);
  
  User getUser(DaoSession session, String email);

  void deleteUser(DaoSession session, int id);

  void addUserListener(UserListener listener);

  void removeListener(UserListener listener);

  String createActivationCode();

  String createSignature(String email);

  // ===========================================================================
  // Wired properties
  // ===========================================================================

  void setEmailFactory(EmailFactory emailFactory);

  void setTextFactory(TextFactory textFactory);

  // ===========================================================================
  // User registration & activation related functions
  // ===========================================================================

  Map<String, String> validateRegister(DaoSession session, String email,
      String password, String passwordConfirm, String referral);

  Map<String, String> validateSendActivationEmail(DaoSession session,
      String email);

  /**
   * The email & password is supposed to be validated first before this method
   * is called.
   * 
   * @param email
   * @param password
   * @return
   * @throws MessagingException
   */
  User register(DaoSession session, String email, String password,
      String referral) throws MessagingException;

  User activate(DaoSession session, String email, String activationCode)
      throws UserException;

  void sendActivationEmail(DaoSession session, String email)
      throws MessagingException;

  // ===========================================================================
  // User login & logout related functions
  // ===========================================================================

  Map<String, String> validateLogin(DaoSession session, String email,
      String password);

  Map<String, String> validateForgetPassword(DaoSession session, String email);

  void forgetPassword(DaoSession session, String email)
      throws MessagingException;

  User login(DaoSession session, String email, String password)
      throws UserException;

  void logout(DaoSession session, User user);

  // ===========================================================================
  // User profile related functions
  // ===========================================================================

  Map<String, String> validateUpdateEmail(DaoSession session, User user,
      String newEmail);

  Map<String, String> validateUpdatePassword(DaoSession session, User user,
      String oldPassword, String newPassword, String passwordConfirm);

  void updatePassword(DaoSession session, User user, String password);

  void updateEmail(DaoSession session, User user, String email);

  List<Server> getServers(DaoSession session, User user);
  
  boolean isPurchased(DaoSession session, User user);
}
