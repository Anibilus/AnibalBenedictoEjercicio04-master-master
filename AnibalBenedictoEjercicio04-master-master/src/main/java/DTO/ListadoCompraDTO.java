package DTO;
import com.example.anibalbenedictoejercicio04.Entidades.Product;
import com.example.anibalbenedictoejercicio04.Entidades.Cart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ListadoCompraDTO {
    private String nameCustomer;
    private short id;
    private String description;
    private String sku;
    private BigDecimal price;
    private int stock;
    private int quantity;

    public static ListadoCompraDTO fromEntity(Product product, Cart cart) {
        return new ListadoCompraDTO(
                cart.getCustomer().getFirstName(),
                product.getProductId(),
                product.getDescription(),
                product.getSku(),
                product.getPrice(),
                product.getStock(),
                cart.getQuantity()
        );
    }
}
