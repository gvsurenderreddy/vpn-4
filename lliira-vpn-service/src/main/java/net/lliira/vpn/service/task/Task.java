package net.lliira.vpn.service.task;

import org.springframework.context.ApplicationContext;

/**
 * @author jerric
 *
 */
public interface Task {

  /**
   * @return interval in milliseconds.
   */
  long getInterval();
  
  void setContext(ApplicationContext context);
  
  void run();
  
  void stop();
}
