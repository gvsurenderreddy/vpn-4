/**
 * 
 */
package net.lliira.vpn.model.admin;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import net.lliira.vpn.model.Factory;
import net.lliira.vpn.model.TextFactory;
import net.lliira.vpn.model.dao.DaoSession;
import net.lliira.vpn.model.email.EmailFactory;

/**
 * @author jerric
 * 
 */
public interface AdminFactory extends Factory {
  
  static final String ROLE_ADMIN = "admin";

  static final String FIELD_ANOUNCEMENT = "anouncement";

  void setEmailFactory(EmailFactory emailFactory);

  void setTextFactory(TextFactory textFactory);

  Map<String, String> validateAnouncement(String anouncement);

  List<String> getEmails(DaoSession session);

  List<String> getEmails(DaoSession session, boolean activated);
  
  List<String> getNewEmails(DaoSession session);
  
  List<String> getActiveEmails(DaoSession session);

  List<String> getCustomEmails(DaoSession session);

  int sendAnouncement(DaoSession session, String anouncement, StringBuilder errors)
      throws IOException, MessagingException;

  int sendAnouncement(DaoSession session, String anouncement,
      Collection<String> emails, StringBuilder errors) throws IOException, MessagingException;
  
}
