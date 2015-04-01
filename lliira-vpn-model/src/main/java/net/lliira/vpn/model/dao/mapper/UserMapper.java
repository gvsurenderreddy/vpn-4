package net.lliira.vpn.model.dao.mapper;

import java.util.List;

import net.lliira.vpn.model.user.Server;
import net.lliira.vpn.model.user.User;
import net.lliira.vpn.model.user.UserLog;

public interface UserMapper {

  List<User> selectAllUsers();
  
  List<User> selectUsersByActivation(boolean activated);
  
  User selectUserById(int id);

  User selectUserBySignature(String signature);

  User selectUserByEmail(String email);
  
  List<String> selectUserRoles(User user);
  
  List<String> selectBlackLists();

  void insertUser(User user);

  void updateUser(User user);

  void deleteUser(int id);

  void insertUserLog(UserLog log);

  List<Server> selectServers();
  
  boolean selectIsPurchased(int userId);
}
