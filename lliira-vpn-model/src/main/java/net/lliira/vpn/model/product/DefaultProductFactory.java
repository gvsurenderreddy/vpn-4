package net.lliira.vpn.model.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.lliira.vpn.model.Errors;
import net.lliira.vpn.model.TextFactory;
import net.lliira.vpn.model.dao.DaoSession;
import net.lliira.vpn.model.dao.mapper.ProductMapper;
import net.lliira.vpn.model.user.Server;

public class DefaultProductFactory implements ProductFactory {

  private static final String PROP_DEFAULT_PRODUCT = "default-product";

  private int defaultId;
  private TextFactory textFactory;

  @Override
  public void setProperties(Properties properties) {
    String strDefault = properties.getProperty(PROP_DEFAULT_PRODUCT, "0");
    defaultId = Integer.valueOf(strDefault);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * net.lliira.vpn.model.product.ProductFactory#setTextFactory(net.lliira.vpn
   * .model.TextFactory)
   */
  @Override
  public void setTextFactory(TextFactory textFactory) {
    this.textFactory = textFactory;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * net.lliira.vpn.model.product.ProductFactory#getDefaultProduct(net.lliira
   * .vpn.model.dao.DaoSession)
   */
  @Override
  public Product getDefaultProduct(DaoSession session) {
    List<Product> products = getAvailableProductMap(session).getProducts();
    for (Product product : products) {
      if (product.getId() == defaultId) return product;
    }
    return null;
  }

  @Override
  public ProductMap getAvailableProductMap(DaoSession session) {
    ProductMapper mapper = session.getMapper(ProductMapper.class);
    return new ProductMap(mapper.selectAvailableProducts());
  }

  @Override
  public ProductMap getAllProductMap(DaoSession session) {
    ProductMapper mapper = session.getMapper(ProductMapper.class);
    return new ProductMap(mapper.selectAllProducts());
  }

  @Override
  public Product getProduct(DaoSession session, int id) {
    ProductMapper mapper = session.getMapper(ProductMapper.class);
    return mapper.selectProduct(id);
  }

  /*
   * (non-Javadoc)
   * 
   * @see net.lliira.vpn.model.product.ProductFactory#validateAddProduct(int,
   * int, double)
   */
  @Override
  public Map<String, String> validateProduct(DaoSession session, int bandwidth,
      int duration, double price) {
    Map<String, String> errors = new HashMap<String, String>();
    if (bandwidth < 1) {
      errors.put(FIELD_BANDWIDTH, textFactory.get(Errors.BANDWIDTH_INVALID));
    }
    if (duration < 1) {
      errors.put(FIELD_DURATION, textFactory.get(Errors.DURATION_INVALID));
    }
    if (bandwidth <= 0) {
      errors.put(FIELD_PRICE, textFactory.get(Errors.PRICE_INVALID));
    }

    return errors;
  }

  /*
   * (non-Javadoc)
   * 
   * @see net.lliira.vpn.model.product.ProductFactory#addProduct(int, int,
   * double, java.lang.String, boolean)
   */
  @Override
  public Product addProduct(DaoSession session, int bandwidth, int duration,
      double price, String description, boolean available) {
    Product product = new Product();
    product.setAvailable(available);
    product.setBandwidth(bandwidth);
    product.setDescription(description);
    product.setDuration(duration);
    product.setPrice(price);

    ProductMapper mapper = session.getMapper(ProductMapper.class);
    mapper.insertProduct(product);

    return product;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * net.lliira.vpn.model.product.ProductFactory#updateProduct(net.lliira.vpn
   * .model.product.Product, int, int, double, java.lang.String, boolean)
   */
  @Override
  public void updateProduct(DaoSession session, Product product, int bandwidth,
      int duration, double price, String description, boolean available) {
    product.setAvailable(available);
    product.setBandwidth(bandwidth);
    product.setDescription(description);
    product.setDuration(duration);
    product.setPrice(price);

    ProductMapper mapper = session.getMapper(ProductMapper.class);
    mapper.updateProduct(product);
  }

  @Override
  public void deleteProduct(DaoSession session, int productId) {
    ProductMapper mapper = session.getMapper(ProductMapper.class);
    mapper.deleteProduct(productId);
  }

  @Override
  public List<Server> getServers(DaoSession session) {
    // TODO Auto-generated method stub
    return null;
  }

}
