package net.lliira.vpn.service.fix;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.lliira.vpn.model.VpnModel;
import net.lliira.vpn.model.account.UserTimeRange;
import net.lliira.vpn.model.dao.DaoSession;
import net.lliira.vpn.model.dao.mapper.ServiceMapper;
import net.lliira.vpn.model.user.User;
import net.lliira.vpn.model.user.UserFactory;
import net.lliira.vpn.service.VpnServiceException;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

public class Fix20120915 extends AbstractFix {

  private static final DateFormat TIME_FORMAT = new SimpleDateFormat(
      "yyyy-MM-dd HH:mm:ss");

  private static final long SLEEP_INTERVAL = 1000;

  private static final Logger logger = Logger.getLogger(Fix20120915.class);

  @Override
  public void execute(ApplicationContext context) {
    // get users
    VpnModel model = context.getBean(VpnModel.class);
    UserFactory userFactory = context.getBean(UserFactory.class);
    DaoSession session = model.openSession();
    try {

      computeDailyUsage(session);
      session.commit();
      computeMonthlyUsage(userFactory, session);

      session.commit();
    } catch (Exception ex) {
      session.rollback();
      throw new RuntimeException(ex);
    } finally {
      session.close();
    }
  }

  public void computeDailyUsage(DaoSession session) throws ParseException,
      VpnServiceException {
    ServiceMapper mapper = session.getMapper(ServiceMapper.class);

    // get the start & end for backup table
    Calendar startTime = Calendar.getInstance();
    startTime.setTime(TIME_FORMAT.parse("2012-08-02 00:00:00"));
    Calendar endTime = Calendar.getInstance();
    endTime.setTime(TIME_FORMAT.parse("2012-09-12 00:00:00"));

    while (startTime.before(endTime)) {
      Date from = startTime.getTime();
      startTime.add(Calendar.DAY_OF_MONTH, 1);
      Date to = startTime.getTime();
      logger.info("Daily summary " + from + " to " + to);

      // summarize the usage in the given range
      UserTimeRange range = new UserTimeRange();
      range.setFrom(from);
      range.setTo(to);
      summarizeDaily(session, mapper, range);

      session.commit();

      // sleep for a while to let database handle other requests
      try {
        Thread.sleep(SLEEP_INTERVAL);
      } catch (InterruptedException ex) {}

      if (isStopping())
        throw new VpnServiceException("Task terminated prematurally.");
    }
    logger.info("Fix stopped.");
  }

  private void summarizeDaily(DaoSession session, ServiceMapper mapper,
      UserTimeRange range) throws VpnServiceException {
    // get users in the range
    // List<Integer> users = mapper.selectUsers(range);
    // logger.debug(users.size() + " users to be processed");
    // for (int userId : users) {
    // range.setUserId(userId);
    // Usage usage = mapper.selectUsageByRange(range);
    // if (usage == null) continue;
    //
    // // delete the usages
    // mapper.deleteUsagesByRange(range);
    //
    // // insert the summarized usage
    // if (usage.getDownload() != 0 || usage.getUpload() != 0
    // || usage.getSessionTime() != 0) {
    // usage.setStartTime(range.getFrom());
    // mapper.insertDaylyUsage(usage);
    // }
    // session.commit();
    // if (isStopping())
    // throw new VpnServiceException("Task terminated prematurally.");
    // }
    logger.info("Daily summary finished");
  }

  public void computeMonthlyUsage(UserFactory userFactory, DaoSession session)
      throws ParseException {
//    ServiceMapper mapper = session.getMapper(ServiceMapper.class);
    // get activated users
    List<User> users = userFactory.getUsers(session, true);

    UserTimeRange range = new UserTimeRange();
    range.setFrom(TIME_FORMAT.parse("2012-08-01 00:00:00"));
    range.setTo(TIME_FORMAT.parse("2012-09-01 00:00:00"));
    for (User user : users) {
      // skip users that activated after the end date
      if (user.getActivatedTime().after(range.getTo())) continue;

      range.setUserId(user.getId());
      // Usage usage = mapper.selectUsageByRange(range);
      // if (usage == null) continue;
      //
      // if (usage.getSessionTime() != 0 || usage.getDownload() != 0
      // && usage.getUpload() != 0) { // insert a summary row
      // mapper.insertMonthlyUsage(usage);
      // }
      //
      // // remove individual rows
      // mapper.deleteUsagesByRange(range);

      session.commit();

      try {
        Thread.sleep(1000);
      } catch (InterruptedException ex) {}

      if (isStopping()) return;
    }
  }
}
