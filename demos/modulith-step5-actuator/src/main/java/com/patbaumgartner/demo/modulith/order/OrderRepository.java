package com.patbaumgartner.demo.modulith.order;

import org.springframework.data.repository.CrudRepository;

interface OrderRepository extends CrudRepository<Order, Long> {

}
