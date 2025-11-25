package com.icaro.capacitacao.repository;

import com.icaro.capacitacao.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
