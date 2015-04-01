package net.lliira.vpn.model.product;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductMap {

  private final List<Product> products;
  private final Integer[] durations;
  private final Integer[] bandwidths;

  public ProductMap(List<Product> products) {
    this.products = products;

    Set<Integer> durationSet = new HashSet<Integer>();
    Set<Integer> bandwidthSet = new HashSet<Integer>();
    for (Product product : products) {
      durationSet.add(product.getDuration());
      bandwidthSet.add(product.getBandwidth());
    }
    durations = durationSet.toArray(new Integer[0]);
    Arrays.sort(durations);
    bandwidths = bandwidthSet.toArray(new Integer[0]);
    Arrays.sort(bandwidths);
  }

  /**
   * @return the products
   */
  public List<Product> getProducts() {
    return products;
  }
  
  public Product[][] getArray() {
    Product[][] array = new Product[bandwidths.length][durations.length];
    for (int b = 0; b < bandwidths.length; b++) {
      for(int d = 0; d < durations.length; d++) {
        array[b][d] = getProduct(durations[d], bandwidths[b]);
      }
    }
    return array;
  }

  /**
   * @return the durations
   */
  public Integer[] getDurations() {
    return durations;
  }

  /**
   * @return the bandwidths
   */
  public Integer[] getBandwidths() {
    return bandwidths;
  }

  public Product getProduct(int duration, int bandwidth) {
    for (Product product : products) {
      if (product.getDuration() == duration
          && product.getBandwidth() == bandwidth) return product;
    }
    return null;
  }
}
