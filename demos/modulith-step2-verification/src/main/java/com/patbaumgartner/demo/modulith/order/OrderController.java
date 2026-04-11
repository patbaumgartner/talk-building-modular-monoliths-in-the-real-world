package com.patbaumgartner.demo.modulith.order;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
class OrderController {

	private final OrderService orderService;

	OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping
	String createOrder(@RequestParam(defaultValue = "PROD-001") String productId) {
		String orderId = orderService.createOrder(productId);
		return "Order created: " + orderId;
	}

}
