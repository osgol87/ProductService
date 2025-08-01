package com.speedsneakers.productservice.model.pojo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Modelo de datos del producto
 */
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

    /**
     * Identificador del producto
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre del producto
     */
    @Column(name = "name")
    private String name;

    /**
     * Marca del producto
     */
    @Column(name = "brand")
    private String brand;

    /**
     * Categoría del producto
     */
    @Column(name = "category")
    private String category;

    /**
     * Descripción corta del producto
     */
    @Column(name = "shortDescription")
    private String shortDescription;

    /**
     * Descripción larga del producto
     */
    @Column(name = "longDescription")
    private String longDescription;

    /**
     * Precio del producto
     */
    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    /**
     * Imagen del producto
     */
    @Column(name = "imageUrl")
    private String imageUrl;
}
