package com.speedsneakers.productservice.repository;

import com.speedsneakers.productservice.model.pojo.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para manejar las operaciones CRUD de productos.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Busca productos que coincidan con los filtros proporcionados.
     */
    @Query("SELECT p FROM Product p WHERE " +
            "(:name IS NULL OR :name = '' OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:brand IS NULL OR :brand = '' OR LOWER(p.brand) LIKE LOWER(CONCAT('%', :brand, '%'))) AND " +
            "(:category IS NULL OR :category = '' OR LOWER(p.category) LIKE LOWER(CONCAT('%', :category, '%')))")
    List<Product> searchProducts(@Param("name") String name,
                                 @Param("brand") String brand,
                                 @Param("category") String category);
}
