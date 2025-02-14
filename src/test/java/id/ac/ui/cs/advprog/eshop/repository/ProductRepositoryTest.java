package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setup() {
        this.productRepository = new ProductRepository();
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb5589f1c39-646e-8860-71a6fa63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());

        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb5589f1c39-646e-8860-71a6fa63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("40f9e46e-901f-425d-ab0f-d0821dde9096");
        product2.setProductName("Sampo Cap User");
        product2.setProductQuantity(200);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());

        assertTrue(productIterator.hasNext());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());

        assertFalse(productIterator.hasNext());
    }

    @Test
    void testUpdateProduct() {
        Product product = new Product();
        product.setProductId("eb5589f1c39-646e-8860-71a6fa63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        productRepository.create(product);

        product.setProductName("Sampo Cap Aja");
        product.setProductQuantity(50);

        Product updatedProduct = productRepository.update(product);

        assertNotNull(updatedProduct);
        assertEquals("Sampo Cap Aja", updatedProduct.getProductName());
        assertEquals(50, updatedProduct.getProductQuantity());
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setProductId("eb5589f1c39-646e-8860-71a6fa63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        productRepository.create(product);

        productRepository.delete(product.getProductId());

        Product deletedProduct = productRepository.findById(product.getProductId());

        assertNull(deletedProduct);
    }

    @Test
    void testDeleteProductNotFound() {
        // Attempting to delete a product that does not exist in the repository
        productRepository.delete("non_existing_id");

        // Verifying that the product with the non-existing ID is still not found
        Product deletedProduct = productRepository.findById("non_existing_id");

        assertNull(deletedProduct);
    }

}
