package by.krupenko.ws.productmicroservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateProductDto {

    private String title;
    private BigDecimal price;
    private int quantity;

}
