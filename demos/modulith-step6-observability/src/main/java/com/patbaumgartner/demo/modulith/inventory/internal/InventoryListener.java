package com.patbaumgartner.demo.modulith.inventory.internal;

import com.patbaumgartner.demo.modulith.order.OrderCompleted;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Component
class InventoryListener {

	private static final Logger log = LoggerFactory.getLogger(InventoryListener.class);

	private final StockRepository stockRepository;

	public InventoryListener(StockRepository stockRepository) {
		this.stockRepository = stockRepository;
	}

	@ApplicationModuleListener
	void on(OrderCompleted event) {
		log.info("Received order completed event: {}", event.orderId());

		// Simulate a failure — the event will be stored in the EVENT_PUBLICATION table
		throw new RuntimeException("Inventory service temporarily unavailable!");
	}

}
