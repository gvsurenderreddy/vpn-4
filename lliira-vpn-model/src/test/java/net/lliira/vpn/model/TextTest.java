package net.lliira.vpn.model;

import java.io.IOException;
import java.util.InvalidPropertiesFormatException;

import junit.framework.Assert;

import org.junit.Test;

public class TextTest {

  @Test
  public void testReadText() throws InvalidPropertiesFormatException,
      IOException {
    TextFactory text = new TextFactory();
    String key = "test";
    String value = "This is a test message.";
    Assert.assertEquals(value, text.get(key));
  }
}
