package com.icaro.capacitacao.repository;

import com.icaro.capacitacao.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
