package com.ShopifyLite.model;

import jakarta.persistence.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator ="order_seq")
	@SequenceGenerator(name="order_seq", sequenceName="order_seq",allocationSize=1, initialValue=1)
	private Integer oId;
	private String totalPrice;
	
}
