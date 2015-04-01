package net.lliira.vpn.model.pref;

import java.util.Map;

import net.lliira.vpn.model.dao.DaoSession;

public interface PreferenceFactory {
  
  String getPreference(DaoSession session, String name);
  
  Map<String, String> getPreferences(DaoSession session);

  void setPreference(DaoSession session, String name, String value);
  
  void removePreference(DaoSession session, String name);
}
