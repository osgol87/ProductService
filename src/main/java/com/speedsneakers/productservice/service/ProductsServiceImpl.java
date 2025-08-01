package com.speedsneakers.productservice.service;

import com.speedsneakers.productservice.exception.IllegalProductIdException;
import com.speedsneakers.productservice.exception.InvalidProductRequest;
import com.speedsneakers.productservice.exception.ProductNotFoundException;
import com.speedsneakers.productservice.model.pojo.Product;
import com.speedsneakers.productservice.model.request.ProductRequest;
import com.speedsneakers.productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * Implementación del servicio de productos.
 */
@Service
@Slf4j
public class ProductsServiceImpl implements ProductsService {

    /**
     * Repositorio de productos.
     */
    private final ProductRepository productRepository;

    /**
     * Constructor del servicio de productos.
     *
     * @param productRepository Repositorio de productos.
     */
    @Autowired
    public ProductsServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Obtiene productos que coincidan con los filtros proporcionados.
     * Return List<Product> Lista de productos que coinciden con los filtros.
     */
    @Override
    public List<Product> getProducts(String name, String brand, String category) {

        if (StringUtils.hasLength(name)
            || StringUtils.hasLength(brand)
            || StringUtils.hasLength(category)) {

            return productRepository.searchProducts(name, brand, category);
        }

        return productRepository.findAll();
    }

    /**
     * Obtiene un producto por su ID.
     * Return Product Producto con el ID proporcionado.
     */
    @Override
    public Product getProductById(String id) {

        if (!StringUtils.hasLength(id)) {
            throw new IllegalProductIdException(id);
        }

        return productRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    /**
     * Crea un nuevo producto.
     * Return Product Producto creado.
     */
    @Override
    public Product createProduct(ProductRequest request) {

        if (request != null
            && StringUtils.hasLength(request.getName())
            && StringUtils.hasLength(request.getBrand())
            && StringUtils.hasLength(request.getCategory())
            && StringUtils.hasLength(request.getShortDescription())
            && StringUtils.hasLength(request.getLongDescription())
            && StringUtils.hasLength(request.getPrice())
            && StringUtils.hasLength(request.getImageUrl())) {

            Product product = new Product();
            product.setName(request.getName());
            product.setBrand(request.getBrand());
            product.setCategory(request.getCategory());
            product.setShortDescription(request.getShortDescription());
            product.setLongDescription(request.getLongDescription());
            product.setPrice(new BigDecimal(request.getPrice()));
            product.setImageUrl(request.getImageUrl());

            return productRepository.save(product);
        }

        throw new InvalidProductRequest("Invalid product request");
    }

    @Override
    public Product updateProduct(String id, ProductRequest request) {

        if (request != null
            && StringUtils.hasLength(request.getName())
            && StringUtils.hasLength(request.getBrand())
            && StringUtils.hasLength(request.getCategory())
            && StringUtils.hasLength(request.getShortDescription())
            && StringUtils.hasLength(request.getLongDescription())
            && StringUtils.hasLength(request.getPrice())
            && StringUtils.hasLength(request.getImageUrl())) {

            Product product = getProductById(id);

            product.setName(request.getName());
            product.setBrand(request.getBrand());
            product.setCategory(request.getCategory());
            product.setShortDescription(request.getShortDescription());
            product.setLongDescription(request.getLongDescription());
            product.setPrice(new BigDecimal(request.getPrice()));
            product.setImageUrl(request.getImageUrl());

            return productRepository.save(product);
        }

        throw new InvalidProductRequest("Invalid product request");
    }

    @Override
    public void deleteProduct(String id) {

        Product product = getProductById(id);

        productRepository.delete(product);
    }
}
