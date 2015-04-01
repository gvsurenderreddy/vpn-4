package net.lliira.vpn.model;

import javax.mail.MessagingException;

import net.lliira.vpn.model.account.Account;
import net.lliira.vpn.model.account.AccountFactory;
import net.lliira.vpn.model.account.Benefit;
import net.lliira.vpn.model.user.User;
import net.lliira.vpn.model.user.UserException;
import net.lliira.vpn.model.user.UserFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AccountTest extends AbstractTest {

  private static final int REFERRALS = 10;

  private final AccountFactory accountFactory;

  private User user;

  public AccountTest() {
    accountFactory = TestHelper.getBean(AccountFactory.class);
  }

  @Before
  public void createUser() throws MessagingException, UserException {

    // create a login
    String email = "user" + random.nextInt() + "@test.com";
    String password = "pass" + random.nextInt();
    UserFactory userFactory = TestHelper.getBean(UserFactory.class);
    user = userFactory.register(session, email, password, null);
    user = userFactory.activate(session, email, user.getActivationCode());
  }

  @Test
  public void testGetAccount() throws MessagingException, UserException {
    Account account = accountFactory.getAccount(session, user);

    // always have some bandwidth
    Assert.assertTrue(account.getEffectiveBandwidth() > 0);
    Assert.assertEquals(0, account.getTotalReferrals());
    Assert.assertEquals(0, account.getEffectiveReferrals());

    // now register new users with referral
    UserFactory userFactory = TestHelper.getBean(UserFactory.class);
    for (int i = 0; i < REFERRALS; i++) {
      String email = "user" + random.nextInt() + "@test.com";
      String password = "pass" + random.nextInt();
      User u =
          userFactory.register(session, email, password, user.getSignature());
      userFactory.activate(session, email, u.getActivationCode());
    }

    Account account2 = accountFactory.getAccount(session, user);

    // always have some bandwidth
    Assert.assertTrue(account2.getEffectiveBandwidth() > account
        .getEffectiveBandwidth());
    Assert.assertEquals(REFERRALS, account2.getTotalReferrals());
    Assert.assertEquals(REFERRALS, account2.getEffectiveReferrals());
  }

  @Test
  public void testGetBenefit() {
    Benefit benefit = accountFactory.getBenefit(session);
    Assert.assertTrue(benefit.getFreeBandwidth() > 0);
    Assert.assertTrue(benefit.getReferralBandwidth() > 0);
    Assert.assertTrue(benefit.getReferralLevel() > 0);
    Assert.assertTrue(benefit.getReferralMax() > 0);
  }
}
