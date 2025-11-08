package com.ewallet.orderservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {
    @NotBlank(message = "customerName is required")
    private String customerName;

    @NotNull(message = "amount is required")
    @Min(value = 1, message = "amount must be at least 1")
    private Double amount;

    @NotBlank(message = "status is required")
    private String status;
}
