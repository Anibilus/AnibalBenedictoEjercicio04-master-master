package com.example.anibalbenedictoejercicio04.Controller;
import com.example.anibalbenedictoejercicio04.DTO.ProductDTO;
import com.example.anibalbenedictoejercicio04.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/ProductList")
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
