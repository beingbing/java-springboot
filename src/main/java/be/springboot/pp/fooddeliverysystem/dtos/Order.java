package be.springboot.pp.fooddeliverysystem.dtos;

import be.springboot.pp.fooddeliverysystem.enums.OrderStatus;
import be.springboot.pp.fooddeliverysystem.pojos.CartItem;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class Order {
    private final List<CartItem> cartItems;
    private final Long orderId;
    private final Long userId;
    private final OrderStatus orderStatus;
}
