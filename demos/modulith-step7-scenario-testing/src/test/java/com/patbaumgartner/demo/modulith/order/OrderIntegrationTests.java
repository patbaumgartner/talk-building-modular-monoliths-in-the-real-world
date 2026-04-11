package com.patbaumgartner.demo.modulith.order;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.Scenario;

@ApplicationModuleTest
class OrderIntegrationTests {

	@Autowired
	OrderService orderService;

	@Test
	void shouldPublishOrderCompletedEvent(Scenario scenario) {
		scenario.stimulate(() -> orderService.createOrder("PROD-001"))
			.andWaitForEventOfType(OrderCompleted.class)
			.matching(event -> "PROD-001".equals(event.productId()))
			.toArriveAndVerify(event -> {
				assertThat(event.orderId()).isNotNull();
				assertThat(event.productId()).isEqualTo("PROD-001");
			});
	}

}
