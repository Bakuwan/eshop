package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductModelTest {
    @Test
    void testSetAndGetProductId() {
        Product product = new Product();
        product.setProductId("P001");
        assertEquals("P001", product.getProductId());
    }

    @Test
    void testSetAndGetProductName() {
        Product product = new Product();
        product.setProductName("Laptop");
        assertEquals("Laptop", product.getProductName());
    }

    @Test
    void testSetAndGetProductQuantity() {
        Product product = new Product();
        product.setProductQuantity(10);
        assertEquals(10, product.getProductQuantity());
    }
}
