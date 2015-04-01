package net.lliira.vpn.model.radius;

import net.lliira.vpn.model.Factory;
import net.lliira.vpn.model.user.UserFactory;
import net.lliira.vpn.model.user.UserListener;

public interface RadiusFactory extends Factory, UserListener {
  
  void setUserFactory(UserFactory userFactory);

}
