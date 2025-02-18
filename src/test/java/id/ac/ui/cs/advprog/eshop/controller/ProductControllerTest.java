package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
class ProductControllerTest {
    @Mock
    private ProductService productService;
    @InjectMocks
    private ProductController productController;
    private MockMvc mockMvc;

    @Test
    void testCreateProductPage() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("CreateProduct"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    void testCreateProductPost() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
        mockMvc.perform(post("/product/create"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));
    }

    @Test
    void testProductListPage() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
        List<Product> productList = Arrays.asList(new Product());
        when(productService.findAll()).thenReturn(productList);

        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("ProductList"))
                .andExpect(model().attributeExists("products"));
    }

    @Test
    void testEditProductPage() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
        Product product = new Product();
        when(productService.getProduct("123")).thenReturn(product);

        mockMvc.perform(get("/product/edit/123"))
                .andExpect(status().isOk())
                .andExpect(view().name("EditProduct"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    void testUpdateProduct() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
        mockMvc.perform(post("/product/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));
    }

    @Test
    void testDeleteProduct() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
        mockMvc.perform(post("/product/delete/123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));
    }
}
