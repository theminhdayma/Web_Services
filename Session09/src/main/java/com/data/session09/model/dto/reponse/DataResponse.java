package com.data.session09.model.dto.reponse;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataResponse<T> {
    private T data;
    private HttpStatus status;
}
