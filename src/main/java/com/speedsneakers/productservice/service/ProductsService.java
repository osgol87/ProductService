package com.speedsneakers.productservice.service;


import com.speedsneakers.productservice.model.pojo.Product;
import com.speedsneakers.productservice.model.request.ProductRequest;

import java.util.List;

/**
 * Interfaz del servicio de productos.
 */
public interface ProductsService {

    /**
     * Obtiene productos que coincidan con los filtros proporcionados.
     */
    List<Product> getProducts(String name, String brand, String category);

    /**
     * Obtiene un producto por su ID.
     */
    Product getProductById(String id);

    /**
     * Crea un nuevo producto.
     */
    Product createProduct(ProductRequest request);

    /**
     * Actualiza un producto existente.
     */
    Product updateProduct(String id, ProductRequest request);

    /**
     * Elimina un producto por su ID.
     */
    void deleteProduct(String id);

}
