package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;

import java.util.Iterator;

public interface ProductRepository {
    void create(Product product);
    Iterator<Product> findAll();
    Product findById(String id);
    Product update(Product product);
    void delete(String id);
}
