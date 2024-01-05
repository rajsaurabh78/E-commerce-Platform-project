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
public class Quantity {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator ="quantity_seq")
	@SequenceGenerator(name="quantity_seq", sequenceName="quantity_seq",allocationSize=1, initialValue=1)
	private Integer qId;
	private Integer quantity;
}
