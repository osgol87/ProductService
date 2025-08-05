package com.speedsneakers.productservice.service;

import com.speedsneakers.productservice.exception.IllegalProductIdException;
import com.speedsneakers.productservice.exception.InvalidProductRequest;
import com.speedsneakers.productservice.exception.ProductNotFoundException;
import com.speedsneakers.productservice.model.dto.ProductDto;
import com.speedsneakers.productservice.model.entity.Product;
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
     *
     * Return List<Product> Lista de productos que coinciden con los filtros.
     */
    @Override
    public List<ProductDto> getProducts(String name, String brand, String category) {

        if (StringUtils.hasLength(name)
            || StringUtils.hasLength(brand)
            || StringUtils.hasLength(category)) {

            return productRepository.searchProducts(name, brand, category).stream().map(product ->
                new ProductDto(
                        product.getId(),
                        product.getName(),
                        product.getBrand(),
                        product.getCategory(),
                        product.getShortDescription(),
                        product.getLongDescription(),
                        product.getPrice(),
                        product.getImageUrl()
                )
            ).toList();
        }

        return productRepository.findAll().stream().map(product ->
                new ProductDto(
                    product.getId(),
                    product.getName(),
                    product.getBrand(),
                    product.getCategory(),
                    product.getShortDescription(),
                    product.getLongDescription(),
                    product.getPrice(),
                    product.getImageUrl()
            )
        ).toList();
    }

    /**
     * Obtiene un producto por su ID.
     * Return Product Producto con el ID proporcionado.
     */
    @Override
    public ProductDto getProductById(String id) {

        Product product = findProductById(id);

        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getBrand(),
                product.getCategory(),
                product.getShortDescription(),
                product.getLongDescription(),
                product.getPrice(),
                product.getImageUrl()
        );
    }

    /**
     * Crea un nuevo producto.
     * Return Product Producto creado.
     */
    @Override
    public ProductDto createProduct(ProductRequest request) {

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

            productRepository.save(product);

            return new ProductDto(
                    product.getId(),
                    product.getName(),
                    product.getBrand(),
                    product.getCategory(),
                    product.getShortDescription(),
                    product.getLongDescription(),
                    product.getPrice(),
                    product.getImageUrl()
            );
        }

        throw new InvalidProductRequest("Invalid product request");
    }

    @Override
    public ProductDto updateProduct(String id, ProductRequest request) {

        if (request != null
            && StringUtils.hasLength(request.getName())
            && StringUtils.hasLength(request.getBrand())
            && StringUtils.hasLength(request.getCategory())
            && StringUtils.hasLength(request.getShortDescription())
            && StringUtils.hasLength(request.getLongDescription())
            && StringUtils.hasLength(request.getPrice())
            && StringUtils.hasLength(request.getImageUrl())) {

            Product product = findProductById(id);

            product.setName(request.getName());
            product.setBrand(request.getBrand());
            product.setCategory(request.getCategory());
            product.setShortDescription(request.getShortDescription());
            product.setLongDescription(request.getLongDescription());
            product.setPrice(new BigDecimal(request.getPrice()));
            product.setImageUrl(request.getImageUrl());

            productRepository.save(product);

            return new ProductDto(
                    product.getId(),
                    product.getName(),
                    product.getBrand(),
                    product.getCategory(),
                    product.getShortDescription(),
                    product.getLongDescription(),
                    product.getPrice(),
                    product.getImageUrl()
            );
        }

        throw new InvalidProductRequest("Invalid product request");
    }

    /**
     * Elimina un producto por su ID.
     *
     * @param id Identificador del producto a eliminar.
     *
     * @throws ProductNotFoundException Si no se encuentra el producto.
     */
    @Override
    public void deleteProduct(String id) {

        productRepository.delete(findProductById(id));
    }

    /**
     * Busca un producto por su ID.
     *
     * @param id Identificador del producto.
     *
     * @return Producto encontrado.
     *
     * @throws ProductNotFoundException Si no se encuentra el producto.
     */
    private Product findProductById(String id) {

        if (!StringUtils.hasLength(id)) {
            throw new IllegalProductIdException(id);
        }

        return productRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
}
