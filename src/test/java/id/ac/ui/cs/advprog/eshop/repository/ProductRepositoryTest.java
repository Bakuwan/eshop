package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {

    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepository();
    }

    @Test
    void testCreateProduct() {
        Product product = new Product();
        productRepository.create(product);
        product.setProductId("1");
        product.setProductName("Laptop");
        product.setProductQuantity(10);

        Product retrievedProduct = productRepository.findById("1");
        assertNotNull(retrievedProduct);
        assertEquals("Laptop", retrievedProduct.getProductName());
        assertEquals(10, retrievedProduct.getProductQuantity());
    }

    @Test
    void testFindAllProducts() {
        Product product1 = new Product();
        productRepository.create(product1);
        product1.setProductId("1");
        product1.setProductName("Laptop");
        product1.setProductQuantity(10);

        Product product2 = new Product();
        productRepository.create(product2);
        product1.setProductId("2");
        product1.setProductName("Laptop Pro");
        product1.setProductQuantity(15);

        Iterator<Product> iterator = productRepository.findAll();
        int count = 0;
        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }

        assertEquals(2, count);
    }

    @Test
    void testFindById() {
        Product product = new Product();
        productRepository.create(product);
        product.setProductId("1");
        product.setProductName("Laptop");
        product.setProductQuantity(10);

        Product foundProduct = productRepository.findById("1");
        assertNotNull(foundProduct);
        assertEquals("Laptop", foundProduct.getProductName());
        assertEquals(10, foundProduct.getProductQuantity());
    }

    @Test
    void testFindByIdIfNotFound() {
        Product product = new Product();
        productRepository.create(product);
        product.setProductId("1");
        product.setProductName("Laptop");
        product.setProductQuantity(10);

        Product foundProduct = productRepository.findById("100");
        assertNull(foundProduct);
    }

    @Test
    void testUpdateProduct() {
        Product product = new Product();
        productRepository.create(product);
        product.setProductId("1");
        product.setProductName("Laptop");
        product.setProductQuantity(10);

        Product updatedProduct = new Product();
        updatedProduct.setProductId("1");
        updatedProduct.setProductName("Laptop Pro Max");
        updatedProduct.setProductQuantity(15);
        productRepository.update(updatedProduct);

        Product retrievedProduct = productRepository.findById("1");
        assertNotNull(retrievedProduct);
        assertEquals("Laptop Pro Max", retrievedProduct.getProductName());
        assertEquals(15, retrievedProduct.getProductQuantity());
    }

    @Test
    void testUpdateProductIfNotFound() {
        Product product = new Product();
        productRepository.create(product);
        product.setProductId("1");
        product.setProductName("Laptop");
        product.setProductQuantity(10);

        Product updatedProduct = new Product();
        updatedProduct.setProductId("999");
        updatedProduct.setProductName("Laptop Pro Max");
        updatedProduct.setProductQuantity(15);
        productRepository.update(updatedProduct);

        assertNull(productRepository.update(updatedProduct));
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        productRepository.create(product);
        product.setProductId("1");
        product.setProductName("Laptop");
        product.setProductQuantity(10);
        productRepository.delete("1");

        Product deletedProduct = productRepository.findById("1");
        assertNull(deletedProduct);
    }

    @Test
    void testDeleteProductIfNotFound() {
        Product product = new Product();
        productRepository.create(product);
        product.setProductId("1");
        product.setProductName("Laptop");
        product.setProductQuantity(10);
        productRepository.delete("999");

        Product existingProduct = productRepository.findById("1");
        assertNotNull(existingProduct);
    }

}
