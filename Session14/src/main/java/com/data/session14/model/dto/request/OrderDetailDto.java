package com.data.session14.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDto {
    private Long id;

    @NotNull(message = "Order ID cannot be empty")
    private Long orderId;

    @NotNull(message = "Product ID cannot be empty")
    private Long productId;

    @NotNull(message = "Price cannot be empty")
    private Double price;

    @NotNull(message = "Quantity cannot be empty")
    private Integer quantity;
}
