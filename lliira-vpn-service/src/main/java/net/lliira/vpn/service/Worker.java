package net.lliira.vpn.service;

import net.lliira.vpn.service.task.Task;

public class Worker implements Runnable {

  private static final long MIN_SLEEP = 100;
  private static final long MAX_INTERVAL = 10 * 1000;

  private final Task task;

  private boolean running;

  public Worker(Task task) {
    this.task = task;
  }

  public void stop() {
    running = false;
    task.stop();
  }

  @Override
  public void run() {
    running = true;
    while (running) {
      long start = System.currentTimeMillis();
      task.run();
      long spent = System.currentTimeMillis() - start;

      // sleep for the given interval, but check periodically for early exit.
      long toSleep = Math.max(MIN_SLEEP, task.getInterval() - spent);
      while (toSleep > MIN_SLEEP) {
        if (!running) break;

        long interval = Math.min(toSleep, MAX_INTERVAL);
        try {
          Thread.sleep(interval);
        } catch (InterruptedException ex) {}
        toSleep -= interval;
      }
    }
    task.stop();
    running = false;
  }

}
