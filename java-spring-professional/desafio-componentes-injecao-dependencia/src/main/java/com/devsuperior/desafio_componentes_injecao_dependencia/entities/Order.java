package com.devsuperior.desafio_componentes_injecao_dependencia.entities;

public class Order {
	
	private Integer code;
	private Double basic;
	private Double discount;

	public Order() {}
	
	public Order(Integer code, double basic, double discount) {
		this.code = code;
		this.basic = basic;
		this.discount = discount;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public double getBasic() {
		return basic;
	}

	public void setBasic(double basic) {
		this.basic = basic;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
}
