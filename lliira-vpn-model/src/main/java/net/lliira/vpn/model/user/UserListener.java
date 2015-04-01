package net.lliira.vpn.model.user;

import net.lliira.vpn.model.dao.DaoSession;

public interface UserListener {

  void onRegister(DaoSession session, User user);

  void onActivate(DaoSession session, User user);

  void onLogin(DaoSession session, User user);

  void onLogout(DaoSession session, User user);

  void onChangeEmail(DaoSession session, User user, String oldEmail);

  void onChangePassword(DaoSession session, User user, String oldPassword);
}
