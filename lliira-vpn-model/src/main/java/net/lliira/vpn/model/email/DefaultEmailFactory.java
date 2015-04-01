/**
 * 
 */
package net.lliira.vpn.model.email;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import net.lliira.vpn.model.VpnModel;

import org.apache.log4j.Logger;

/**
 * @author jerric
 * 
 */
public class DefaultEmailFactory implements EmailFactory {

  private static final Logger logger = Logger
      .getLogger(DefaultEmailFactory.class);

  private static final String PROP_SMTP = "email.smtp";
  private static final String PROP_SUPPORT_EMAIL = "email.support";
  private static final String PROP_NOREPLY_EMAIL = "email.noreply";

  private static final String RESOURCE_REGISTER_EMAIL = "/register.email";
  private static final String RESOURCE_FORGET_PASS_EMAIL = "/forget-pass.email";

  private static final String MACRO_EMAIL = "email";
  private static final String MACRO_PASSWORD = "password";
  private static final String MACRO_ACTIVATION_CODE = "activation-code";

  private String smtp;
  private String supportEmail;
  private String noreplyEmail;

  private final EmailTemplate registerEmail;
  private final EmailTemplate forgetPassEmail;

  public DefaultEmailFactory() throws IOException {
    registerEmail = loadEmailTemplate(RESOURCE_REGISTER_EMAIL);
    forgetPassEmail = loadEmailTemplate(RESOURCE_FORGET_PASS_EMAIL);
  }

  public void setProperties(Properties properties) {
    smtp = properties.getProperty(PROP_SMTP, "127.0.0.1");
    supportEmail = properties.getProperty(PROP_SUPPORT_EMAIL);
    noreplyEmail = properties.getProperty(PROP_NOREPLY_EMAIL);
  }

  /**
   * @return the supportEmail
   */
  public String getSupportEmail() {
    return supportEmail;
  }

  /**
   * @return the noreplyEmail
   */
  public String getNoreplyEmail() {
    return noreplyEmail;
  }

  @Override
  public void sendEmail(String to, String from, String subject, String content)
      throws MessagingException {
    Properties props = System.getProperties();
    props.put("mail.smtp.host", smtp);
    Session session = Session.getDefaultInstance(props);

    logger.trace("subject: " + subject);
    // logger.debug("content: " + content);

    Message message = new MimeMessage(session);
    message.setFrom(new InternetAddress(from));
    message.addRecipient(RecipientType.TO, new InternetAddress(to));
    message.setSubject(subject);
    message.setContent(content, "text/plain; charset=UTF-8");

    Transport.send(message);
  }

  public EmailTemplate loadEmailTemplate(String resource) throws IOException {
    EmailTemplate template = new EmailTemplate();

    InputStream stream = VpnModel.class.getResourceAsStream(resource);
    BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));

    // the first line in the file is subject line
    String line = reader.readLine();
    template.setSubject(line);

    // read the rest as the email template content.
    StringBuilder buffer = new StringBuilder();
    while ((line = reader.readLine()) != null) {
      buffer.append(line).append("\n");
    }
    reader.close();
    template.setContent(buffer.toString());
    return template;
  }

  @Override
  public void sendRegistrationEmail(String to, String activationCode)
      throws MessagingException {
    String subject =
        registerEmail.prepareSubject(new HashMap<String, String>());

    Map<String, String> macros = new HashMap<String, String>();
    macros.put(MACRO_EMAIL, to);
    macros.put(MACRO_ACTIVATION_CODE, activationCode);

    String content = registerEmail.prepareContent(macros);

    sendEmail(to, noreplyEmail, subject, content);
  }

  @Override
  public void sendForgetPasswordEmail(String to, String password)
      throws MessagingException {
    String subject =
        registerEmail.prepareSubject(new HashMap<String, String>());

    Map<String, String> macros = new HashMap<String, String>();
    macros.put(MACRO_EMAIL, to);
    macros.put(MACRO_PASSWORD, password);

    String content = forgetPassEmail.prepareContent(macros);

    sendEmail(to, supportEmail, subject, content);
  }

}
