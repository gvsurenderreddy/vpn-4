package net.lliira.vpn.model.pref;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.lliira.vpn.model.dao.DaoSession;
import net.lliira.vpn.model.dao.mapper.PreferenceMapper;

public class DefaultPreferenceFactory implements PreferenceFactory {

  @Override
  public void setPreference(DaoSession session, String name, String value) {
    PreferenceMapper mapper = session.getMapper(PreferenceMapper.class);

    // get all preferences
    Map<String, String> preferences = getPreferences(session);

    Preference preference = new Preference(name);
    preference.setValue(value);
    if (preferences.containsKey(name)) {
      mapper.updatePreference(preference);
    } else {
      mapper.insertPreference(preference);
    }
  }

  @Override
  public String getPreference(DaoSession session, String name) {
    PreferenceMapper mapper = session.getMapper(PreferenceMapper.class);
    Preference preference = mapper.selectPreference(name);
    return (preference == null) ? null : preference.getValue();
  }

  @Override
  public void removePreference(DaoSession session, String name) {
    PreferenceMapper mapper = session.getMapper(PreferenceMapper.class);
    mapper.deletePreference(name);
  }

  @Override
  public Map<String, String> getPreferences(DaoSession session) {
    PreferenceMapper mapper = session.getMapper(PreferenceMapper.class);

    // get all preferences
    List<Preference> list = mapper.selectPreferences();
    Map<String, String> preferences = new HashMap<String, String>();
    for (Preference preference : list) {
      preferences.put(preference.getName(), preference.getValue());
    }
    return preferences;
  }

}
