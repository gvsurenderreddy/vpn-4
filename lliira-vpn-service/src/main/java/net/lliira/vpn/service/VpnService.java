package net.lliira.vpn.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.lliira.vpn.service.task.ProcessUsageTask;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 * 
 */
public class VpnService implements Runnable {

  static final String SIGNAL_FILE = "vpn-service.running";
  private static final long SLEEP_INTERVAL = 1000;

  private static final Logger logger = Logger.getLogger(VpnService.class);

  public static void main(String[] args) throws IOException,
      VpnServiceException {
    VpnService service = new VpnService();
    service.start();
  }

  private final ApplicationContext context;
  private final List<Worker> workers;

  public VpnService() {
    context = new ClassPathXmlApplicationContext("/applicationContext.xml");
    workers = new ArrayList<Worker>();
  }

  private void registerTasks() {
    workers.add(new Worker(new ProcessUsageTask(context)));
  }

  private boolean isStopping() {
    File file = new File(SIGNAL_FILE);
    return !file.exists();
  }

  public void start() throws IOException, VpnServiceException {
    // create running flag file
    File runFile = new File(SIGNAL_FILE);
    if (runFile.exists())
      throw new VpnServiceException(
          "VPN Service is already running, cannot start another instance.");

    runFile.createNewFile();
    logger.info("Running flag: " + runFile.getAbsolutePath());

    // register tasks;
    logger.info("Registering & starting tasks...");
    registerTasks();

    new Thread(this).start();
  }

  public void run() {

    // start tasks
    for (Worker worker : workers) {
      new Thread(worker).start();
    }

    logger.info("VPN Service started. " + workers.size() + " tasks running.");
    do {
      try {
        Thread.sleep(SLEEP_INTERVAL);
      } catch (InterruptedException ex) {}
    } while (!isStopping());
    logger.info("Running flag removed. stopping tasks...");

    // stop workers gracefully
    for (Worker worker : workers) {
      worker.stop();
    }

    File runFile = new File(SIGNAL_FILE);
    if (runFile.exists()) runFile.delete();
  }
}
