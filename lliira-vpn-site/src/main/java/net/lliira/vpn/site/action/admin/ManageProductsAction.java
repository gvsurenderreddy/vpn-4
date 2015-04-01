package net.lliira.vpn.site.action.admin;

import net.lliira.vpn.model.product.ProductFactory;
import net.lliira.vpn.model.product.ProductMap;
import net.lliira.vpn.site.Constants;
import net.lliira.vpn.site.action.DefaultAction;

public class ManageProductsAction extends DefaultAction {

  /**
   * 
   */
  private static final long serialVersionUID = 4136137980713339998L;

  private ProductFactory productFactory;

  private ProductMap productMap;

  private int id;

  /**
   * @return the products
   */
  public ProductMap getProductMap() {
    return productMap;
  }

  /**
   * @param id
   *          the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * @param productFactory
   *          the productFactory to set
   */
  public void setProductFactory(ProductFactory productFactory) {
    this.productFactory = productFactory;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.opensymphony.xwork2.ActionSupport#execute()
   */
  @Override
  public String execute() throws Exception {
    productMap = productFactory.getAllProductMap(session);
    return Constants.RESULT_SUCCESS;
  }

  public String deleteProduct() {
    productFactory.deleteProduct(session, id);
    return Constants.RESULT_SUCCESS;
  }

}
