package net.lliira.vpn.model.account;

import net.lliira.vpn.model.Factory;
import net.lliira.vpn.model.dao.DaoSession;
import net.lliira.vpn.model.pref.PreferenceFactory;
import net.lliira.vpn.model.user.User;

public interface AccountFactory  extends Factory {

  void setPreferenceFactory(PreferenceFactory preferenceFactory);
  
  Account getAccount(DaoSession session, User user);
  
  Benefit getBenefit(DaoSession session);
}
