package net.lliira.vpn.model;

import java.util.Random;

public class KeyGenerator {

  /**
   * @param args
   */
  public static void main(String[] args) {
    Random random = new Random();
    StringBuilder buffer = new StringBuilder();
    int length = random.nextInt(10) + 6;
    for (int i = 0; i<length; i++) {
      int value = random.nextInt(126 - 33) + 33;
      buffer.append((char)value);
    }
    
    System.out.println(buffer.toString());
  }

}
