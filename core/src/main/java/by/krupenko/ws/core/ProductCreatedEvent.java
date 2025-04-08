package by.krupenko.ws.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductCreatedEvent {

    private String productId;
    private String title;
    private BigDecimal price;
    private int quantity;

}
