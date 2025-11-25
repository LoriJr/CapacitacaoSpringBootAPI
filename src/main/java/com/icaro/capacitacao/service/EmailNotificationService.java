package com.icaro.capacitacao.service;

import com.icaro.capacitacao.dto.order.OrderResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService {

    public String sendOrderConfirmation(OrderResponseDTO order){

        String fileContent = buildEmailContent(order);

        System.out.println("--- INICIO DO ARQUIVO DE COMPRA ---");
        System.out.println(fileContent);
        System.out.println("--- FIM DO ARQUIVO DE COMPRA ---");

        String clientEmail = order.getUser().getEmail();
        return "Envio de email realizado para: " + clientEmail;

    }

    private String buildEmailContent(OrderResponseDTO order){
        StringBuilder content = new StringBuilder();
        content.append("Detalhes da Compra #").append(order.getId()).append("\n");
        content.append("Cliente: ").append(order.getUser().getUsername()).append("\n");
        content.append("Data: ").append(order.getDate()).append("\n");
        content.append("Status: ").append(order.getOrderStatus()).append("\n");
        content.append("Total: R$ ").append(order.getTotalValue()).append("\n\n");
        content.append("ITENS:\n");

        order.getItems().forEach(item ->{
            content.append("- ").append(item.getProductName())
                    .append(" (x").append(item.getQuantity())
                    .append(" @ R$ ").append(item.getPrice()).append(")\n");
        });
        return content.toString();
    }
}
