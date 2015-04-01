package net.lliira.vpn.model.dao.mapper;

import java.util.List;

import net.lliira.vpn.model.purchase.Purchase;

public interface PurchaseMapper {

  List<Purchase> selectPurchasesByUser(String email);
  
  Purchase selectPurchase(int id);
  
  void insertPurchase(Purchase purchase);
  
  void updatePurchase(Purchase purchase);
  
  void deletePurchase(int id);
}
