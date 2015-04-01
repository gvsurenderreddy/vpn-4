package net.lliira.vpn.service.fix;

import java.util.List;

import net.lliira.vpn.model.VpnModel;
import net.lliira.vpn.model.dao.DaoSession;
import net.lliira.vpn.model.dao.mapper.UserMapper;
import net.lliira.vpn.model.user.User;
import net.lliira.vpn.model.user.UserFactory;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

public class Fix20120905 implements Fix {

  private static final Logger logger = Logger.getLogger(Fix20120905.class);

  public void fix(ApplicationContext context) {
    VpnModel vpnModel = context.getBean(VpnModel.class);
    DaoSession session = vpnModel.openSession();

    createSignatures(context, session);

    session.commit();
    session.close();
  }

  private void createSignatures(ApplicationContext context, DaoSession session) {
    logger.debug("Creating user signatures & activation code...");
    UserFactory userFactory = context.getBean(UserFactory.class);
    List<User> users = userFactory.getUsers(session);

    UserMapper mapper = session.getMapper(UserMapper.class);
    int count = 0;
    for (User user : users) {
      boolean updated = false;

      // update activation code
      String activationCode = user.getActivationCode();
      if (activationCode == null || activationCode.length() == 0) {
        user.setActivationCode(userFactory.createActivationCode());
        updated = true;
      }

      // update signature
      String signature = user.getSignature();
      if (signature == null || signature.length() == 0
          || signature.equals(user.getEmail())) {
        user.setSignature(userFactory.createSignature(user.getEmail()));
        updated = true;
      }

      if (updated) {
        mapper.updateUser(user);
        count++;
      }
    }
    logger.debug("Total " + count + " users updated.");
  }
}
