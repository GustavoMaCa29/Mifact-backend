package com.example.mifact_test.controller;

import com.example.mifact_test.repository.entity.Product;
import com.example.mifact_test.services.interfaces.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list")
    @ApiOperation(value = "Listado de productos", notes = "Obtiene el listado de los productos")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Búsqueda de producto por Id", notes = "Busca un producto por Id")
    public Product findById(@PathVariable("id") Long id) {
        return productService.findById(id);
    }

    @GetMapping("/filter")
    @ApiOperation(value = "Búsqueda de producto por nombre", notes = "Busca un producto por nombre")
    public List<Product> findByName(@RequestParam String name) {
        return productService.findByName(name);
    }

    @PostMapping
    @ApiOperation(value = "Crear producto", notes = "Crea un producto por Id")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        Product newProduct = productService.createProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Editar producto", notes = "Edita un producto por Id")
    public Product updateById(@Valid @RequestBody Product product, @PathVariable("id") Long id) {
        return productService.updateById(product, id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Eliminar producto", notes = "Elimina un producto por Id")
    public void deleteById(@PathVariable("id") Long id) {
        productService.deleteById(id);
    }
}
