package com.example.anibalbenedictoejercicio04.Services;

import com.example.anibalbenedictoejercicio04.DTO.ListadoCompraDTO;
import com.example.anibalbenedictoejercicio04.Entidades.Cart;
import com.example.anibalbenedictoejercicio04.Entidades.Customer;
import com.example.anibalbenedictoejercicio04.Entidades.Product;
import com.example.anibalbenedictoejercicio04.Repositories.CartRepository;
import com.example.anibalbenedictoejercicio04.Repositories.CustomerRepository;
import com.example.anibalbenedictoejercicio04.Repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    @Autowired
    public CartService(CartRepository cartRepository, ProductRepository productRepository, CustomerRepository customerRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }
    public List<ListadoCompraDTO> getCartByCustomerId(int customerId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Cart> cartPage = cartRepository.findCartByCustomerId(pageable, (short) customerId);
        List<ListadoCompraDTO> listadoCarrito = new ArrayList<>();
        for (Cart cart : cartPage.getContent()) {
            listadoCarrito.add(ListadoCompraDTO.fromEntity(cart.getProduct(), cart));
        }
        return listadoCarrito;
    }
        public List<ListadoCompraDTO> addProductToCart (short customerId, short productId, int quantity) {
            Optional<Cart> optionalCart = cartRepository.findCustomerIdProductId( customerId, productId);
       if (optionalCart.isPresent()){
           Cart cart = optionalCart.get();
           cart.setQuantity(cart.getQuantity() + quantity);
       }else{
           Product product = getProductById(productId);
           Customer customer = getCustomerById(customerId);
           Cart nuevoCart=new Cart();
           nuevoCart.setCustomer(customer);
           nuevoCart.setProduct(product);
           nuevoCart.setQuantity(quantity);
           cartRepository.save(nuevoCart);
       }
            return getCartByCustomerId(customerId, 0, 10);
        }

        //metodo ppara obtener productopor id y customerpor id
        private Product getProductById(short productId) {
            Optional<Product> optionalProduct = productRepository.findById(productId);
            return optionalProduct.orElse(null);
        }
        private Customer getCustomerById(short customerId) {
            Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
            return optionalCustomer.orElse(null);
        }
    @Transactional
    public int vaciarCarritoCustomerId(Short customerId) {
        return cartRepository.vaciarCarrito(customerId);
    }
    public ResponseEntity<String> deleteProductFromCart(Short customerId, Short productId) {
        int quantity = cartRepository.getProductQuantity(customerId, productId);
        if(quantity >=1) {
            int filasAfectadas = cartRepository.removeProductFromCart(customerId, productId);
            if (filasAfectadas > 0) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        else {
            int filasAfectadas = cartRepository.deleteProductFromCart(customerId, productId);
            if (filasAfectadas > 0) {
                return ResponseEntity.ok().build();
            }else{
                return ResponseEntity.notFound().build();
            }
        }
    }
    }


