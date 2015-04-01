/**
 * 
 */
package net.lliira.vpn.model;

import org.apache.log4j.Logger;

import net.lliira.vpn.model.dao.DaoSession;
import net.lliira.vpn.model.dao.mapper.AdminMapper;

/**
 * @author jerric
 * 
 */
public class CacheTask implements Task {

  private static final long SLEEP_TIME = 10 * 60 * 1000;
  private static final Logger logger = Logger.getLogger(CacheTask.class);

  private final VpnModel model;
  private boolean running;
  private boolean stopped;

  /**
   * 
   */
  public CacheTask(VpnModel model) {
    this.model = model;
  }

  @Override
  public void stop() {
    running = false;
  }

  @Override
  public boolean isStopped() {
    return stopped;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Runnable#run()
   */
  @Override
  public void run() {
    running = stopped = true;
    while (running) {
      long start = System.currentTimeMillis();
      DaoSession session = model.openSession();
      AdminMapper mapper = session.getMapper(AdminMapper.class);

      logger.debug("making radcheck...");
      mapper.makeCheck();
      logger.debug("making radreply...");
      mapper.makeReply();

      session.commit();
      session.close();

      long spent = System.currentTimeMillis() - start;
      logger.debug("check & reply made in " + (spent / 1000D) + " seconds.");
      long sleep = Math.max(1000, SLEEP_TIME - spent);
      try {
        Thread.sleep(sleep);
      } catch (InterruptedException ex) {}
    }
    stopped = true;
  }

}
