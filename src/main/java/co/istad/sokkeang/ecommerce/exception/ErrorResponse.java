package co.istad.sokkeang.ecommerce.exception;

import lombok.Builder;

@Builder
public record ErrorResponse<T>(
        Boolean status,
        Integer code,
        String message,
        T errors
) {

}