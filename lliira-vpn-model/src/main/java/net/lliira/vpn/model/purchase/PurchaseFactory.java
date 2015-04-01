package net.lliira.vpn.model.purchase;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import net.lliira.vpn.model.TextFactory;
import net.lliira.vpn.model.dao.DaoSession;
import net.lliira.vpn.model.email.EmailFactory;
import net.lliira.vpn.model.product.Product;
import net.lliira.vpn.model.user.UserFactory;

public interface PurchaseFactory {

  static final String FIELD_EMAIL = "email";

  void setTextFactory(TextFactory textFactory);

  void setUserFactory(UserFactory userFactory);

  void setEmailFactory(EmailFactory emailFactory);

  List<Purchase> getPurchases(DaoSession session, String email);

  Purchase getPurchase(DaoSession session, int id);

  Map<String, String> validateAddPurchase(DaoSession session, String email,
      Product product);

  Purchase addPurchase(DaoSession session, String email, Product product)
      throws IOException, MessagingException;

  void updatePurchase(DaoSession session, Purchase purchase, Product product,
      Date purchaseTime);

  void deletePurchase(DaoSession session, int id);
}
