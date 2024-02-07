package DTO;

import com.example.anibalbenedictoejercicio04.Entidades.Customer;
import com.example.anibalbenedictoejercicio04.Entidades.Product;
import com.example.anibalbenedictoejercicio04.Entidades.Wishlist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import com.example.anibalbenedictoejercicio04.Entidades.Customer;
import com.example.anibalbenedictoejercicio04.Entidades.Wishlist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class WishlistDTO {
    private short whislistId;
    private String whislistName;
    private Customer customer;
    private List<ProductDTO> products;

    public static WishlistDTO fromEntity(Wishlist wishlist) {
        List<ProductDTO> productDTOs = wishlist.getProducts().stream()
                .map(ProductDTO::fromEntity)
                .collect(Collectors.toList());

        return new WishlistDTO(
                wishlist.getWhislistId(),
                wishlist.getWhislistName(),
                wishlist.getCustomer(),
                productDTOs
        );
    }

    public WishlistDTO() {
    }
}

