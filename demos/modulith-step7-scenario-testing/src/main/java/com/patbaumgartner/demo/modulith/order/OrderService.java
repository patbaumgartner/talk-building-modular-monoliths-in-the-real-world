package com.patbaumgartner.demo.modulith.order;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
class OrderService {

	private static final Logger log = LoggerFactory.getLogger(OrderService.class);

	private final ApplicationEventPublisher events;

	private final OrderRepository orderRepository;

	OrderService(ApplicationEventPublisher events, OrderRepository orderRepository) {
		this.events = events;
		this.orderRepository = orderRepository;
	}

	String createOrder(String productId) {
		log.info("Creating order for product: {}", productId);

		String orderId = UUID.randomUUID().toString();

		Order order = new Order();
		order.setOrderId(orderId);
		order.setProductId(productId);
		order.setStatus("CREATED");
		orderRepository.save(order);

		log.info("Order created: {}", orderId);

		events.publishEvent(new OrderCompleted(orderId, productId));

		return orderId;
	}

}
