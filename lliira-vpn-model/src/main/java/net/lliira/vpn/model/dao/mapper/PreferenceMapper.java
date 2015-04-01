package net.lliira.vpn.model.dao.mapper;

import java.util.List;

import net.lliira.vpn.model.pref.Preference;

public interface PreferenceMapper {

  List<Preference> selectPreferences();
  
  Preference selectPreference(String name);
  
  void insertPreference(Preference preference);
  
  void updatePreference(Preference preference);
  
  void deletePreference(String name);
}
