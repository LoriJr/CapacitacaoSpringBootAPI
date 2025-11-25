package com.icaro.capacitacao.controller;

import com.icaro.capacitacao.dto.order.OrderRequestDTO;
import com.icaro.capacitacao.dto.order.OrderResponseDTO;
import com.icaro.capacitacao.dto.order.StatusUpdateDTO;
import com.icaro.capacitacao.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody @Valid OrderRequestDTO orderRequestDTO){

        OrderResponseDTO response = service.createOrder(orderRequestDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> find(){
        return ResponseEntity.ok(service.findAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> findById(@PathVariable Long id) {
        OrderResponseDTO response =  service.findByid(id);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        service.deleteOrder(id);
    }

    @PatchMapping("/status/{id}")
    public ResponseEntity<OrderResponseDTO> updateStatus(@PathVariable Long id, @RequestBody StatusUpdateDTO statusUpdateDTO){
        OrderResponseDTO response = service.updateStatus(id, statusUpdateDTO.getOrderStatus());
        return ResponseEntity.ok().body(response);
    }
}
