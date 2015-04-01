package net.lliira.vpn.service.fix;

import java.io.IOException;

import org.springframework.context.ApplicationContext;

public interface Fix {

  void fix(ApplicationContext context) throws IOException;
}
