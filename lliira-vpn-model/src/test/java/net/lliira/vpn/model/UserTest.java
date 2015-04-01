package net.lliira.vpn.model;

import java.util.Date;

import javax.mail.MessagingException;

import net.lliira.vpn.model.user.User;
import net.lliira.vpn.model.user.UserException;
import net.lliira.vpn.model.user.UserFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserTest extends AbstractTest {

  public static void compareTime(Date expect, Date actual) {
    // since the database version of time doesn't store millie seconds part,
    // will remove it and compare
    long exp = expect.getTime() / 1000;
    long act = actual.getTime() / 1000;
    Assert.assertEquals(exp, act);
  }

  private final UserFactory userFactory;

  private User user;

  public UserTest() {
    userFactory = TestHelper.getBean(UserFactory.class);
  }

  @Before
  public void createUser() throws MessagingException, UserException {
    // create a login
    String email = "user" + random.nextInt() + "@test.com";
    String password = "pass" + random.nextInt();
    user = userFactory.register(session, email, password, null);
    userFactory.activate(session, email, user.getActivationCode());
  }

  @Test
  public void testCreateUser() throws MessagingException {
    String email = "user-" + random.nextInt() + "@test.com";
    String password = "pass-" + random.nextInt();
    Date registeredTime = new Date();
    User user = userFactory.register(session, email, password, null);

    Assert.assertEquals(email, user.getEmail());
    Assert.assertEquals(password, user.getPassword());
    compareTime(registeredTime, user.getRegisteredTime());
    Assert.assertNotNull(user.getSignature());
  }

  @Test
  public void testGetUser() throws MessagingException {
    String email = "user-" + random.nextInt() + "@test.com";
    String password = "pass-" + random.nextInt();
    User user = userFactory.register(session, email, password, null);

    User savedUser = userFactory.getUser(session, user.getId());
    Assert.assertEquals(user.getId(), savedUser.getId());
    compareTime(user.getRegisteredTime(), savedUser.getRegisteredTime());
    Assert.assertNotNull(user.getSignature());
  }

  @Test
  public void testLoginUser() throws MessagingException, UserException {
    String email = "user-" + random.nextInt() + "@test.com";
    String password = "pass-" + random.nextInt();
    User user = userFactory.register(session, email, password, null);
    userFactory.activate(session, email, user.getActivationCode());

    Date now = new Date();

    User loginUser = userFactory.login(session, email, password);

    Assert.assertEquals(user.getId(), loginUser.getId());
    compareTime(user.getRegisteredTime(), loginUser.getRegisteredTime());
    Assert.assertTrue(now.compareTo(loginUser.getLastLoginTime()) <= 0);
  }

  @Test
  public void testDeleteUser() throws MessagingException {
    String email = "user-" + random.nextInt() + "@test.com";
    String password = "pass-" + random.nextInt();
    User user = userFactory.register(session, email, password, null);

    userFactory.deleteUser(session, user.getId());

    user = userFactory.getUser(session, user.getId());
    Assert.assertNull(user);
  }
}
