package net.lliira.vpn.model.dao;

import org.apache.ibatis.session.SqlSession;

public class MyBatisSession implements DaoSession {

  private final SqlSession session;

  public MyBatisSession(SqlSession session) {
    this.session = session;
  }

  /**
   * 
   * @see org.apache.ibatis.session.SqlSession#close()
   */
  @Override
  public void close() {
    session.close();
  }

  /**
   * 
   * @see org.apache.ibatis.session.SqlSession#commit()
   */
  @Override
  public void commit() {
    session.commit();
  }

  /**
   * @param type
   * @return
   * @see org.apache.ibatis.session.SqlSession#getMapper(java.lang.Class)
   */
  @Override
  public <T> T getMapper(Class<T> type) {
    return session.getMapper(type);
  }

  /**
   * 
   * @see org.apache.ibatis.session.SqlSession#rollback()
   */
  @Override
  public void rollback() {
    session.rollback();
  }
}
