package com.speedsneakers.productservice.model.request;

import lombok.Data;

/**
 * Modelo de datos de la solicitud de creación de un producto.
 */
@Data
public class ProductRequest {

    /**
     * Nombre del producto.
     */
    private String name;

    /**
     * Marca del producto.
     */
    private String brand;

    /**
     * Categoría del producto.
     */
    private String category;

    /**
     * Descripción corta del producto.
     */
    private String shortDescription;

    /**
     * Descripción larga del producto.
     */
    private String longDescription;

    /**
     * Precio del producto.
     */
    private String price;

    /**
     * Imagen del producto.
     */
    private String imageUrl;

}
