package com.data.session05.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FruitDTO {
    private Long id;
    private String name;
    private Double price;
    private Boolean status;
}
