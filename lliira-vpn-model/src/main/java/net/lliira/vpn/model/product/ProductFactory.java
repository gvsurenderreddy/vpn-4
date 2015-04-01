package net.lliira.vpn.model.product;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.lliira.vpn.model.Factory;
import net.lliira.vpn.model.TextFactory;
import net.lliira.vpn.model.dao.DaoSession;
import net.lliira.vpn.model.user.Server;

public interface ProductFactory  extends Factory {

  static final String FIELD_BANDWIDTH = "bandwidth";
  static final String FIELD_DURATION = "duration";
  static final String FIELD_PRICE = "price";

  void setProperties(Properties properties);
  
  void setTextFactory(TextFactory textFactory);
  
  Product getDefaultProduct(DaoSession session);
  
  ProductMap getAllProductMap(DaoSession session);
  
  ProductMap getAvailableProductMap(DaoSession session);
  
  Product getProduct(DaoSession session, int id);
  
  Map<String, String> validateProduct(DaoSession session, int bandwidth, int duration, double price);
  
  Product addProduct(DaoSession session, int bandwidth, int duration, double price, String description, boolean available);
  
  void updateProduct(DaoSession session, Product product, int bandwidth, int duration, double price, String description, boolean available);
  
  void deleteProduct(DaoSession session, int productId);
  
  List<Server> getServers(DaoSession session);
}
