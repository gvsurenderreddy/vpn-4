package net.lliira.vpn.model.dao.mapper;

import java.util.List;

import net.lliira.vpn.model.product.Product;

public interface ProductMapper {

  List<Product> selectAvailableProducts();

  List<Product> selectAllProducts();
  
  Product selectProduct(int id);
  
  void insertProduct(Product product);
  
  void updateProduct(Product product);
  
  void deleteProduct(int id);
}
