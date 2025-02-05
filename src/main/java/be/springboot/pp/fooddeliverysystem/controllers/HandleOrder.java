package be.springboot.pp.fooddeliverysystem.controllers;

import be.springboot.pp.fooddeliverysystem.dtos.Order;
import be.springboot.pp.fooddeliverysystem.dtos.PaymentResponse;
import be.springboot.pp.fooddeliverysystem.enums.OrderStatus;
import be.springboot.pp.fooddeliverysystem.enums.PaymentStatus;
import be.springboot.pp.fooddeliverysystem.managers.OrderManager;
import be.springboot.pp.fooddeliverysystem.managers.UserManager;
import be.springboot.pp.fooddeliverysystem.managers.payment.PaymentFactory;
import be.springboot.pp.fooddeliverysystem.managers.payment.PaymentManager;
import be.springboot.pp.fooddeliverysystem.pojos.User;
import org.springframework.util.ObjectUtils;

import java.util.Map;

public class HandleOrder {

    private final UserManager userManager = new UserManager();
    private final OrderManager orderManager = new OrderManager();

    public Order placeOrder(String userToken, Map<String, String> paymentInfo, String paymentMode) {
        // validations
        if (userToken == null || userToken.isEmpty()) throw new IllegalArgumentException("put in valid food-item id and user-token");

        User user = userManager.getByToken(userToken);
        if (user == null) throw new IllegalArgumentException("no user found associated with provided token");

        PaymentManager paymentManager = PaymentFactory.getPaymentManager(paymentMode, paymentInfo);
        PaymentResponse response = paymentManager.executePayment();

        if (response == null || ObjectUtils.isEmpty(response.getStatus()) || response.getStatus().equals(PaymentStatus.FAILED))
            throw new RuntimeException("payment failed!!");

        return orderManager.placeOrder(user);
    }

    public boolean updateOrder(Long orderId, OrderStatus status, String userToken) {
        // validations ...

        User user = userManager.getByToken(userToken);
        // validate user ...

        Order order = orderManager.getOrder(orderId);
        // validate order ...

        return false;
    }
}
