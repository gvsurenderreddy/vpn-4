package net.lliira.vpn.model;

public interface Task extends Runnable {

  void stop();
  
  boolean isStopped();
}
