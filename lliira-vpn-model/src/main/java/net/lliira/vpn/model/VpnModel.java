package net.lliira.vpn.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import net.lliira.vpn.model.dao.DaoFactory;
import net.lliira.vpn.model.dao.DaoSession;
import net.lliira.vpn.model.dao.MyBatisFactory;

public class VpnModel {

  public static final String PROP_DB_LOGIN = "vpndb.login";
  public static final String PROP_DB_PASSWORD = "vpndb.password";

  private static final String RESOURCE_PROPERTIES = "lliira-vpn.properties";

  private static final String SECRET_KEY = "hR*2K%19$a";

  public static String generateHash(String content) {
    // prepend secret key into content
    byte[] data = (SECRET_KEY + content).getBytes();
    // generate MD5 hash
    MessageDigest md;
    try {
      md = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException ex) {
      throw new RuntimeException(ex);
    }
    byte[] digest = md.digest(data);

    // convert byte[] into text
    StringBuilder buffer = new StringBuilder();
    for (byte b : digest) {
      String ch = Integer.toHexString(b).toUpperCase();
      if (ch.length() < 2) ch = "0" + ch;
      else if (ch.length() > 2) ch = ch.substring(ch.length() - 2);
      buffer.append(ch);
    }
    return buffer.toString();
  }

  private final DaoFactory daoFactory;
  private final Properties properties;
  private final List<Task> tasks;

  public VpnModel() throws IOException {
    this.properties = loadProperties();

    // the implementation details is located here, might consider refactor into
    // Spring Framework.
    this.daoFactory = new MyBatisFactory(properties);
    this.tasks = new ArrayList<Task>();
  }
  
  public void initialize() {
    startTasks();
  }
  
  public void finish() {
    stopTasks();
  }

  private void startTasks() {
    CacheTask cacheTask = new CacheTask(this);
    tasks.add(cacheTask);
    new Thread(cacheTask).start();
  }

  private void stopTasks() {
    for (Task task : tasks) {
      task.stop();
    }

    while (!tasks.isEmpty()) {
      Task task = tasks.get(0);
      if (task.isStopped()) {
        tasks.remove(0);
      } else {
        try {
          Thread.sleep(100);
        } catch (InterruptedException ex) {}
      }
    }
  }

  public DaoSession openSession() {
    return daoFactory.openSession();
  }

  /**
   * @return the properties
   */
  public Properties getProperties() {
    return properties;
  }

  @SuppressWarnings("resource")
  private Properties loadProperties() throws IOException {
    Properties properties = new Properties();

    File file = new File(RESOURCE_PROPERTIES);
    InputStream input = null;
    if (file.exists()) { // try loading config from current path
      input = new FileInputStream(file);
    } else { // try loading config from root classpath
      input = getClass().getResourceAsStream("/" + RESOURCE_PROPERTIES);
    }
    if (input != null) {
      properties.load(input);
      input.close();
    }
    return properties;
  }
}
