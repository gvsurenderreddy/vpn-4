package net.lliira.vpn.model.email;

import java.util.Map;
import java.util.regex.Pattern;

public class EmailTemplate {

  private String subject;
  private String content;

  /**
   * @return the subject
   */
  public String getSubject() {
    return subject;
  }

  /**
   * @param subject
   *          the subject to set
   */
  public void setSubject(String subject) {
    this.subject = subject;
  }

  /**
   * @return the content
   */
  public String getContent() {
    return content;
  }

  /**
   * @param content
   *          the content to set
   */
  public void setContent(String content) {
    this.content = content;
  }

  public String prepareSubject(Map<String, String> macros) {
    String buffer = subject;
    for (String macro : macros.keySet()) {
      String pattern = Pattern.quote("${" + macro + "}");
      buffer = buffer.replaceAll(buffer, pattern);
    }
    return buffer;
  }

  public String prepareContent(Map<String, String> macros) {
    String buffer = content;
    for (String macro : macros.keySet()) {
      String pattern = Pattern.quote("${" + macro + "}");
      buffer = buffer.replaceAll(pattern, macros.get(macro));
    }
    return buffer;
  }
}
