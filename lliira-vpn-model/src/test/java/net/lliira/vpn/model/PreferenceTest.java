package net.lliira.vpn.model;

import junit.framework.Assert;

import org.junit.Test;

import net.lliira.vpn.model.pref.PreferenceFactory;

public class PreferenceTest extends AbstractTest {

  private final PreferenceFactory factory;

  public PreferenceTest() {
    factory = TestHelper.getBean(PreferenceFactory.class);
  }

  @Test
  public void testGetPreference() {
    String name = "pref-" + random.nextInt();
    String value = "value-" + random.nextInt();
    factory.setPreference(session, name, value);

    // now try getting the value
    String newValue = factory.getPreference(session, name);
    Assert.assertEquals(value, newValue);

    // now update the value
    value = "value-" + random.nextInt();
    factory.setPreference(session, name, value);

    // now try getting the value again
    newValue = factory.getPreference(session, name);
    Assert.assertEquals(value, newValue);
  }
}
