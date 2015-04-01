package net.lliira.vpn.model;

import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;

import junit.framework.Assert;
import net.lliira.vpn.model.user.User;
import net.lliira.vpn.model.user.UserException;
import net.lliira.vpn.model.user.UserFactory;

import org.apache.log4j.Logger;
import org.junit.Test;

public class RegistratorTest extends AbstractTest {

  private static final Logger logger = Logger.getLogger(RegistratorTest.class);

  private final UserFactory userFactory;
  private final TextFactory textFactory;

  public RegistratorTest() throws IOException {
    userFactory = TestHelper.getBean(UserFactory.class);
    textFactory = TestHelper.getBean(TextFactory.class);
  }

  @Test
  public void testValidateRegister() {
    logger.debug("validating register...");
    String email = "user" + random.nextInt() + "@test.com";
    String password = "pass" + random.nextInt();
    Map<String, String> errors =
        userFactory.validateRegister(session, email, password, password, null);
    if (errors.size() > 0) {
      for (String error : errors.values()) {
        logger.debug(error);
      }
    }
    Assert.assertEquals(0, errors.size());
  }

  @Test
  public void testValidateRegisterEmailEmpty() {
    String email = "";
    String password = "pass" + random.nextInt();
    Map<String, String> errors =
        userFactory.validateRegister(session, email, password, password, null);
    Assert.assertEquals(1, errors.size());
    Assert.assertEquals("email", errors.keySet().iterator().next());
    Assert.assertEquals(textFactory.get(Errors.EMAIL_INVALID), errors.values()
        .iterator().next());
  }

  @Test
  public void testValidateRegisterEmailNull() {
    String email = null;
    String password = "pass" + random.nextInt();
    Map<String, String> errors =
        userFactory.validateRegister(session, email, password, password, null);
    Assert.assertEquals(1, errors.size());
    Assert.assertEquals("email", errors.keySet().iterator().next());
    Assert.assertEquals(textFactory.get(Errors.EMAIL_INVALID), errors.values()
        .iterator().next());
  }

  @Test
  public void testValidateRegisterEmailInvalid() {
    String email = "user" + random.nextInt() + "@" + random.nextInt();
    String password = "pass" + random.nextInt();
    Map<String, String> errors =
        userFactory.validateRegister(session, email, password, password, null);
    Assert.assertEquals(1, errors.size());
    Assert.assertEquals("email", errors.keySet().iterator().next());
    Assert.assertEquals(textFactory.get(Errors.EMAIL_INVALID), errors.values()
        .iterator().next());
  }

  @Test
  public void testValidateRegisterEmailDuplicated() throws MessagingException {
    String email = "user" + random.nextInt() + "@test.com";
    String password = "pass" + random.nextInt();

    userFactory.register(session, email, password, null);

    // validate register another user with same email
    logger.debug("validating register with duplicate email.");
    password = "pass" + random.nextInt();
    Map<String, String> errors =
        userFactory.validateRegister(session, email, password, password, null);
    Assert.assertEquals(1, errors.size());
    Assert.assertEquals("email", errors.keySet().iterator().next());
    Assert.assertEquals(textFactory.get(Errors.EMAIL_DUPLICATED), errors
        .values().iterator().next());
  }

  @Test
  public void testValidateRegisterPasswordUnmatched() {
    String email = "user" + random.nextInt() + "@test.com";
    String password = "pass" + random.nextInt();
    String passwordConfirm = "pass" + random.nextInt();
    Map<String, String> errors =
        userFactory.validateRegister(session, email, password, passwordConfirm,
            null);
    Assert.assertEquals(1, errors.size());
    Assert.assertEquals("passwordConfirm", errors.keySet().iterator().next());
    Assert.assertEquals(textFactory.get(Errors.PASSWORD_NOT_MATCH), errors
        .values().iterator().next());
  }

  @Test
  public void testValidateRegisterPasswordEmpty() {
    String email = "user" + random.nextInt() + "@test.com";
    String password = "";
    String passwordConfirm = "pass" + random.nextInt();
    Map<String, String> errors =
        userFactory.validateRegister(session, email, password, passwordConfirm,
            null);
    Assert.assertEquals(1, errors.size());
    Assert.assertEquals("password", errors.keySet().iterator().next());
    Assert.assertEquals(textFactory.get(Errors.PASSWORD_INVALID), errors
        .values().iterator().next());
  }

  @Test
  public void testValidateRegisterPasswordNull() {
    String email = "user" + random.nextInt() + "@test.com";
    String password = null;
    String passwordConfirm = "pass" + random.nextInt();
    Map<String, String> errors =
        userFactory.validateRegister(session, email, password, passwordConfirm,
            null);
    Assert.assertEquals(1, errors.size());
    Assert.assertEquals("password", errors.keySet().iterator().next());
    Assert.assertEquals(textFactory.get(Errors.PASSWORD_INVALID), errors
        .values().iterator().next());
  }

  @Test
  public void testRegister() throws MessagingException {
    String email = "user" + random.nextInt() + "@test.com";
    String password = "pass" + random.nextInt();

    User user = userFactory.register(session, email, password, null);

    Assert.assertTrue(user.getId() > 0);
    Assert.assertEquals(email, user.getEmail());
    Assert.assertEquals(password, user.getPassword());
    Assert.assertNotNull(user.getActivationCode());
    Assert.assertNotNull(user.getRegisteredTime());
  }

  @Test
  public void testActivate() throws UserException, MessagingException {
    // create a temp user
    String email = "user" + random.nextInt();
    String password = "pass" + random.nextInt();
    User tempUser = userFactory.register(session, email, password, null);
    Assert.assertFalse(tempUser.isActivated());

    User user =
        userFactory.activate(session, email, tempUser.getActivationCode());
    Assert.assertEquals(tempUser.getId(), user.getId());
    Assert.assertTrue(user.isActivated());
    UserTest
        .compareTime(tempUser.getRegisteredTime(), user.getRegisteredTime());

    User actUser = userFactory.getUser(session, user.getId());
    Assert.assertEquals(tempUser.getId(), actUser.getId());
    Assert.assertTrue(actUser.isActivated());
  }

  @Test(expected = UserException.class)
  public void testActivateInvalidEmail() throws UserException,
      MessagingException {
    // create a temp user
    String email = "user" + random.nextInt();
    String password = "pass" + random.nextInt();
    User tempUser = userFactory.register(session, email, password, null);

    // use a non-existent
    email = "user" + random.nextInt();
    userFactory.activate(session, email, tempUser.getActivationCode());
  }

  @Test(expected = UserException.class)
  public void testActivateInvalidCode() throws UserException,
      MessagingException {
    // create a temp user
    String email = "user" + random.nextInt();
    String password = "pass" + random.nextInt();
    userFactory.register(session, email, password, null);

    // use a wrong code
    String activationCode = "code" + random.nextInt();
    userFactory.activate(session, email, activationCode);
  }
}
