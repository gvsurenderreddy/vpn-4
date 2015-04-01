package net.lliira.vpn.model.dao;

import net.lliira.vpn.model.Factory;

public interface DaoFactory  extends Factory {

  DaoSession openSession();
}
