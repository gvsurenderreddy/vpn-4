package net.lliira.vpn.service.task;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import net.lliira.vpn.model.VpnModel;
import net.lliira.vpn.model.account.TimeRange;
import net.lliira.vpn.model.dao.DaoSession;
import net.lliira.vpn.model.dao.mapper.ServiceMapper;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

public class ProcessUsageTask implements Task {

  private static final long INTERVAL = 12 * 3600 * 1000;

  private static final long SLEEP_INTERVAL = 1000;

  private static final String PROP_BACKUP_DIR = "vpndb.backup";
  private static final String PROPVAL_BACKUP_DIR = "backup";

  private static final String PREFIX_BACKUP_FILE = "iqlink-usage-detail-";
  private static final String PREFIX_DETAIL_TABLE = "usage_details_";

  private static final Logger logger = Logger.getLogger(ProcessUsageTask.class);

  private VpnModel model;
  private boolean running;

  public ProcessUsageTask(ApplicationContext context) {
    setContext(context);
  }

  @Override
  public long getInterval() {
    return INTERVAL;
  }

  @Override
  public void setContext(ApplicationContext context) {
    model = context.getBean(VpnModel.class);
  }

  /*
   * (non-Javadoc)
   * 
   * @see net.lliira.vpn.service.Task#stop()
   */
  @Override
  public void stop() {
    running = false;
  }

  @Override
  public void run() {
    running = true;
    DaoSession session = model.openSession();
    ServiceMapper mapper = session.getMapper(ServiceMapper.class);

    // only process the records at least one day old.
    Calendar limit = Calendar.getInstance();
    limit.add(Calendar.DAY_OF_MONTH, -1);

    try {
      Calendar startTime = getStartTime(mapper);
      Calendar endTime = getEndTime(startTime);
      while (endTime.before(limit)) {
        TimeRange range = new TimeRange();
        range.setFrom(startTime.getTime());
        range.setTo(endTime.getTime());

        logger.info("Processing " + range.getFromMonth());
        processDetails(mapper, range);
        session.commit();
        backupDetails(range);

        startTime.add(Calendar.MONTH, 1);
        endTime.add(Calendar.MONTH, 1);

        // sleep for a while to let database handle other requests
        try {
          Thread.sleep(SLEEP_INTERVAL);
        } catch (InterruptedException ex) {}

        if (!running) return;
      }
    } catch (Exception ex) {
      session.rollback();
      throw new RuntimeException(ex);
    } finally {
      running = false;
      session.close();
    }
    logger.info("Daily summary task finished.");
  }

  private Calendar getStartTime(ServiceMapper mapper) throws ParseException {
    Date minTime = mapper.selectMinTime();
    if (minTime == null) minTime = new Date();
    Calendar date = Calendar.getInstance();
    date.setTime(minTime);
    date.set(Calendar.DAY_OF_MONTH, 1);
    date.set(Calendar.HOUR_OF_DAY, 0);
    date.set(Calendar.MINUTE, 0);
    date.set(Calendar.SECOND, 0);
    return date;
  }

  private Calendar getEndTime(Calendar startTime) throws ParseException {
    Calendar endTime = (Calendar) startTime.clone();
    endTime.add(Calendar.MONTH, 1);
    return endTime;
  }

  private void processDetails(ServiceMapper mapper, TimeRange range) {
    logger.debug("creating detail table...");
    mapper.createDetails(range);
    logger.debug("inserting details...");
    mapper.insertDetails(range);
    logger.debug("deleting details...");
    mapper.deleteDetails(range);
    mapper.optimizeDetails();
    logger.debug("computing & inserting summaries...");
    mapper.insertUsage(range);
  }

  private void backupDetails(TimeRange range) throws IOException,
      InterruptedException {
    Properties props = model.getProperties();
    String login = props.getProperty(VpnModel.PROP_DB_LOGIN);
    String password = props.getProperty(VpnModel.PROP_DB_PASSWORD);
    String backupPath = props.getProperty(PROP_BACKUP_DIR, PROPVAL_BACKUP_DIR);
    File backupDir = new File(backupPath);
    if (!backupDir.exists()) backupDir.mkdirs();

    StringBuilder command = new StringBuilder("/usr/bin/mysqldump ");
    command.append(" --user=" + login).append(" --password=" + password);
    command.append(" radius ");
    command.append(PREFIX_DETAIL_TABLE + range.getFromMonth());
    command.append(" > ").append(backupDir.getAbsolutePath());
    command.append("/" + PREFIX_BACKUP_FILE + range.getFromMonth() + ".sql");

    logger.debug("backup command: " + command);
    Process process = Runtime.getRuntime().exec(command.toString());
    int exitValue = process.waitFor();
    logger.debug("backup finished: " + exitValue);
  }
}
