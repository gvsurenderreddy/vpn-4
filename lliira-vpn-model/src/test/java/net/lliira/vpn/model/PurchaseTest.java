package net.lliira.vpn.model;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.junit.Assert;
import org.junit.Test;

import net.lliira.vpn.model.product.Product;
import net.lliira.vpn.model.product.ProductFactory;
import net.lliira.vpn.model.purchase.Purchase;
import net.lliira.vpn.model.purchase.PurchaseFactory;
import net.lliira.vpn.model.user.User;
import net.lliira.vpn.model.user.UserException;
import net.lliira.vpn.model.user.UserFactory;

public class PurchaseTest extends AbstractTest {

  private static final int COUNT = 10;

  private final PurchaseFactory purchaseFactory;
  private final ProductFactory productFactory;
  private final UserFactory userFactory;

  public PurchaseTest() {
    this.purchaseFactory = TestHelper.getBean(PurchaseFactory.class);
    this.productFactory = TestHelper.getBean(ProductFactory.class);
    this.userFactory = TestHelper.getBean(UserFactory.class);
  }

  @Test
  public void testGetPurchase() throws MessagingException, UserException,
      IOException {
    Product product = createProduct();
    User user = createUser();
    Purchase purchase =
        purchaseFactory.addPurchase(session, user.getEmailEscaped(), product);

    Assert.assertTrue(purchase.getId() > 0);
    Assert.assertEquals(user.getId(), purchase.getUserId());
    Assert.assertEquals(product.getId(), purchase.getProductId());
    Assert.assertEquals(product.getBandwidth(), purchase.getBandwidth());
    Assert.assertEquals(product.getDuration(), purchase.getDuration());
    Assert.assertEquals(product.getPrice(), purchase.getPrice(), 0.000001);

    int id = purchase.getId();
    purchase = purchaseFactory.getPurchase(session, id);
    Assert.assertEquals(id, purchase.getId());
    Assert.assertEquals(user.getId(), purchase.getUserId());
    Assert.assertEquals(product.getId(), purchase.getProductId());
    Assert.assertEquals(product.getBandwidth(), purchase.getBandwidth());
    Assert.assertEquals(product.getDuration(), purchase.getDuration());
    Assert.assertEquals(product.getPrice(), purchase.getPrice(), 0.000001);
  }

  @Test
  public void testGetPurchasesByUser() throws MessagingException,
      UserException, IOException {
    User user = createUser();
    String email = user.getEmailEscaped();
    for (int i = 0; i < COUNT; i++) {
      Product product = createProduct();
      purchaseFactory.addPurchase(session, email, product);
    }

    List<Purchase> purchases = purchaseFactory.getPurchases(session, email);
    Assert.assertEquals(COUNT, purchases.size());
    for (Purchase purchase : purchases) {
      Assert.assertEquals(user.getId(), purchase.getUserId());
    }
  }

  @Test
  public void testUpdatePurchase() throws MessagingException, UserException,
      IOException {
    Product product = createProduct();
    User user = createUser();
    Purchase purchase =
        purchaseFactory.addPurchase(session, user.getEmailEscaped(), product);
    int id = purchase.getId();

    product = createProduct();
    Date purchaseTime = new Date();
    purchaseFactory.updatePurchase(session, purchase, product, purchaseTime);
    Assert.assertEquals(id, purchase.getId());
    Assert.assertEquals(user.getId(), purchase.getUserId());
    Assert.assertEquals(product.getId(), purchase.getProductId());
    Assert.assertEquals(product.getBandwidth(), purchase.getBandwidth());
    Assert.assertEquals(product.getDuration(), purchase.getDuration());
    Assert.assertEquals(product.getPrice(), purchase.getPrice(), 0.000001);

    purchase = purchaseFactory.getPurchase(session, id);
    Assert.assertEquals(id, purchase.getId());
    Assert.assertEquals(user.getId(), purchase.getUserId());
    Assert.assertEquals(product.getId(), purchase.getProductId());
    Assert.assertEquals(product.getBandwidth(), purchase.getBandwidth());
    Assert.assertEquals(product.getDuration(), purchase.getDuration());
    Assert.assertEquals(product.getPrice(), purchase.getPrice(), 0.000001);
  }

  @Test
  public void testDeletePurchase() throws MessagingException, UserException,
      IOException {
    Product product = createProduct();
    User user = createUser();
    Purchase purchase =
        purchaseFactory.addPurchase(session, user.getEmailEscaped(), product);
    int id = purchase.getId();

    purchaseFactory.deletePurchase(session, id);
    purchase = purchaseFactory.getPurchase(session, id);
    Assert.assertNull(purchase);
  }

  private Product createProduct() {
    int bandwidth = random.nextInt(100) + 1;
    int duration = random.nextInt(12) + 1;
    double price = Math.round(random.nextDouble() * 50000) / 100.0 + 1;
    String description = "product-" + random.nextInt();
    boolean available = random.nextBoolean();

    return productFactory.addProduct(session, bandwidth, duration, price,
        description, available);
  }

  private User createUser() throws MessagingException, UserException {
    String email = "user" + random.nextInt() + "@test.com";
    String password = "pass" + random.nextInt();
    User user = userFactory.register(session, email, password, null);
    userFactory.activate(session, email, user.getActivationCode());
    return user;
  }
}
