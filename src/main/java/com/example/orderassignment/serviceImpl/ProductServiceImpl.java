package com.example.orderassignment.serviceImpl;

import com.example.orderassignment.dto.ProductDTO;
import com.example.orderassignment.entity.Product;
import com.example.orderassignment.exception.AlreadyExists;
import com.example.orderassignment.exception.ProductNotFound;
import com.example.orderassignment.mapper.ProductMapper;
import com.example.orderassignment.repository.ProductRepository;
import com.example.orderassignment.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;

    /**
     * Returns a list of all the
     * products found in the database
     * @return a list of the products
     * @throws ProductNotFound when no orders are found
     */
    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> productsList = productRepository.findAll();
        if(productsList.isEmpty()) {
            throw new ProductNotFound("No products were found!");
        }
        return productMapper.toDto(productsList);
    }

    /**
     * Returns a product by the given id
     * @param id is the id of a product
     * @return a product
     * @throws IllegalArgumentException when the id
     * is null
     */
    @Override
    public Product getProductById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return productRepository.findById(id).orElse(null);
    }

    /**
     * Creates an object of the type product
     * in the database
     * @param product is the product information provided
     * @return th id of the product
     */
    @Override
    public Product createProduct(Product product)  {
        if(productRepository.findByName(product.getName()) == null) {
            return productRepository.save(product);
        }
        throw new AlreadyExists("Product with the same name already exists");
    }
}
