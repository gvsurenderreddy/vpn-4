package net.lliira.vpn.model.pref;

import java.io.Serializable;

public class Preference implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -2268464489912196695L;
  
  private final String name;
  private String value;

  public Preference(String name) {
    this.name = name;
  }

  /**
   * @return the value
   */
  public String getValue() {
    return value;
  }

  /**
   * @param value
   *          the value to set
   */
  public void setValue(String value) {
    this.value = value;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

}
