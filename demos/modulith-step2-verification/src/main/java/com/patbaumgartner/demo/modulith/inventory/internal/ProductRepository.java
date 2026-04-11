package com.patbaumgartner.demo.modulith.inventory.internal;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

interface ProductRepository extends CrudRepository<Product, Long> {

	Optional<Product> findByProductId(String productId);

}
