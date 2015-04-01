package net.lliira.vpn.model;

import java.util.HashSet;
import java.util.Set;

import net.lliira.vpn.model.product.Product;
import net.lliira.vpn.model.product.ProductFactory;
import net.lliira.vpn.model.product.ProductMap;

import org.junit.Assert;
import org.junit.Test;

public class ProductTest extends AbstractTest {

  private static final int COUNT = 20;

  private final ProductFactory productFactory;

  public ProductTest() {
    productFactory = TestHelper.getBean(ProductFactory.class);
  }

  @Test
  public void testGetProduct() {
    int bandwidth = random.nextInt(100) + 1;
    int duration = random.nextInt(12) + 1;
    double price = Math.round(random.nextDouble() * 50000) / 100.0 + 1;
    String description = "product-" + random.nextInt();
    boolean available = random.nextBoolean();

    Product product =
        productFactory.addProduct(session, bandwidth, duration, price,
            description, available);
    Assert.assertTrue(product.getId() > 0);
    Assert.assertEquals(bandwidth, product.getBandwidth());
    Assert.assertEquals(duration, product.getDuration());
    Assert.assertEquals(price, product.getPrice(), 0.000001);
    Assert.assertEquals(description, product.getDescription());
    Assert.assertEquals(available, product.isAvailable());

    int id = product.getId();
    product = productFactory.getProduct(session, id);
    Assert.assertEquals(id, product.getId());
    Assert.assertEquals(bandwidth, product.getBandwidth());
    Assert.assertEquals(duration, product.getDuration());
    Assert.assertEquals(price, product.getPrice(), 0.000001);
    Assert.assertEquals(description, product.getDescription());
    Assert.assertEquals(available, product.isAvailable());
  }

  @Test
  public void testUpdateProduct() {
    int bandwidth = random.nextInt(100) + 1;
    int duration = random.nextInt(12) + 1;
    double price = Math.round(random.nextDouble() * 50000) / 100.0 + 1;
    String description = "product-" + random.nextInt();
    boolean available = random.nextBoolean();

    Product product =
        productFactory.addProduct(session, bandwidth, duration, price,
            description, available);
    int id = product.getId();

    bandwidth = random.nextInt(100) + 1;
    duration = random.nextInt(12) + 1;
    price = Math.round(random.nextDouble() * 50000) / 100.0 + 1;
    description = "product-" + random.nextInt();
    available = random.nextBoolean();
    productFactory.updateProduct(session, product, bandwidth, duration, price,
        description, available);

    Assert.assertEquals(id, product.getId());
    Assert.assertEquals(bandwidth, product.getBandwidth());
    Assert.assertEquals(duration, product.getDuration());
    Assert.assertEquals(price, product.getPrice(), 0.000001);
    Assert.assertEquals(description, product.getDescription());
    Assert.assertEquals(available, product.isAvailable());

    product = productFactory.getProduct(session, id);
    Assert.assertEquals(id, product.getId());
    Assert.assertEquals(bandwidth, product.getBandwidth());
    Assert.assertEquals(duration, product.getDuration());
    Assert.assertEquals(price, product.getPrice(), 0.000001);
    Assert.assertEquals(description, product.getDescription());
    Assert.assertEquals(available, product.isAvailable());
  }

  @Test
  public void testDeleteProduct() {
    int bandwidth = random.nextInt(100) + 1;
    int duration = random.nextInt(12) + 1;
    double price = Math.round(random.nextDouble() * 50000) / 100.0 + 1;
    String description = "product-" + random.nextInt();
    boolean available = random.nextBoolean();

    Product product =
        productFactory.addProduct(session, bandwidth, duration, price,
            description, available);
    int id = product.getId();

    productFactory.deleteProduct(session, id);
    Assert.assertNull(productFactory.getProduct(session, id));
  }

  @Test
  public void testGetAllProductMap() {
    Set<Integer> ids = new HashSet<Integer>();
    for (int i = 0; i < COUNT; i++) {
      int bandwidth = random.nextInt(10000) + 1;
      int duration = random.nextInt(1000) + 1;
      double price = Math.round(random.nextDouble() * 50000) / 100.0 + 1;
      String description = "product-" + random.nextInt();
      boolean available = random.nextBoolean();
      Product product =
          productFactory.addProduct(session, bandwidth, duration, price,
              description, available);
      ids.add(product.getId());
    }
    Assert.assertEquals(COUNT, ids.size());

    ProductMap productMap = productFactory.getAllProductMap(session);
    Assert.assertTrue(productMap.getProducts().size() >= ids.size());
    Assert.assertTrue(productMap.getBandwidths().length > 0);
    Assert.assertTrue(productMap.getDurations().length > 0);
    Assert.assertTrue(productMap.getArray().length > 0);

    int count = 0;
    for (Product[] row : productMap.getArray()) {
      for (Product product : row) {
        if (product != null) {
          count++;
          int duration = product.getDuration();
          int bandwidth = product.getBandwidth();
          Assert.assertNotNull(productMap.getProduct(duration, bandwidth));
        }
      }
    }
    Assert.assertTrue(count >= COUNT);
  }

  @Test
  public void testGetAvailableProductMap() {
    Set<Integer> ids = new HashSet<Integer>();
    for (int i = 0; i < COUNT; i++) {
      int bandwidth = random.nextInt(10000) + 1;
      int duration = random.nextInt(1000) + 1;
      double price = Math.round(random.nextDouble() * 50000) / 100.0 + 1;
      String description = "product-" + random.nextInt();
      boolean available = random.nextBoolean();
      Product product =
          productFactory.addProduct(session, bandwidth, duration, price,
              description, available);
      if (available) ids.add(product.getId());
    }

    ProductMap productMap = productFactory.getAvailableProductMap(session);
    Assert.assertTrue(productMap.getProducts().size() >= ids.size());
    Assert.assertTrue(productMap.getBandwidths().length > 0);
    Assert.assertTrue(productMap.getDurations().length > 0);
    Product[][] products = productMap.getArray();
    Assert.assertTrue(products.length == productMap.getBandwidths().length);
    Assert.assertTrue(products[0].length == productMap.getDurations().length);
    boolean hasProduct = false;
    for (int b = 0; b < products.length; b++) {
      for (int d = 0; d < products[b].length; d++) {
        if (products[b][d] != null) hasProduct = true;
      }
    }
    Assert.assertTrue(hasProduct);

    int count = 0;
    for (Product product : productMap.getProducts()) {
      if (product != null) {
        Assert.assertTrue(product.isAvailable());
        count++;
      }
    }
    Assert.assertTrue(count >= ids.size());
  }
}
