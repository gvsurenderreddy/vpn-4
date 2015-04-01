/**
 * 
 */
package net.lliira.vpn.model.account;

import java.util.Calendar;
import java.util.Map;

import net.lliira.vpn.model.dao.DaoSession;
import net.lliira.vpn.model.dao.mapper.AccountMapper;
import net.lliira.vpn.model.pref.PreferenceFactory;
import net.lliira.vpn.model.user.User;

/**
 * @author jerric
 * 
 */
public class DefaultAccountFactory implements AccountFactory {

  private static final String PREF_FREE_BANDWIDTH = "free-bandwidth";
  private static final String PREF_REFERRAL_LEVEL = "referral-level";
  private static final String PREF_REFERRAL_BANDWIDTH = "referral-bonus";
  private static final String PREF_REFERRAL_MAX = "referral-max";

  private static final long UNIT = 1000000;

  private PreferenceFactory preferenceFactory;

  /**
   * @param preferenceFactory
   *          the preferenceFactory to set
   */
  public void setPreferenceFactory(PreferenceFactory preferenceFactory) {
    this.preferenceFactory = preferenceFactory;
  }

  @Override
  public Account getAccount(DaoSession session, User user) {
    AccountMapper mapper = session.getMapper(AccountMapper.class);
    Account account = new Account(user);

    // load bandwidth
    account
        .setEffectiveBandwidth(mapper.selectEffectiveBandwidth(user.getId()));

    // load referrals
    account.setTotalReferrals(mapper.selectTotalReferrals(user.getId()));
    account
        .setEffectiveReferrals(mapper.selectEffectiveReferrals(user.getId()));

    // load usages
    Usage currentUsage = mapper.selectCurrentUsage(user.getId());
    if (currentUsage == null) {
      currentUsage = new Usage(user.getId());
      Calendar date = Calendar.getInstance();
      currentUsage.setYear(date.get(Calendar.YEAR));
      currentUsage.setMonth(date.get(Calendar.MONTH) + 1);
    }
    account.setCurrentUsage(currentUsage);
    account.setPreviousUsages(mapper.selectPreviousUsages(user.getId()));

    return account;
  }

  @Override
  public Benefit getBenefit(DaoSession session) {
    Map<String, String> preferences = preferenceFactory.getPreferences(session);
    Benefit benefit = new Benefit();
    if (preferences.containsKey(PREF_FREE_BANDWIDTH)) {
      String value = preferences.get(PREF_FREE_BANDWIDTH);
      benefit.setFreeBandwidth(Long.valueOf(value) * UNIT);
    }
    if (preferences.containsKey(PREF_REFERRAL_BANDWIDTH)) {
      String value = preferences.get(PREF_REFERRAL_BANDWIDTH);
      benefit.setReferralBandwidth(Long.valueOf(value) * UNIT);
    }
    if (preferences.containsKey(PREF_REFERRAL_LEVEL)) {
      String value = preferences.get(PREF_REFERRAL_LEVEL);
      benefit.setReferralLevel(Integer.valueOf(value));
    }
    if (preferences.containsKey(PREF_REFERRAL_MAX)) {
      String value = preferences.get(PREF_REFERRAL_MAX);
      benefit.setReferralMax(Integer.valueOf(value));
    }
    return benefit;
  }

}
