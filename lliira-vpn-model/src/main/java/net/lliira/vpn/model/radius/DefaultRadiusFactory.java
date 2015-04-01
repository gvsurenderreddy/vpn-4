package net.lliira.vpn.model.radius;

import net.lliira.vpn.model.dao.DaoSession;
import net.lliira.vpn.model.dao.mapper.RadiusMapper;
import net.lliira.vpn.model.user.User;
import net.lliira.vpn.model.user.UserFactory;

public class DefaultRadiusFactory implements RadiusFactory {

  /*
   * (non-Javadoc)
   * 
   * @see
   * net.lliira.vpn.model.radius.RadiusFactory#addUserListener(net.lliira.vpn
   * .model.user.UserListener)
   */
  @Override
  public void setUserFactory(UserFactory userFactory) {
    userFactory.addUserListener(this);
  }

  @Override
  public void onRegister(DaoSession session, User user) {}

  @Override
  public void onActivate(DaoSession session, User user) {
    // insert email
    // addCheck(session, user.getEmail(), ATTR_PASSWORD, user.getPassword());
  }

  @Override
  public void onLogin(DaoSession session, User user) {}

  @Override
  public void onLogout(DaoSession session, User user) {}

  @Override
  public void onChangeEmail(DaoSession session, User user, String oldEmail) {
    RadiusMapper mapper = session.getMapper(RadiusMapper.class);
    EmailMap emailMap = new EmailMap(oldEmail, user.getEmail());

    // update email in radius tables
    mapper.updateRadAcctEmail(emailMap);
  }

  @Override
  public void onChangePassword(DaoSession session, User user, String oldPassword) {}

}
