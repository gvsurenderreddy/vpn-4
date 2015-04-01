package net.lliira.vpn.model.dao.mapper;

import java.util.List;

import net.lliira.vpn.model.account.TimeRange;
import net.lliira.vpn.model.account.Usage;


public interface AccountMapper {
  
  long selectEffectiveBandwidth(int userId);
  
  int selectTotalReferrals(int userId);
  
  int selectEffectiveReferrals(int userId);
  
  List<Integer> selectUsers(TimeRange range);

  List<Usage> selectPreviousUsages(int userId);
  
  Usage selectCurrentUsage(int userId);
}
