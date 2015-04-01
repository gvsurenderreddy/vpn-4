package net.lliira.vpn.service;

import java.io.IOException;

import net.lliira.vpn.service.fix.Fix;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Fixer {

  private static final Logger logger = Logger.getLogger(Fixer.class);

  /**
   * @param args
   * @throws ClassNotFoundException
   * @throws IllegalAccessException
   * @throws InstantiationException
   * @throws IOException 
   */
  public static void main(String[] args) throws ClassNotFoundException,
      InstantiationException, IllegalAccessException, IOException {
    if (args.length == 0) {
      System.err.println("fix <fix1> <fix2>...");
      System.exit(-1);
    }

    ApplicationContext context =
        new ClassPathXmlApplicationContext("/applicationContext.xml");

    String packageName = Fixer.class.getPackage().getName() + ".fix.";
    for (String name : args) {
      logger.info("Executing fix: " + name);
      String className = packageName + name;
      Class<? extends Fix> fixClass =
          Class.forName(className).asSubclass(Fix.class);
      Fix fix = fixClass.newInstance();
      fix.fix(context);
    }
  }
}
