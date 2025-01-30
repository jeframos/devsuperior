package com.devsuperior.desafio_componentes_injecao_dependencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.devsuperior.desafio_componentes_injecao_dependencia.entities.Order;
import com.devsuperior.desafio_componentes_injecao_dependencia.service.OrderService;

@SpringBootApplication
@ComponentScan({"com.devsuperior.desafio_componentes_injecao_dependencia"})
public class DesafioComponentesInjecaoDependenciaApplication implements CommandLineRunner {

	@Autowired
	private OrderService orderService;
	
	public static void main(String[] args) {
		SpringApplication.run(DesafioComponentesInjecaoDependenciaApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		Order order1 = new Order(1034, 150.0, 20.0);
		Order order2 = new Order(2282, 800.0, 10.0);
		Order order3 = new Order(1309, 95.90, 0.0);
		
		System.out.println("Exemplo 1:");
		System.out.println("Pedido Código: " + order1.getCode());
		System.out.println("Valor Total: R$ " + String.format("%.2f", orderService.total(order1)));
		
		System.out.println("Exemplo 2:");
		System.out.println("Pedido Código: " + order2.getCode());
		System.out.println("Valor Total: R$ " + String.format("%.2f", orderService.total(order2)));
		
		System.out.println("Exemplo 3:");
		System.out.println("Pedido Código: " + order3.getCode());
		System.out.println("Valor Total: R$ " + String.format("%.2f", orderService.total(order3)));
	}
}
