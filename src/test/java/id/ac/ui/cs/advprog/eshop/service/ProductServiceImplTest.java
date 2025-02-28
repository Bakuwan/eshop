package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    private AutoCloseable closeable;

    @Mock
    private ProductRepositoryImpl productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void testCreate() {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Laptop");
        product.setProductQuantity(10);

        doNothing().when(productRepository).create(any(Product.class));

        Product createdProduct = productService.create(product);

        verify(productRepository, times(1)).create(product);
        assertNotNull(createdProduct);
        assertEquals("Laptop", createdProduct.getProductName());
        assertEquals(10, createdProduct.getProductQuantity());
    }

    @Test
    void testFindAll() {
        Product product1 = new Product();
        product1.setProductId("1");
        product1.setProductName("Laptop");
        product1.setProductQuantity(10);

        Product product2 = new Product();
        product2.setProductId("2");
        product2.setProductName("Mouse");
        product2.setProductQuantity(5);

        List<Product> productList = Arrays.asList(product1, product2);
        Iterator<Product> mockIterator = productList.iterator();
        when(productRepository.findAll()).thenReturn(mockIterator);

        List<Product> allProducts = productService.findAll();

        assertNotNull(allProducts);
        assertEquals(2, allProducts.size());
        assertEquals("Laptop", allProducts.get(0).getProductName());
        assertEquals("Mouse", allProducts.get(1).getProductName());

        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Laptop");
        product.setProductQuantity(10);

        when(productRepository.findById("1")).thenReturn(product);

        Product foundProduct = productService.findById("1");

        assertNotNull(foundProduct);
        assertEquals("1", foundProduct.getProductId());
        assertEquals("Laptop", foundProduct.getProductName());
        assertEquals(10, foundProduct.getProductQuantity());

        verify(productRepository, times(1)).findById("1");
    }

    @Test
    void testUpdate() {
        Product updatedProduct = new Product();
        updatedProduct.setProductId("1");
        updatedProduct.setProductName("Laptop Pro Max");
        updatedProduct.setProductQuantity(10);

        when(productRepository.update(updatedProduct)).thenReturn(updatedProduct);

        Product result = productService.update(updatedProduct);

        assertNotNull(result);
        assertEquals("1", result.getProductId());
        assertEquals("Laptop Pro Max", result.getProductName());
        assertEquals(10, result.getProductQuantity());

        verify(productRepository, times(1)).update(updatedProduct);
    }

    @Test
    void testDelete() {
        Product deletedProduct = new Product();
        deletedProduct.setProductId("1");
        deletedProduct.setProductName("Laptop");
        deletedProduct.setProductQuantity(10);

        productService.delete(deletedProduct.getProductId());
        assertNull(productRepository.findById("1"));

        verify(productRepository, times(1)).delete(deletedProduct.getProductId());
    }
}
