package com.example.anibalbenedictoejercicio04.Controller;
import DTO.ProductDTO;
import com.example.anibalbenedictoejercicio04.Entidades.Product;
import com.example.anibalbenedictoejercicio04.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/pagination")
    public ResponseEntity<List<ProductDTO>> getPaginatedProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<ProductDTO> productDTOList = productService.getPaginatedProducts(page, size);
        return ResponseEntity.ok(productDTOList);
    }

    @GetMapping("/search/{query}")
    public ResponseEntity<List<ProductDTO>> getfindByDescription(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @PathVariable String query
    ) {
        List<ProductDTO> productDTOList = productService.getfindByDescription(page, size, query);
        return ResponseEntity.ok(productDTOList);
    }
}
