package com.patbaumgartner.demo.modulith.inventory.internal;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface StockRepository extends CrudRepository<Stock, Long> {

	Optional<Stock> findByProductId(String productId);

}
