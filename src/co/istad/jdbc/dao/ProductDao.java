package co.istad.jdbc.dao;

import co.istad.jdbc.model.Product;

import java.util.List;

public interface  ProductDao {

    // Dao = data access object

    List<Product> findAll();
}
