package net.lliira.vpn.model;

import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;

import junit.framework.Assert;
import net.lliira.vpn.model.user.User;
import net.lliira.vpn.model.user.UserException;
import net.lliira.vpn.model.user.UserFactory;

import org.junit.Test;

public class AuthenticatorTest extends AbstractTest {

  private final UserFactory userFactory;
  private final TextFactory textFactory;

  public AuthenticatorTest() throws IOException {
    userFactory = TestHelper.getBean(UserFactory.class);
    textFactory = TestHelper.getBean(TextFactory.class);
  }

  @Test
  public void testValidateLogin() throws MessagingException, UserException {
    String email = "user" + random.nextInt() + "@test.com";
    String password = "pass" + random.nextInt();

    User user = userFactory.register(session, email, password, null);
    userFactory.activate(session, email, user.getActivationCode());

    Map<String, String> errors =
        userFactory.validateLogin(session, email, password);
    Assert.assertEquals(0, errors.size());
    for (String field : errors.keySet()) {
      System.err.println(field + " - " + errors.get(field));
    }
  }

  @Test
  public void testValidateEmailNotFound() {
    String email = "user" + random.nextInt() + "@test.com";
    String password = "pass" + random.nextInt();

    Map<String, String> errors =
        userFactory.validateLogin(session, email, password);
    Assert.assertEquals(1, errors.size());
    Assert.assertEquals("email", errors.keySet().iterator().next());
    Assert.assertEquals(textFactory.get(Errors.LOGIN_INVALID), errors.values()
        .iterator().next());
  }

  @Test
  public void testValidatePasswordNotMatch() throws UserException,
      MessagingException {
    String email = "user" + random.nextInt() + "@test.com";
    String password = "pass" + random.nextInt();

    User user = userFactory.register(session, email, password, null);
    userFactory.activate(session, email, user.getActivationCode());

    password = "pass" + random.nextInt();

    Map<String, String> errors =
        userFactory.validateLogin(session, email, password);
    Assert.assertEquals(1, errors.size());
    Assert.assertEquals("email", errors.keySet().iterator().next());
    Assert.assertEquals(textFactory.get(Errors.LOGIN_INVALID), errors.values()
        .iterator().next());
  }

  @Test
  public void testLogin() throws UserException, MessagingException,
      InterruptedException {
    String email = "user" + random.nextInt() + "@test.com";
    String password = "pass" + random.nextInt();

    User user = userFactory.register(session, email, password, null);
    System.out
        .println("reg time (in reg)        : " + user.getRegisteredTime());
    long registeredTime = user.getRegisteredTime().getTime() / 1000;
    long activatedTime =
        (user.getActivatedTime() == null) ? 0 : user.getActivatedTime()
            .getTime() / 1000;
    long lastLoginTime =
        (user.getLastLoginTime() == null) ? 0 : user.getLastLoginTime()
            .getTime() / 1000;

    Thread.sleep(100);
    user = userFactory.getUser(session, user.getId());
    System.out
        .println("reg time (after reg)     : " + user.getRegisteredTime());

    User activated =
        userFactory.activate(session, email, user.getActivationCode());
    System.out.println("reg time (in activate)   : "
        + activated.getRegisteredTime());

    Assert.assertEquals(user.getId(), activated.getId());
    Assert.assertEquals(registeredTime,
        activated.getRegisteredTime().getTime() / 1000);
    Assert
        .assertTrue(activatedTime != activated.getActivatedTime().getTime() / 1000);
    Assert
        .assertTrue(lastLoginTime != activated.getLastLoginTime().getTime() / 1000);

    activatedTime = activated.getActivatedTime().getTime() / 1000;

    Thread.sleep(100);
    user = userFactory.getUser(session, user.getId());
    System.out
        .println("reg time (after activate): " + user.getRegisteredTime());

    User login = userFactory.login(session, email, password);
    System.out.println("reg time (in login)      : "
        + login.getRegisteredTime());

    Thread.sleep(100);
    user = userFactory.getUser(session, user.getId());
    System.out
        .println("reg time (after login)   : " + user.getRegisteredTime());

    Assert.assertEquals(user.getId(), login.getId());
    Assert.assertEquals(registeredTime,
        login.getRegisteredTime().getTime() / 1000);
    Assert.assertEquals(activatedTime,
        login.getActivatedTime().getTime() / 1000);
    Assert
        .assertTrue(lastLoginTime != login.getLastLoginTime().getTime() / 1000);
  }

