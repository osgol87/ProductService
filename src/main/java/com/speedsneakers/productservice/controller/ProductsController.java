package com.speedsneakers.productservice.controller;

import com.speedsneakers.productservice.model.pojo.Product;
import com.speedsneakers.productservice.model.request.ProductRequest;
import com.speedsneakers.productservice.service.ProductsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/products")
public class ProductsController {

    /**
     * Servicio de productos.
     */
    private final ProductsService productsService;

    /**
     * Constructor del controlador de productos.
     *
     * @param productsService Servicio de productos.
     */
    @Autowired
    public ProductsController(ProductsService productsService) {

        this.productsService = productsService;
    }

    /**
     * Obtiene productos que coincidan con los filtros proporcionados.
     * @param name El nombre del producto.
     * @param brand La marca del producto.
     * @param category La categoría del producto.
     * @return La respuesta HTTP con la lista de productos.
     */
    @GetMapping
    public ResponseEntity<List<Product>> getProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String category) {

        List<Product> products = productsService.getProducts(name, brand, category);
        return ResponseEntity.ok(products);
    }

    /**
     * Obtiene un producto por su ID.
     * @param id El ID del producto a obtener.
     * @return La respuesta HTTP con el producto obtenido.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable String id) {

        Product product = productsService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    /**
     * Inserta un producto
     * @param request La solicitud de creación del producto.
     * @return La respuesta HTTP con el producto creado.
     */
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody ProductRequest request) {

        Product product = productsService.createProduct(request);

        if (product == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    /**
     * Modifica un producto existente.
     * @param id El ID del producto a modificar.
     * @param request La solicitud de modificación del producto.
     * @return La respuesta HTTP con el producto modificado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody ProductRequest request) {

        Product product = productsService.updateProduct(id, request);
        return ResponseEntity.ok(product);

    }

    /**
     * Elimina un producto por su ID.
     *
     * @param id El ID del producto a eliminar.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {

        productsService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

}
