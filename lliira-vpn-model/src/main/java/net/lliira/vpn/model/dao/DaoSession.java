package net.lliira.vpn.model.dao;

public interface DaoSession {

  void commit();

  void rollback();

  void close();

  <T> T getMapper(Class<T> type);

}
