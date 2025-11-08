package com.ewallet.orderservice.dto;

import com.ewallet.orderservice.constants.ServiceConstants;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {
    @NotBlank(message = ServiceConstants.CUSTOMER_NAME_REQUIRED)
    private String customerName;

    @NotNull(message = ServiceConstants.AMOUNT_REQUIRED)
    @Min(value = 1, message = ServiceConstants.AMOUNT_MIN)
    private Double amount;

    @NotBlank(message = ServiceConstants.STATUS_REQUIRED)
    private String status;
}
