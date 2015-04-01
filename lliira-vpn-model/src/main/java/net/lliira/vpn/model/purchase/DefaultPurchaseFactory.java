/**
 * 
 */
package net.lliira.vpn.model.purchase;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import net.lliira.vpn.model.Errors;
import net.lliira.vpn.model.TextFactory;
import net.lliira.vpn.model.dao.DaoSession;
import net.lliira.vpn.model.dao.mapper.PurchaseMapper;
import net.lliira.vpn.model.email.EmailFactory;
import net.lliira.vpn.model.email.EmailTemplate;
import net.lliira.vpn.model.product.Product;
import net.lliira.vpn.model.user.User;
import net.lliira.vpn.model.user.UserFactory;

/**
 * @author jerric
 * 
 */
public class DefaultPurchaseFactory implements PurchaseFactory {

  private static final String PURCHASE_EMAIL = "/purchase.email";

  private static final String MACRO_DURATION = "duration";
  private static final String MACRO_BANDWIDTH = "bandwidth";
  private static final String MACRO_PRICE = "price";
  private static final String MACRO_START = "start";
  private static final String MACRO_END = "end";

  private TextFactory textFactory;
  private UserFactory userFactory;
  private EmailFactory emailFactory;

  /*
   * (non-Javadoc)
   * 
   * @see
   * net.lliira.vpn.model.purchase.PurchaseFactory#setTextFactory(net.lliira
   * .vpn.model.TextFactory)
   */
  @Override
  public void setTextFactory(TextFactory textFactory) {
    this.textFactory = textFactory;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * net.lliira.vpn.model.purchase.PurchaseFactory#setUserFactory(net.lliira
   * .vpn.model.user.UserFactory)
   */
  @Override
  public void setUserFactory(UserFactory userFactory) {
    this.userFactory = userFactory;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * net.lliira.vpn.model.purchase.PurchaseFactory#setEmailFactory(net.lliira
   * .vpn.model.email.EmailFactory)
   */
  @Override
  public void setEmailFactory(EmailFactory emailFactory) {
    this.emailFactory = emailFactory;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * net.lliira.vpn.model.purchase.PurchaseFactory#getPurchases(java.lang.String
   * )
   */
  @Override
  public List<Purchase> getPurchases(DaoSession session, String email) {
    email = User.unescapeEmail(email);
    PurchaseMapper mapper = session.getMapper(PurchaseMapper.class);
    return mapper.selectPurchasesByUser(email);
  }

  /*
   * (non-Javadoc)
   * 
   * @see net.lliira.vpn.model.purchase.PurchaseFactory#getPurchase(int)
   */
  @Override
  public Purchase getPurchase(DaoSession session, int id) {
    PurchaseMapper mapper = session.getMapper(PurchaseMapper.class);
    return mapper.selectPurchase(id);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * net.lliira.vpn.model.purchase.PurchaseFactory#validateAddPurchase(net.lliira
   * .vpn.model.dao.DaoSession, java.lang.String,
   * net.lliira.vpn.model.product.Product)
   */
  @Override
  public Map<String, String> validateAddPurchase(DaoSession session,
      String email, Product product) {
    Map<String, String> errors = new HashMap<String, String>();

    // check if email is a valid one
    email = User.unescapeEmail(email);
    User user = userFactory.getUser(session, email);
    if (user == null) {
      errors.put(FIELD_EMAIL, textFactory.get(Errors.EMAIL_INVALID));
    } else if (!user.isActivated()) {
      errors.put(FIELD_EMAIL, textFactory.get(Errors.USER_NOT_ACTIVATED));
    }

    return errors;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * net.lliira.vpn.model.purchase.PurchaseFactory#addPurchase(net.lliira.vpn
   * .model.dao.DaoSession, java.lang.String,
   * net.lliira.vpn.model.product.Product)
   */
  @Override
  public Purchase addPurchase(DaoSession session, String email, Product product)
      throws IOException, MessagingException {
    email = User.unescapeEmail(email);
    User user = userFactory.getUser(session, email);

    Purchase purchase = new Purchase();
    purchase.setProductId(product.getId());
    purchase.setBandwidth(product.getBandwidth());
    purchase.setDuration(product.getDuration());
    purchase.setPrice(product.getPrice());
    purchase.setUserId(user.getId());
    purchase.setPurchaseTime(new Date());

    PurchaseMapper mapper = session.getMapper(PurchaseMapper.class);
    mapper.insertPurchase(purchase);

    sendPurchaseEmail(purchase, user);
    return purchase;
  }

  private void sendPurchaseEmail(Purchase purchase, User user)
      throws IOException, MessagingException {
    Map<String, String> macros = new HashMap<String, String>();
    macros.put(MACRO_BANDWIDTH, Integer.toString(purchase.getBandwidth()));
    macros.put(MACRO_DURATION, Integer.toString(purchase.getDuration()));
    macros.put(MACRO_PRICE, Double.toString(purchase.getPrice()));

    DateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
    Date startTime = purchase.getPurchaseTime();
    Calendar time = Calendar.getInstance();
    time.setTime(startTime);
    time.add(Calendar.MONTH, purchase.getDuration());
    Date endTime = time.getTime();
    macros.put(MACRO_START, format.format(startTime));
    macros.put(MACRO_END, format.format(endTime));

    EmailTemplate email = emailFactory.loadEmailTemplate(PURCHASE_EMAIL);
    String subject = email.prepareSubject(new HashMap<String, String>());
    String content = email.prepareContent(macros);
    emailFactory.sendEmail(user.getEmail(), emailFactory.getSupportEmail(),
        subject, content);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * net.lliira.vpn.model.purchase.PurchaseFactory#updatePurchase(net.lliira
   * .vpn.model.dao.DaoSession, net.lliira.vpn.model.purchase.Purchase,
   * net.lliira.vpn.model.product.Product, java.util.Date)
   */
  @Override
  public void updatePurchase(DaoSession session, Purchase purchase,
      Product product, Date purchaseTime) {
    purchase.setProductId(product.getId());
    purchase.setBandwidth(product.getBandwidth());
    purchase.setDuration(product.getDuration());
    purchase.setPrice(product.getPrice());
    purchase.setPurchaseTime(purchaseTime);

    PurchaseMapper mapper = session.getMapper(PurchaseMapper.class);
    mapper.updatePurchase(purchase);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * net.lliira.vpn.model.purchase.PurchaseFactory#deletePurchase(net.lliira
   * .vpn.model.dao.DaoSession, int)
   */
  @Override
  public void deletePurchase(DaoSession session, int id) {
    PurchaseMapper mapper = session.getMapper(PurchaseMapper.class);
    mapper.deletePurchase(id);
  }

}
