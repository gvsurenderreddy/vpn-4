package net.lliira.vpn.model.email;

import java.io.IOException;
import java.util.Properties;

import javax.mail.MessagingException;

import net.lliira.vpn.model.Factory;

public interface EmailFactory extends Factory {

  void setProperties(Properties properties);
  
  String getSupportEmail();
  
  String getNoreplyEmail();

  EmailTemplate loadEmailTemplate(String resource) throws IOException;

  void sendEmail(String to, String from, String subject, String content)
      throws MessagingException;

  void sendRegistrationEmail(String to, String activationCode)
      throws MessagingException;

  void sendForgetPasswordEmail(String to, String password)
      throws MessagingException;
}