  @Test
  public void testValidateUpdateEmail() throws MessagingException,
      UserException {
    String email = "user" + random.nextInt() + "@test.com";
    String password = "pass" + random.nextInt();

    User user = userFactory.register(session, email, password, null);
    userFactory.activate(session, email, user.getActivationCode());

    email = "user" + random.nextInt() + "@test.com";
    Map<String, String> errors =
        userFactory.validateUpdateEmail(session, user, email);
    Assert.assertEquals(0, errors.size());
  }

  @Test
  public void testValidateUpdateEmailInvalid() throws UserException,
      MessagingException {
    String email = "user" + random.nextInt() + "@test.com";
    String password = "pass" + random.nextInt();

    User user = userFactory.register(session, email, password, null);
    userFactory.activate(session, email, user.getActivationCode());

    email = "user" + random.nextInt() + "@test";
    Map<String, String> errors =
        userFactory.validateUpdateEmail(session, user, email);
    Assert.assertEquals(1, errors.size());
    Assert.assertEquals("email", errors.keySet().iterator().next());
    Assert.assertEquals(textFactory.get(Errors.EMAIL_INVALID), errors.values()
        .iterator().next());
  }

  @Test
  public void testValidateUpdateEmailEmpty() throws UserException,
      MessagingException {
    String email = "user" + random.nextInt() + "@test.com";
    String password = "pass" + random.nextInt();

    User user = userFactory.register(session, email, password, null);
    userFactory.activate(session, email, user.getActivationCode());

    email = "";
    Map<String, String> errors =
        userFactory.validateUpdateEmail(session, user, email);
    Assert.assertEquals(1, errors.size());
    Assert.assertEquals("email", errors.keySet().iterator().next());
    Assert.assertEquals(textFactory.get(Errors.EMAIL_INVALID), errors.values()
        .iterator().next());
  }

  @Test
  public void testValidateUpdateEmailNull() throws UserException,
      MessagingException {
    String email = "user" + random.nextInt() + "@test.com";
    String password = "pass" + random.nextInt();

    User user = userFactory.register(session, email, password, null);
    userFactory.activate(session, email, user.getActivationCode());

    email = null;
    Map<String, String> errors =
        userFactory.validateUpdateEmail(session, user, email);
    Assert.assertEquals(1, errors.size());
    Assert.assertEquals("email", errors.keySet().iterator().next());
    Assert.assertEquals(textFactory.get(Errors.EMAIL_INVALID), errors.values()
        .iterator().next());
  }

  @Test
  public void testUpdateEmail() throws UserException, MessagingException {
    String email = "user" + random.nextInt() + "@test.com";
    String password = "pass" + random.nextInt();

    User user = userFactory.register(session, email, password, null);
    userFactory.activate(session, email, user.getActivationCode());

    email = "user" + random.nextInt() + "@test.com";
    userFactory.updateEmail(session, user, email);
    Assert.assertEquals(email, user.getEmail());

    user = userFactory.getUser(session, user.getId());

    Assert.assertEquals(email, user.getEmail());
  }

  @Test
  public void testValidateUpdatePassword() throws UserException,
      MessagingException {
    String email = "user" + random.nextInt() + "@test.com";
    String password = "pass" + random.nextInt();

    User user = userFactory.register(session, email, password, null);
    userFactory.activate(session, email, user.getActivationCode());

    String oldPassword = password;
    password = "user" + random.nextInt() + "@test.com";
    Map<String, String> errors =
        userFactory.validateUpdatePassword(session, user, oldPassword,
            password, password);
    Assert.assertEquals(0, errors.size());
  }

  @Test
  public void testValidateUpdatePasswordOldInvalid() throws MessagingException,
      UserException {
    String email = "user" + random.nextInt() + "@test.com";
    String password = "pass" + random.nextInt();

    User user = userFactory.register(session, email, password, null);
    userFactory.activate(session, email, user.getActivationCode());

    String oldPassword = "user" + random.nextInt() + "@test.com";
    password = "user" + random.nextInt() + "@test.com";
    Map<String, String> errors =
        userFactory.validateUpdatePassword(session, user, oldPassword,
            password, password);
    Assert.assertEquals(1, errors.size());
    Assert.assertEquals("oldPassword", errors.keySet().iterator().next());
    Assert.assertEquals(textFactory.get(Errors.PASSWORD_OLD_INVALID), errors
        .values().iterator().next());
  }

