package com.ShopifyLite.model;

import java.time.LocalDate;

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
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator ="product_seq")
	@SequenceGenerator(name="product_seq", sequenceName="product_seq",allocationSize=1, initialValue=1)
	private Integer pId;
	private String name;
	private String type;
//	private String size;
	private Integer price;
	private LocalDate manufactureDate;
	
}
