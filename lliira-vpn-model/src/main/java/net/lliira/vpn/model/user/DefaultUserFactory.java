/**
 * 
 */
package net.lliira.vpn.model.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.mail.MessagingException;

import net.lliira.vpn.model.Errors;
import net.lliira.vpn.model.TextFactory;
import net.lliira.vpn.model.VpnModel;
import net.lliira.vpn.model.dao.DaoSession;
import net.lliira.vpn.model.dao.mapper.UserMapper;
import net.lliira.vpn.model.email.EmailFactory;

import org.apache.log4j.Logger;

/**
 * @author jerric
 * 
 */
public class DefaultUserFactory implements UserFactory {

  private static final int ACTIVATION_CODE_LENGTH = 32;
  private static final String EMAIL_REGEX =
      "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$";

  private static final Logger logger = Logger
      .getLogger(DefaultUserFactory.class);

  private final List<UserListener> userListeners;

  private EmailFactory emailFactory;
  private TextFactory textFactory;

  public DefaultUserFactory() {
    userListeners = new ArrayList<UserListener>();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * net.lliira.vpn.model.user.UserFactory#addUserListener(net.lliira.vpn.model
   * .user.UserListener)
   */
  @Override
  public void addUserListener(UserListener listener) {
    userListeners.add(listener);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * net.lliira.vpn.model.user.UserFactory#removeListener(net.lliira.vpn.model
   * .user.UserListener)
   */
  @Override
  public void removeListener(UserListener listener) {
    userListeners.remove(listener);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * net.lliira.vpn.model.user.UserManager#setEmailManager(net.lliira.vpn.model
   * .email.EmailManager)
   */
  @Override
  public void setEmailFactory(EmailFactory emailFactory) {
    this.emailFactory = emailFactory;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * net.lliira.vpn.model.user.UserManager#setTextManager(net.lliira.vpn.model
   * .TextManager)
   */
  @Override
  public void setTextFactory(TextFactory textFactory) {
    this.textFactory = textFactory;
  }

  /*
   * (non-Javadoc)
   * 
   * @see net.lliira.vpn.model.user.UserFactory#getUser(int)
   */
  @Override
  public User getUser(DaoSession session, int id) {
    UserMapper mapper = session.getMapper(UserMapper.class);
    User user = mapper.selectUserById(id);
    if (user != null) user.setRoles(mapper.selectUserRoles(user));
    return user;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * net.lliira.vpn.model.user.UserFactory#getUser(net.lliira.vpn.model.dao.
   * DaoSession, java.lang.String)
   */
  @Override
  public User getUser(DaoSession session, String email) {
    UserMapper mapper = session.getMapper(UserMapper.class);
    User user = mapper.selectUserByEmail(email);
    if (user != null) user.setRoles(mapper.selectUserRoles(user));
    return user;
  }

  /*
   * (non-Javadoc)
   * 
   * @see net.lliira.vpn.model.user.UserFactory#getUsers()
   */
  @Override
  public List<User> getUsers(DaoSession session) {
    UserMapper mapper = session.getMapper(UserMapper.class);
    return mapper.selectAllUsers();
  }

