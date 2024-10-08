package com.hetpatel.product_service.controller;

import com.hetpatel.product_service.dto.ProductDto;
import com.hetpatel.product_service.model.Product;
import com.hetpatel.product_service.service.ProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Slf4j
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/healthcheck")
    public String healthcheck() {
        return "OK";
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        log.info("GET request to fetch all users");
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{prodId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long prodId){
        log.info("GET request to fetch product by ID {}", prodId);
        return new ResponseEntity<>(productService.getProduct(prodId),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDto> addProduct(@Valid @RequestBody ProductDto productDto){
        log.info("POST request to add a product");
        return new ResponseEntity<>(productService.addProduct(productDto),HttpStatus.OK);
    }

    @PutMapping("/{prodId}")
    public ResponseEntity<ProductDto> updateProduct(
            @Valid @RequestBody ProductDto productDto,
            @PathVariable Long prodId){
        log.info("PUT request to update a product");
        return new ResponseEntity<>(productService.updateProduct(productDto, prodId),HttpStatus.OK);
    }

    @DeleteMapping("/{prodId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long prodId){
        log.info("DELETE request to remove a product");
        productService.deleteProduct(prodId);
        return ResponseEntity.noContent().build();
    }

//    @PutMapping("/{prodId}/adjustInventory")
//    public ResponseEntity<ProductDto> adjustInventory(
//            @PathVariable Long prodId,
//            @RequestParam int adjustment) {
//        ProductDto updatedProduct = productService.adjustInventory(prodId, adjustment);
//        return ResponseEntity.ok(updatedProduct);
//    }


}
