package com.icaro.capacitacao.repository;


import com.icaro.capacitacao.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