  /*
   * (non-Javadoc)
   * 
   * @see net.lliira.vpn.model.user.UserFactory#getUsers(boolean)
   */
  @Override
  public List<User> getUsers(DaoSession session, boolean activated) {
    UserMapper mapper = session.getMapper(UserMapper.class);
    return mapper.selectUsersByActivation(activated);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * net.lliira.vpn.model.user.UserManager#validateRegister(net.lliira.vpn.model
   * .dao.DaoSession, java.lang.String, java.lang.String, java.lang.String)
   */
  @Override
  public Map<String, String> validateRegister(DaoSession session, String email,
      String password, String passwordConfirm, String referral) {
    // validate password
    Map<String, String> errors = new HashMap<String, String>();
    if (password == null || password.trim().length() == 0) {
      errors.put(FIELD_PASSWORD, textFactory.get(Errors.PASSWORD_INVALID));
      return errors;
    }

    if (!password.equals(passwordConfirm)) {
      errors.put(FIELD_PASSWORD_CONFIRM,
          textFactory.get(Errors.PASSWORD_NOT_MATCH));
      return errors;
    }

    // validate email
    if (email == null || !email.toUpperCase().matches(EMAIL_REGEX)) {
      logger.debug("Invalid email = " + email);
      errors.put(FIELD_EMAIL, textFactory.get(Errors.EMAIL_INVALID));
      return errors;
    }

    // check if the email has been used.
    UserMapper mapper = session.getMapper(UserMapper.class);
    User user = mapper.selectUserByEmail(email);
    if (user != null) {
      logger.debug("Duplicate temp email = " + email);
      errors.put(FIELD_EMAIL, textFactory.get(Errors.EMAIL_DUPLICATED));
      return errors;
    }

    // check if the referral is valid
    if (referral != null) {
      referral = referral.trim();
      if (referral.length() > 0) {
        user = mapper.selectUserBySignature(referral);
        if (user == null) {
          logger.debug("Invalid referral = " + referral);
          errors.put(FIELD_REFERRAL, textFactory.get(Errors.REFERRAL_INVALID));
          return errors;
        }
      }
    }

    return errors;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * net.lliira.vpn.model.user.UserManager#validateSendActivationEmail(net.lliira
   * .vpn.model.dao.DaoSession, java.lang.String)
   */
  @Override
  public Map<String, String> validateSendActivationEmail(DaoSession session,
      String email) {
    Map<String, String> errors = new HashMap<String, String>();

    // validate email
    if (email == null || !email.toUpperCase().matches(EMAIL_REGEX)) {
      logger.debug("Invalid email = " + email);
      errors.put(FIELD_EMAIL, textFactory.get(Errors.EMAIL_INVALID));
      return errors;
    }

    // check if the email exists
    UserMapper mapper = session.getMapper(UserMapper.class);
    User user = mapper.selectUserByEmail(email);
    if (user == null) {
      logger.debug("email not found = " + email);
      errors.put(FIELD_EMAIL, textFactory.get(Errors.EMAIL_NOT_FOUND));
      return errors;
    }

    // check if user is already activated
    if (user.isActivated()) {
      logger.debug("User is already activated " + email);
      errors.put(FIELD_EMAIL, textFactory.get(Errors.USER_ACTIVATED));
      return errors;
    }

    return errors;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * net.lliira.vpn.model.user.UserManager#register(net.lliira.vpn.model.dao
   * .DaoSession, java.lang.String, java.lang.String)
   */
  @Override
  public User register(DaoSession session, String email, String password,
      String referral) throws MessagingException {
    // create user
    User user = new User();
    user.setEmail(email);
    user.setPassword(password);
    user.setRegisteredTime(new Date());

    // generate a activation code
    user.setActivationCode(createActivationCode());

    // generate signature
    String seed = System.currentTimeMillis() + "@" + email;
    user.setSignature(VpnModel.generateHash(seed));

    // get referral
    UserMapper mapper = session.getMapper(UserMapper.class);
    if (referral != null) {
      referral = referral.trim();
      if (referral.length() > 0) {
        User refer = mapper.selectUserBySignature(referral);
        user.setReferral(refer.getId());
      }
    }

    // insert the user
    mapper.insertUser(user);

    for (UserListener listener : userListeners) {
      listener.onRegister(session, user);
    }

    // send email to the user
    emailFactory.sendRegistrationEmail(email, user.getActivationCode());

    return user;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * net.lliira.vpn.model.user.UserManager#activate(net.lliira.vpn.model.dao
   * .DaoSession, java.lang.String, java.lang.String)
   */
  @Override
  public User activate(DaoSession session, String email, String activationCode)
      throws UserException {
    UserMapper mapper = session.getMapper(UserMapper.class);
    User user = mapper.selectUserByEmail(email);
    if (user == null)
      throw new UserException(textFactory.get(Errors.EMAIL_NOT_FOUND));

    // check if user has been activated
    if (user.isActivated())
      throw new UserException(textFactory.get(Errors.USER_ACTIVATED));

    // check if activation code is valid
    if (!activationCode.equals(user.getActivationCode()))
      throw new UserException(textFactory.get(Errors.ACTIVATION_CODE_INVALID));

    // store user activation info
    user.setActivated(true);
    user.setActivatedTime(new Date());
    mapper.updateUser(user);

    for (UserListener listener : userListeners) {
      listener.onActivate(session, user);
    }

    return user;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * net.lliira.vpn.model.user.UserManager#sendActivationEmail(net.lliira.vpn
   * .model.dao.DaoSession, java.lang.String)
   */
  @Override
  public void sendActivationEmail(DaoSession session, String email)
      throws MessagingException {
    UserMapper mapper = session.getMapper(UserMapper.class);
    User user = mapper.selectUserByEmail(email);

    // send email to the user
    emailFactory.sendRegistrationEmail(email, user.getActivationCode());
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * net.lliira.vpn.model.user.UserManager#validateLogin(net.lliira.vpn.model
   * .dao.DaoSession, java.lang.String, java.lang.String)
   */
  @Override
  public Map<String, String> validateLogin(DaoSession session, String email,
      String password) {
    Map<String, String> errors = new HashMap<String, String>();

    // validate email
    if (email == null || !email.toUpperCase().matches(EMAIL_REGEX)) {
      errors.put(FIELD_EMAIL, textFactory.get(Errors.EMAIL_INVALID));
      return errors;
    }

    UserMapper mapper = session.getMapper(UserMapper.class);
    User user = mapper.selectUserByEmail(email);

    if (user == null || !user.isActivated() || user.isBlocked()
        || !user.getPassword().equals(password)) {
      errors.put("email", textFactory.get(Errors.LOGIN_INVALID));
      return errors;
    }

    return errors;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * net.lliira.vpn.model.user.UserManager#validateForgetPassword(net.lliira
   * .vpn.model.dao.DaoSession, java.lang.String)
   */
  @Override
  public Map<String, String> validateForgetPassword(DaoSession session,
      String email) {
    Map<String, String> errors = new HashMap<String, String>();

    // validate email
    if (email == null || !email.toUpperCase().matches(EMAIL_REGEX)) {
      errors.put(FIELD_EMAIL, textFactory.get(Errors.EMAIL_INVALID));
      return errors;
    }

    // validate email
    UserMapper mapper = session.getMapper(UserMapper.class);
    User user = mapper.selectUserByEmail(email);

    if (user == null || !user.isActivated()) {
      errors.put("email", textFactory.get(Errors.EMAIL_NOT_FOUND));
      return errors;
    }

    return errors;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * net.lliira.vpn.model.user.UserManager#forgetPassword(net.lliira.vpn.model
   * .dao.DaoSession, java.lang.String)
   */
  @Override
  public void forgetPassword(DaoSession session, String email)
      throws MessagingException {
    UserMapper mapper = session.getMapper(UserMapper.class);
    User user = mapper.selectUserByEmail(email);

    emailFactory.sendForgetPasswordEmail(email, user.getPassword());
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * net.lliira.vpn.model.user.UserManager#login(net.lliira.vpn.model.dao.DaoSession
   * , java.lang.String, java.lang.String)
   */
  @Override
  public User login(DaoSession session, String email, String password)
      throws UserException {
    UserMapper mapper = session.getMapper(UserMapper.class);
    User user = mapper.selectUserByEmail(email);

    if (user == null || !user.isActivated() || user.isBlocked()
        || !password.equals(user.getPassword()))
      throw new UserException(textFactory.get(Errors.LOGIN_INVALID));

    user.setRoles(mapper.selectUserRoles(user));

    // log user login events
    user.setLastLoginTime(new Date());
    mapper.updateUser(user);

    UserLog log = new UserLog();
    log.setUserId(user.getId());
    log.setLogTime(user.getLastLoginTime());
    log.setType("LOGIN");
    log.setMessage("Login success");
    mapper.insertUserLog(log);

    for (UserListener listener : userListeners) {
      listener.onLogin(session, user);
    }

    return user;
  }

