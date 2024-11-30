package br.com.cesarsicas.springstore.domain.order.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateOrder(
        Long addressId,
        Long creditCardId,
        BigDecimal totalAmount,
        LocalDateTime datetime
) {
}
