package com.devsuperior.desafio_componentes_injecao_dependencia.service;

import org.springframework.stereotype.Service;

import com.devsuperior.desafio_componentes_injecao_dependencia.entities.Order;

@Service
public class ShippingService {
	
	public double shipment(Order order) {
		double shipping = 0.0; 
		if (order.getBasic() >= 100.0 && order.getBasic() < 200.0) {
			shipping += 12.0; 
		}else if (order.getBasic() < 100.0){
			shipping += 20.0;
		}
		return shipping; 
	}
}
