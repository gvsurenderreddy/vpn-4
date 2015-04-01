package net.lliira.vpn.model.dao.mapper;

import java.util.List;

public interface AdminMapper {

  List<String> selectAllEmails();

  List<String> selectActivatedEmails();

  List<String> selectUnactivatedEmails();
  
  List<String> selectActiveEmails();
  
  List<String> selectNewEmails();
  
  List<String> selectCustomEmails();

  void makeCheck();
  
  void makeReply();
}
