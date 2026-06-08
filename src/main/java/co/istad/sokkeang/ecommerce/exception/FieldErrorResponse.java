package co.istad.sokkeang.ecommerce.exception;

import lombok.Builder;

@Builder
public record FieldErrorResponse(
        String field,
        String message
) {
}
