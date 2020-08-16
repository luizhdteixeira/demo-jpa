package com.luizhdteixeira.demojpa.resource;

import com.luizhdteixeira.demojpa.domain.ProductEntity;
import com.luizhdteixeira.demojpa.domain.repositories.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "products")
public class ProductResource {

    private final ProductRepository repository;

    public ProductResource(ProductRepository repository) {
        this.repository = repository;
    }

    /**
     * Create
     *
     * @RequestBody ProductEntity {name, value, quantity}
     * @PostMapping {localhost:8080/products/}
     *
     */

    @PostMapping("/")
    public ProductEntity createProduct(@RequestBody ProductEntity productEntity) {
        return repository.save(productEntity);
    }

    /**
     *  RetrieveAll
     *
     *  @GetMapping {localhost:8080/products/}
     *
     */

    @GetMapping("/")
    public List<ProductEntity> retrieveProducts() {
        return repository.findAll();
    }

    /**
     * Retrieve By Uuid
     *
     * @PathVariable {uuid}
     * @GetMapping {localhost:8080/products/{uuid}/product}
     *
     */

    @GetMapping("/{uuid}/product")
    public Optional<ProductEntity> retrieveProduct(@PathVariable UUID uuid) {
        return repository.findById(uuid);
    }

    /**
     * Update
     *
     * @PathVariable {uuid}
     * @RequestBody ProductEntity {uuid, name, value, quantity, createdDate}
     * @PutMapping {localhost:8080/products/{uuid}/product}
     *
     */

    @PutMapping("/{uuid}/product")
    public ProductEntity updateProduct(@PathVariable UUID uuid, @RequestBody ProductEntity productEntity) {
        if (repository.existsById(uuid))
           return repository.save(productEntity);
        return productEntity;
    }

    /**
     * Delete
     *
     * @PathVariable {uuid}
     * @DeleteMapping {localhost:8080/products/{uuid}/product}
     */

    @DeleteMapping("/{uuid}/product")
    public String deleteProduct(@PathVariable UUID uuid) {
        repository.deleteById(uuid);
        return "Delete register: " + uuid.toString();
    }

    /**
     * Retrieve By Name
     *
     * @PathVariable {name}
     * @GetMapping {localhost:8080/products/product}
     *
     */

    @GetMapping("/product")
    public Optional<ProductEntity> retrieveProduct(@RequestParam String name) {
        return repository.findByNameLiked(name);
    }
}
