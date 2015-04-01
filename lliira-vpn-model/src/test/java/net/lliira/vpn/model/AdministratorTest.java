package net.lliira.vpn.model;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.mail.MessagingException;

import net.lliira.vpn.model.admin.AdminFactory;
import net.lliira.vpn.model.user.User;
import net.lliira.vpn.model.user.UserException;
import net.lliira.vpn.model.user.UserFactory;

import org.junit.Assert;
import org.junit.Test;

public class AdministratorTest extends AbstractTest {

  private final AdminFactory administrator;

  public AdministratorTest() throws IOException {
    administrator = TestHelper.getBean(AdminFactory.class);
  }

  @Test
  public void testGetAllEmails() throws MessagingException, UserException {
    // create some test users
    String email = "user-" + random.nextInt() + "@test.com";
    String password = "pass-" + random.nextInt();
    UserFactory userFactory = TestHelper.getBean(UserFactory.class);
    userFactory.register(session, email, password, null);

    List<String> emails = administrator.getEmails(session);
    Collections.sort(emails);
    Assert.assertTrue(emails.size() > 0);
    Assert.assertTrue(emails.contains(email));
  }

  @Test
  public void testGetUnactivatedEmails() throws MessagingException {
    // create some test users
    String email = "user-" + random.nextInt() + "@test.com";
    String password = "pass-" + random.nextInt();
    UserFactory userFactory = TestHelper.getBean(UserFactory.class);
    userFactory.register(session, email, password, null);

    List<String> emails = administrator.getEmails(session, false);
    Assert.assertTrue(emails.size() > 0);
    Assert.assertTrue(emails.contains(email));

    emails = administrator.getEmails(session, true);
    Assert.assertFalse(emails.contains(email));
  }

  @Test
  public void testGetActivatedEmails() throws MessagingException, UserException {
    // create some test users
    String email = "user-" + random.nextInt() + "@test.com";
    String password = "pass-" + random.nextInt();
    UserFactory userFactory = TestHelper.getBean(UserFactory.class);
    User user = userFactory.register(session, email, password, null);
    userFactory.activate(session, email, user.getActivationCode());

    List<String> emails = administrator.getEmails(session, true);
    Assert.assertTrue(emails.size() > 0);
    Assert.assertTrue(emails.contains(email));

    emails = administrator.getEmails(session, false);
    Assert.assertFalse(emails.contains(email));
  }

  @Test
  public void testSendAnouncement() throws IOException, MessagingException {
    List<String> emails = Arrays.asList("jerric@pcbi.upenn.edu");
    StringBuilder errors = new StringBuilder();
    int count =
        administrator.sendAnouncement(session, "anouncement-01.email", emails,
            errors);
    Assert.assertEquals(emails.size(), count);
    Assert.assertEquals(0, errors.length());
  }
}
