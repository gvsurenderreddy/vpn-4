package net.lliira.vpn.model.dao.mapper;

import java.util.Date;

import net.lliira.vpn.model.account.TimeRange;


public interface ServiceMapper {

  Date selectMinTime();
  
  void createDetails(TimeRange range);
  
  void insertDetails(TimeRange range);
  
  void deleteDetails(TimeRange range);
  
  void optimizeDetails();
  
  void insertUsage(TimeRange range);
}
