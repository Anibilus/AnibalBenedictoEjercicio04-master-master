package com.example.anibalbenedictoejercicio04.Services;
import com.example.anibalbenedictoejercicio04.DTO.ProductDTO;
import com.example.anibalbenedictoejercicio04.Entidades.Product;
import com.example.anibalbenedictoejercicio04.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.data.domain.Pageable;
@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> getPaginatedProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findPaginatedProducts(pageable);
        List<ProductDTO> productDTOList = productPage.getContent()
                .stream()
                .map(ProductDTO::fromEntity)
                .collect(Collectors.toList());

        return productDTOList;
    }
    public List<ProductDTO> getfindByDescription(int page, int size, String query) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findByDescription(pageable, query);
        List<ProductDTO> productDTOList = productPage.getContent()
                .stream()
                .map(ProductDTO::fromEntity)
                .collect(Collectors.toList());
        return productDTOList;
    }
}



