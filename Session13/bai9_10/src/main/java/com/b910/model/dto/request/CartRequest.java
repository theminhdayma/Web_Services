package com.b910.model.dto.request;

import lombok.Data;

@Data
public class CartRequest {
    private Long productId;
    private Integer quantity;
}
