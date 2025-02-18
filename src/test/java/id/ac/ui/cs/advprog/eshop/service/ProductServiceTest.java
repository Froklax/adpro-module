package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Pak Asep");
        product.setProductQuantity(100);
    }

    @Test
    void testCreate() {
        when(productRepository.create(product)).thenReturn(product);
        Product createdProduct = productService.create(product);
        assertNotNull(createdProduct);
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", createdProduct.getProductId());
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testFindAll() {
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        Iterator<Product> iterator = productList.iterator();

        when(productRepository.findAll()).thenReturn(iterator);
        List<Product> foundProducts = productService.findAll();
        assertEquals(1, foundProducts.size());
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", foundProducts.get(0).getProductId());
    }

    @Test
    void testGetProduct() {
        when(productRepository.getProduct("eb558e9f-1c39-460e-8860-71af6af63bd6")).thenReturn(product);
        Product foundProduct = productService.getProduct("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertNotNull(foundProduct);
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", foundProduct.getProductId());
    }

    @Test
    void testUpdateProduct() {
        when(productRepository.updateProduct(product)).thenReturn(product);
        Product updatedProduct = productService.updateProduct(product);
        assertNotNull(updatedProduct);
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", updatedProduct.getProductId());
    }

    @Test
    void testDeleteProduct() {
        doNothing().when(productRepository).deleteProduct("eb558e9f-1c39-460e-8860-71af6af63bd6");
        productService.deleteProduct("eb558e9f-1c39-460e-8860-71af6af63bd6");
        verify(productRepository, times(1)).deleteProduct("eb558e9f-1c39-460e-8860-71af6af63bd6");
    }
}
