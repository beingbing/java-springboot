package be.springboot.pp.ecommerce.dto;

import be.springboot.pp.ecommerce.order.OrderStatus;
import lombok.Getter;

@Getter
public class OrderStatusDetails {
    private final OrderStatus orderStatus;
    private final String description;

    public OrderStatusDetails(OrderStatus orderStatus, String description) {
        this.orderStatus = orderStatus;
        this.description = description;
    }
}
