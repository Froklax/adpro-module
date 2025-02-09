package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product getProduct(String productId) {
        for (Product product:productData) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }

        return null;
    }

    public Product updateProduct(Product product) {
        Product newProduct = getProduct(product.getProductId());
        newProduct.setProductName(product.getProductName());
        newProduct.setProductQuantity(product.getProductQuantity());
        return newProduct;
    }
}
