package net.lliira.vpn.model.dao;

import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisFactory implements DaoFactory {
  
  private static final String CONFIG_FILE = "lliira-vpn-mybatis.xml";

  private final SqlSessionFactory sessionFactory;

  public MyBatisFactory(Properties properties) throws IOException {
    Reader reader = Resources.getResourceAsReader(CONFIG_FILE);
    SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
    sessionFactory = builder.build(reader, properties);
  }
  
  @Override
  public DaoSession openSession() {
    return new MyBatisSession(sessionFactory.openSession());
  }
}