  /*
   * (non-Javadoc)
   * 
   * @see net.lliira.vpn.model.user.UserManager#logout(net.lliira.vpn.model.dao.
   * DaoSession)
   */
  @Override
  public void logout(DaoSession session, User user) {
    UserMapper mapper = session.getMapper(UserMapper.class);

    // log events
    UserLog log = new UserLog();
    log.setUserId(user.getId());
    log.setLogTime(user.getLastLoginTime());
    log.setType("LOGIN");
    log.setMessage("Login success");
    mapper.insertUserLog(log);

    for (UserListener listener : userListeners) {
      listener.onLogout(session, user);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * net.lliira.vpn.model.user.UserManager#validateUpdateEmail(net.lliira.vpn
   * .model.dao.DaoSession, int, java.lang.String)
   */
  @Override
  public Map<String, String> validateUpdateEmail(DaoSession session, User user,
      String newEmail) {
    Map<String, String> errors = new HashMap<String, String>();

    if (user.getEmail().equals(newEmail)) return errors;

    // check if email is valid
    if (newEmail == null || !newEmail.toUpperCase().matches(EMAIL_REGEX)) {
      errors.put("email", textFactory.get(Errors.EMAIL_INVALID));
      return errors;
    }

    // check if email is duplicated
    UserMapper mapper = session.getMapper(UserMapper.class);
    User otherUser = mapper.selectUserByEmail(newEmail);

    if (otherUser != null && user.getId() != otherUser.getId()) {
      errors.put("password", textFactory.get(Errors.EMAIL_DUPLICATED));
      return errors;
    }

    return errors;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * net.lliira.vpn.model.user.UserManager#validateUpdatePassword(net.lliira
   * .vpn.model.dao.DaoSession, int, java.lang.String, java.lang.String,
   * java.lang.String)
   */
  @Override
  public Map<String, String> validateUpdatePassword(DaoSession session,
      User user, String oldPassword, String newPassword, String passwordConfirm) {
    Map<String, String> errors = new HashMap<String, String>();

    // check if password is valid
    if (newPassword == null || newPassword.trim().length() == 0) {
      errors.put("password", textFactory.get(Errors.PASSWORD_INVALID));
      return errors;
    }

    // check if password matches
    if (!newPassword.equals(passwordConfirm)) {
      errors.put("passwordConfirm", textFactory.get(Errors.PASSWORD_NOT_MATCH));
      return errors;
    }

    if (!oldPassword.matches(user.getPassword())) {
      errors.put("oldPassword", textFactory.get(Errors.PASSWORD_OLD_INVALID));
      return errors;
    }

    return errors;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * net.lliira.vpn.model.user.UserManager#updatePassword(net.lliira.vpn.model
   * .dao.DaoSession, int, java.lang.String)
   */
  @Override
  public void updatePassword(DaoSession session, User user, String password) {
    UserMapper mapper = session.getMapper(UserMapper.class);

    if (user.getPassword().equals(password)) return;

    // save password.
    String oldPassword = user.getPassword();
    user.setPassword(password);
    mapper.updateUser(user);

    for (UserListener listener : userListeners) {
      listener.onChangePassword(session, user, oldPassword);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * net.lliira.vpn.model.user.UserManager#updateEmail(net.lliira.vpn.model.
   * dao.DaoSession, int, java.lang.String)
   */
  @Override
  public void updateEmail(DaoSession session, User user, String email) {
    if (user.getEmail().equals(email)) return;

    String oldEmail = user.getEmail();
    user.setEmail(email);
    UserMapper mapper = session.getMapper(UserMapper.class);
    mapper.updateUser(user);

    for (UserListener listener : userListeners) {
      listener.onChangeEmail(session, user, oldEmail);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * net.lliira.vpn.model.user.UserFactory#deleteUser(net.lliira.vpn.model.dao
   * .DaoSession, int)
   */
  @Override
  public void deleteUser(DaoSession session, int id) {
    UserMapper mapper = session.getMapper(UserMapper.class);
    mapper.deleteUser(id);
  }

  @Override
  public String createActivationCode() {
    Random random = new Random();
    StringBuilder buffer = new StringBuilder();
    for (int i = 0; i < ACTIVATION_CODE_LENGTH; i++) {
      int code = random.nextInt(62);
      char ch;
      if (code < 26) {
        ch = (char) ('A' + code);
      } else if (code < 52) {
        ch = (char) ('a' + code - 26);
      } else {
        ch = (char) ('0' + code - 52);
      }
      buffer.append(ch);
    }
    return buffer.toString();
  }

  @Override
  public String createSignature(String email) {
    // generate signature
    String seed = System.currentTimeMillis() + "@" + email;
    return VpnModel.generateHash(seed);
  }

  @Override
  public List<Server> getServers(DaoSession session, User user) {
    UserMapper mapper = session.getMapper(UserMapper.class);
    List<Server> servers = mapper.selectServers();

    // now filter out the servers that given user cannot use.
    boolean isActivated = (user != null) ? user.isActivated() : false;
    boolean isPurchased = (user != null) ? isPurchased(session, user) : false;
    
    logger.debug("Total servers: " + servers.size() + ", activated=" + isActivated + ", purchased=" + isPurchased);

    List<Server> list = new ArrayList<Server>();
    for (Server server : servers) {
      if (server.isForPublic()) {
        list.add(server);
      } else if (server.isForRegisteredUsers() && isActivated) {
        list.add(server);
      } else if (server.isForPurchasedUsers() && isPurchased) {
        list.add(server);
      }

    }
    return list;
  }

  @Override
  public boolean isPurchased(DaoSession session, User user) {
    UserMapper mapper = session.getMapper(UserMapper.class);
    return mapper.selectIsPurchased(user.getId());
  }
}
