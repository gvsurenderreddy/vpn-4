/**
 * 
 */
package net.lliira.vpn.service.fix;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

/**
 * @author jerric
 * 
 */
public abstract class AbstractFix implements Fix {

  private static final String SIGNAL_FILE = "vpn-fix.running";

  private static final Logger logger = Logger.getLogger(AbstractFix.class);

  protected abstract void execute(ApplicationContext context);

  /*
   * (non-Javadoc)
   * 
   * @see net.lliira.vpn.service.fix.Fix#fix(org.springframework.context.
   * ApplicationContext)
   */
  @Override
  public final void fix(ApplicationContext context) throws IOException {
    File file = new File(SIGNAL_FILE);
    if (!file.exists()) file.createNewFile();
    logger.info("Fix signal file: " + file.getAbsolutePath());

    execute(context);

    if (file.exists()) file.delete();
  }

  protected boolean isStopping() {
    File file = new File(SIGNAL_FILE);
    return !file.exists();
  }

}
