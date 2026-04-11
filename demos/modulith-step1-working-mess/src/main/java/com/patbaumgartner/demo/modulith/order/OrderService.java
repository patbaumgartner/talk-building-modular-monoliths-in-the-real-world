package com.patbaumgartner.demo.modulith.order;

import com.patbaumgartner.demo.modulith.inventory.internal.Stock;
import com.patbaumgartner.demo.modulith.inventory.internal.StockRepository;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
class OrderService {

	private static final Logger log = LoggerFactory.getLogger(OrderService.class);

	// ⚠️ Violation! Crosses module boundary
	private final StockRepository stockRepository;

	private final OrderRepository orderRepository;

	OrderService(StockRepository stockRepository, OrderRepository orderRepository) {
		this.stockRepository = stockRepository;
		this.orderRepository = orderRepository;
	}

	String createOrder(String productId) {
		log.info("Creating order for product: {}", productId);

		// Directly accessing inventory internals — bad practice!
		Stock stock = stockRepository.findByProductId(productId)
			.orElseThrow(() -> new IllegalStateException("Product not in stock: " + productId));

		if (stock.getQuantity() > 0) {
			stock.setQuantity(stock.getQuantity() - 1);
			stockRepository.save(stock);

			String orderId = UUID.randomUUID().toString();

			Order order = new Order();
			order.setOrderId(orderId);
			order.setProductId(productId);
			order.setStatus("CREATED");
			orderRepository.save(order);

			log.info("Order created: {}", orderId);
			return orderId;
		}

		throw new IllegalStateException("Product not in stock: " + productId);
	}

}