  @Test
  public void testValidateUpdatePasswordUnmatched() throws UserException,
      MessagingException {
    String email = "user" + random.nextInt() + "@test.com";
    String password = "pass" + random.nextInt();

    User user = userFactory.register(session, email, password, null);
    userFactory.activate(session, email, user.getActivationCode());

    String oldPassword = password;
    password = "user" + random.nextInt() + "@test.com";
    String passwordConfirm = "user" + random.nextInt() + "@test.com";
    Map<String, String> errors =
        userFactory.validateUpdatePassword(session, user, oldPassword,
            password, passwordConfirm);
    Assert.assertEquals(1, errors.size());
    Assert.assertEquals("passwordConfirm", errors.keySet().iterator().next());
    Assert.assertEquals(textFactory.get(Errors.PASSWORD_NOT_MATCH), errors
        .values().iterator().next());
  }

  @Test
  public void testValidateUpdatePasswordEmpty() throws MessagingException,
      UserException {
    String email = "user" + random.nextInt() + "@test.com";
    String password = "pass" + random.nextInt();

    User user = userFactory.register(session, email, password, null);
    userFactory.activate(session, email, user.getActivationCode());

    String oldPassword = password;
    password = "";
    Map<String, String> errors =
        userFactory.validateUpdatePassword(session, user, oldPassword,
            password, password);
    Assert.assertEquals(1, errors.size());
    Assert.assertEquals("password", errors.keySet().iterator().next());
    Assert.assertEquals(textFactory.get(Errors.PASSWORD_INVALID), errors
        .values().iterator().next());
  }

  @Test
  public void testValidateUpdatePasswordNull() throws MessagingException,
      UserException {
    String email = "user" + random.nextInt() + "@test.com";
    String password = "pass" + random.nextInt();

    User user = userFactory.register(session, email, password, null);
    userFactory.activate(session, email, user.getActivationCode());

    String oldPassword = password;
    password = null;
    Map<String, String> errors =
        userFactory.validateUpdatePassword(session, user, oldPassword,
            password, password);
    Assert.assertEquals(1, errors.size());
    Assert.assertEquals("password", errors.keySet().iterator().next());
    Assert.assertEquals(textFactory.get(Errors.PASSWORD_INVALID), errors
        .values().iterator().next());
  }

  @Test
  public void testUpdatePassword() throws UserException, MessagingException {
    String email = "user" + random.nextInt() + "@test.com";
    String password = "pass" + random.nextInt();

    User user = userFactory.register(session, email, password, null);
    user = userFactory.activate(session, email, user.getActivationCode());
    Assert.assertTrue(user.isActivated());

    password = "pass" + random.nextInt();
    userFactory.updatePassword(session, user, password);
    user = userFactory.getUser(session, user.getId());

    user = userFactory.getUser(session, user.getId());
    Assert.assertEquals(email, user.getEmail());
    Assert.assertEquals(password, user.getPassword());
    Assert.assertTrue(user.isActivated());

    User login = userFactory.login(session, email, password);

    Assert.assertEquals(user.getId(), login.getId());
    Assert.assertEquals(user.getPassword(), login.getPassword());
  }

  @Test
  public void testDeleteUser() throws UserException, MessagingException {
    String email = "user" + random.nextInt() + "@test.com";
    String password = "pass" + random.nextInt();

    User user = userFactory.register(session, email, password, null);
    userFactory.activate(session, email, user.getActivationCode());
    userFactory.deleteUser(session, user.getId());

    user = userFactory.getUser(session, user.getId());
    Assert.assertNull(user);
  }

  @Test
  public void testValidateForgetPassword() throws MessagingException,
      UserException {
    String email = "user" + random.nextInt() + "@test.com";
    String password = "pass" + random.nextInt();

    User user = userFactory.register(session, email, password, null);
    userFactory.activate(session, email, user.getActivationCode());

    Map<String, String> errors =
        userFactory.validateForgetPassword(session, email);
    Assert.assertEquals(0, errors.size());
    for (String field : errors.keySet()) {
      System.err.println(field + " - " + errors.get(field));
    }
  }

  @Test
  public void testValidateForgetPasswordEmailNotFound() {
    String email = "user" + random.nextInt() + "@test.com";

    Map<String, String> errors =
        userFactory.validateForgetPassword(session, email);
    Assert.assertEquals(1, errors.size());
    Assert.assertEquals("email", errors.keySet().iterator().next());
    Assert.assertEquals(textFactory.get(Errors.EMAIL_NOT_FOUND), errors
        .values().iterator().next());
  }

  @Test
  public void testForgetPassword() throws MessagingException, UserException {
    String email = "user" + random.nextInt() + "@test.com";
    String password = "pass" + random.nextInt();

    User user = userFactory.register(session, email, password, null);
    userFactory.activate(session, email, user.getActivationCode());

    userFactory.forgetPassword(session, email);
  }

}
