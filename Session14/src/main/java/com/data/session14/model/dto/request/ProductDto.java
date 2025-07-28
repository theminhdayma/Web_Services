package com.data.session14.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;

    @NotBlank(message = "Product name cannot be empty")
    private String productName;

    @NotBlank(message = "Producer cannot be empty")
    private String producer;

    @NotNull(message = "Year marking cannot be empty")
    private int yearMarking;

    @NotNull(message = "Price cannot be empty")
    private Double price;

    @NotNull(message = "Quantity cannot be null")
    private Integer quantity;

    @NotBlank(message = "Expire date cannot be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expireDate;

    @NotNull(message = "Category ID cannot be null")
    private Long categoryId;
}
