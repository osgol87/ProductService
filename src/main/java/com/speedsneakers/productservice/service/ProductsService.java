package com.speedsneakers.productservice.service;


import com.speedsneakers.productservice.model.dto.ProductDto;
import com.speedsneakers.productservice.model.entity.Product;
import com.speedsneakers.productservice.model.request.ProductRequest;

import java.util.List;

/**
 * Interfaz del servicio de productos.
 */
public interface ProductsService {

    /**
     * Obtiene productos que coincidan con los filtros proporcionados.
     */
    List<ProductDto> getProducts(String name, String brand, String category);

    /**
     * Obtiene un producto por su ID.
     */
    ProductDto getProductById(String id);

    /**
     * Crea un nuevo producto.
     */
    ProductDto createProduct(ProductRequest request);

    /**
     * Actualiza un producto existente.
     */
    ProductDto updateProduct(String id, ProductRequest request);

    /**
     * Elimina un producto por su ID.
     */
    void deleteProduct(String id);

}
