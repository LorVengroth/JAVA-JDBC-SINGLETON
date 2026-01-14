package co.istad.jdbc.dao;

import co.istad.jdbc.model.Product;

import java.util.List;

public interface  ProductDao {

    // Dao = data access object


    int delete(String code);

   /*
   * INsert a new record into DB
   * New data is product
   * @return affected row number
   */
   int insert(Product product);






    //1. Read all record from DB
    // Expected return value is List
    List<Product> findAll();

}
