/**
 * 
 */
package net.lliira.vpn.model.admin;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import net.lliira.vpn.model.Errors;
import net.lliira.vpn.model.TextFactory;
import net.lliira.vpn.model.dao.DaoSession;
import net.lliira.vpn.model.dao.mapper.AdminMapper;
import net.lliira.vpn.model.email.EmailFactory;
import net.lliira.vpn.model.email.EmailTemplate;

import org.apache.log4j.Logger;

/**
 * @author jerric
 * 
 */
public class DefaultAdminFactory implements AdminFactory {

  private static final Logger logger = Logger
      .getLogger(DefaultAdminFactory.class);

  private TextFactory textFactory;
  private EmailFactory emailFactory;

  /*
   * (non-Javadoc)
   * 
   * @see
   * net.lliira.vpn.model.Processor#setTextFactory(net.lliira.vpn.model.TextFactory
   * )
   */
  @Override
  public void setTextFactory(TextFactory textFactory) {
    this.textFactory = textFactory;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * net.lliira.vpn.model.email.EmailProcessorAware#setEmailProcessor(net.lliira
   * .vpn.model.email.EmailProcessor)
   */
  @Override
  public void setEmailFactory(EmailFactory emailFactory) {
    this.emailFactory = emailFactory;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * net.lliira.vpn.model.admin.Administrator#validateAnouncement(java.lang.
   * String)
   */
  @Override
  public Map<String, String> validateAnouncement(String anouncement) {
    Map<String, String> errors = new HashMap<String, String>();

    if (anouncement == null || anouncement.trim().length() == 0) {
      errors
          .put(FIELD_ANOUNCEMENT, textFactory.get(Errors.ANOUNCEMENT_INVALID));
      return errors;
    }

    if (!anouncement.startsWith("/")) anouncement = "/" + anouncement;
    InputStream input = getClass().getResourceAsStream(anouncement);
    if (input == null) {
      errors
          .put(FIELD_ANOUNCEMENT, textFactory.get(Errors.ANOUNCEMENT_INVALID));
      return errors;
    } else {
      try {
        input.close();
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    }

    return errors;
  }

  @Override
  public List<String> getEmails(DaoSession session) {
    AdminMapper mapper = session.getMapper(AdminMapper.class);
    List<String> emails = mapper.selectAllEmails();
    return emails;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * net.lliira.vpn.model.admin.Administrator#getEmails(net.lliira.vpn.model
   * .dao.DaoSession, boolean)
   */
  @Override
  public List<String> getEmails(DaoSession session, boolean activated) {
    AdminMapper mapper = session.getMapper(AdminMapper.class);
    List<String> emails =
        activated ? mapper.selectActivatedEmails() : mapper
            .selectUnactivatedEmails();
    return emails;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * net.lliira.vpn.model.admin.AdminFactory#getActiveEmails(net.lliira.vpn.
   * model.dao.DaoSession)
   */
  @Override
  public List<String> getActiveEmails(DaoSession session) {
    AdminMapper mapper = session.getMapper(AdminMapper.class);
    List<String> emails = mapper.selectActiveEmails();
    return emails;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * net.lliira.vpn.model.admin.AdminFactory#getNewEmails(net.lliira.vpn.model
   * .dao.DaoSession)
   */
  @Override
  public List<String> getNewEmails(DaoSession session) {
    AdminMapper mapper = session.getMapper(AdminMapper.class);
    List<String> emails = mapper.selectNewEmails();
    return emails;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * net.lliira.vpn.model.admin.AdminFactory#getCustomEmails(net.lliira.vpn.
   * model .dao.DaoSession)
   */
  @Override
  public List<String> getCustomEmails(DaoSession session) {
    AdminMapper mapper = session.getMapper(AdminMapper.class);
    List<String> emails = mapper.selectCustomEmails();
    return emails;
  }

  @Override
  public int sendAnouncement(DaoSession session, String anouncement,
      StringBuilder errors) throws IOException, MessagingException {
    AdminMapper mapper = session.getMapper(AdminMapper.class);
    List<String> emails = mapper.selectActiveEmails();
    return sendAnouncement(session, anouncement, emails, errors);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * net.lliira.vpn.model.admin.Administrator#sendAnouncement(java.lang.String)
   */
  @Override
  public int sendAnouncement(DaoSession session, String anouncement,
      Collection<String> emails, StringBuilder errors) throws IOException {
    // read in anouncement email
    if (!anouncement.startsWith("/")) anouncement = "/" + anouncement;
    EmailTemplate email = emailFactory.loadEmailTemplate(anouncement);

    // send emails
    String from = emailFactory.getNoreplyEmail();
    int count = 0;
    for (String to : emails) {
      try {
        emailFactory
            .sendEmail(to, from, email.getSubject(), email.getContent());
        count++;
      } catch (MessagingException ex) { // skip failed emails.
        String error =
            "Failed to send anouncement to: " + to + ",\t\t" + ex.getMessage();
        logger.info(error);
        errors.append(error + "<br />");
      }
    }
    logger.info("Anouncement '" + anouncement + "' has been sent to " + count
        + " users");
    return count;
  }

}
