package com.icaro.capacitacao.service;

import com.icaro.capacitacao.dto.product.ProductRequestDTO;
import com.icaro.capacitacao.dto.product.ProductResponseDTO;
import com.icaro.capacitacao.exception.ProductNotFoundException;
import com.icaro.capacitacao.mapper.ProductMapper;
import com.icaro.capacitacao.model.ProductEntity;
import com.icaro.capacitacao.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    @Transactional
    public ProductResponseDTO saveProduct(ProductRequestDTO request){
        ProductEntity entity = mapper.toEntity(request);
        ProductEntity saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDTO> findAllProducts() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public ProductResponseDTO findById(Long id){
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    @Transactional
    public void deleProduct(Long id){
        if(!repository.existsById(id)) throw new ProductNotFoundException("Product not found");
        repository.deleteById(id);
    }

    @Transactional
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO request){
        ProductEntity entity = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found " + id));

        mapper.updateFromDTO(request, entity);
        ProductEntity saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    @Transactional
    public ProductResponseDTO updatePartialProduct(Long id, ProductRequestDTO request){
        ProductEntity entity = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found " + id));

        mapper.updateEntityPatch(request, entity);
        ProductEntity saved = repository.save(entity);
        return mapper.toDTO(saved);
    }
}
