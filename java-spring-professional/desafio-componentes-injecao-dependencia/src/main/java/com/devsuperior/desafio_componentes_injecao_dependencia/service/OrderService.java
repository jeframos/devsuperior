package com.devsuperior.desafio_componentes_injecao_dependencia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.desafio_componentes_injecao_dependencia.entities.Order;

@Service
public class OrderService {

	@Autowired
	ShippingService shippingService;
	
	public double total(Order order) {
		return order.getBasic() + shippingService.shipment(order) - discount(order);
	}
	
	public double discount(Order order) {
		return (order.getBasic() * order.getDiscount()) / 100;
	}
}
