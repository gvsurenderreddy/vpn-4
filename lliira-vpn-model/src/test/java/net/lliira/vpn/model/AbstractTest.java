package net.lliira.vpn.model;

import java.util.Random;

import net.lliira.vpn.model.dao.DaoSession;

import org.junit.After;
import org.junit.Before;

public abstract class AbstractTest {

  protected final VpnModel model;
  protected final Random random;

  protected DaoSession session;

  public AbstractTest() {
    model = TestHelper.getBean(VpnModel.class);
    random = new Random();
  }

  @Before
  public void openSession() {
    session = model.openSession();
  }

  @After
  public void closeSession() {
    session.rollback();
    session.close();
  }
}
