package net.lliira.vpn.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class TextFactory implements Factory {

  private static final String RESOURCE_TEXT = "/text.xml";

  private static final Logger logger = Logger.getLogger(TextFactory.class);

  private final Properties texts;

  public TextFactory() {
    // load text
    texts = new Properties();
    InputStream input = getClass().getResourceAsStream(RESOURCE_TEXT);
    try {
      texts.loadFromXML(input);
      input.close();
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

  public String get(String key) {
    String content = texts.getProperty(key, key);
    if (content == null) {
      logger.warn("text for key '" + key + "' cannot be found.");
      return "";
    } else return content.trim();
  }
}
