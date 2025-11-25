package com.icaro.capacitacao.service;

import com.icaro.capacitacao.dto.order.OrderRequestDTO;
import com.icaro.capacitacao.dto.order.OrderResponseDTO;
import com.icaro.capacitacao.dto.order.OrderItemRequestDTO;
import com.icaro.capacitacao.enums.OrderStatus;
import com.icaro.capacitacao.exception.OrderNotFoundException;
import com.icaro.capacitacao.exception.ProductNotFoundException;
import com.icaro.capacitacao.exception.UserNotFoundException;
import com.icaro.capacitacao.mapper.OrderMapper;
import com.icaro.capacitacao.model.OrderEntity;
import com.icaro.capacitacao.model.OrderItem;
import com.icaro.capacitacao.model.ProductEntity;
import com.icaro.capacitacao.model.UserEntity;
import com.icaro.capacitacao.repository.OrderRepository;
import com.icaro.capacitacao.repository.ProductRepository;
import com.icaro.capacitacao.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper mapper;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final EmailNotificationService emailNotification;

    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO requestDTO) {
        UserEntity client = userRepository.findById(requestDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        //cabeçalho do pedido
        OrderEntity order = new OrderEntity();
        order.setUser(client);
        order.setDate(Instant.now());
        order.setOrderStatus(OrderStatus.WAITING_PAYMENT);

        List<OrderItem> items = new ArrayList<>();

        //busca o produto
        for(OrderItemRequestDTO itemDTO : requestDTO.getItems()){
            ProductEntity product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException("Product not found"));

            //criação do item (não pode ser via mapper)
            OrderItem orderItem = new OrderItem();
            orderItem.setPrice(product.getPrice());
            orderItem.setQuantity(itemDTO.getQuantity());

            //associando entidades
            orderItem.setProduct(product);
            orderItem.setOrder(order);

            items.add(orderItem);
        }

        //salva o pedido e os itens em cascata
        order.setItems(items);
        OrderEntity saveOrder = orderRepository.save(order);

        OrderResponseDTO responseDTO = mapper.toDTO(saveOrder);

        String confirmationMessage = emailNotification.sendOrderConfirmation(responseDTO);
        System.out.println("Log de Notificação " + confirmationMessage);

        return responseDTO;
    }

    @Transactional(readOnly = true)
    public List<OrderResponseDTO> findAllOrders() {
        return orderRepository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public OrderResponseDTO findByid(Long id){
        return orderRepository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"))  ;
    }

    @Transactional
    public void deleteOrder(Long id) {
        if(!orderRepository.existsById(id)) throw new OrderNotFoundException("Order not found");
        orderRepository.deleteById(id);
    }

    @Transactional
    public OrderResponseDTO updateStatus(Long id, OrderStatus newStatus) {
        OrderEntity order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        order.setOrderStatus(newStatus);
        Hibernate.initialize(order.getItems());
        orderRepository.save(order);
        return mapper.toDTO(order);
    }
}
