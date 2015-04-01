package net.lliira.vpn.site.action;

import java.util.Map;

import net.lliira.vpn.model.pref.PreferenceFactory;
import net.lliira.vpn.model.product.ProductFactory;
import net.lliira.vpn.model.product.ProductMap;
import net.lliira.vpn.site.Constants;

public class ProductsAction extends DefaultAction {

  /**
   * 
   */
  private static final long serialVersionUID = -8765778811500222220L;
  
  private PreferenceFactory preferenceFactory;
  private ProductFactory productFactory;
  
  private ProductMap productMap;
  private Map<String, String> preferences;



  /**
   * @param preferenceFactory the preferenceFactory to set
   */
  public void setPreferenceFactory(PreferenceFactory preferenceFactory) {
    this.preferenceFactory = preferenceFactory;
  }


  /**
   * @param productFactory the productFactory to set
   */
  public void setProductFactory(ProductFactory productFactory) {
    this.productFactory = productFactory;
  }


  /**
   * @return the products
   */
  public ProductMap getProductMap() {
    return productMap;
  }



  /**
   * @return the preferences
   */
  public Map<String, String> getPreferences() {
    return preferences;
  }
  


  /*
   * (non-Javadoc)
   * 
   * @see com.opensymphony.xwork2.ActionSupport#execute()
   */
  @Override
  public String execute() throws Exception {
    productMap = productFactory.getAvailableProductMap(session);
    preferences =  preferenceFactory.getPreferences(session);
    return Constants.RESULT_SUCCESS;
  }

}
