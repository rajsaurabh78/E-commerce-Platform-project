package com.ShopifyLite.model;



import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class ProductDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator ="ProductDetails_seq")
	@SequenceGenerator(name="ProductDetails_seq", sequenceName="ProductDetails_seq",allocationSize=1, initialValue=1)
	private Integer cdid;
	private Integer pid;
	private Integer amount;
	private Integer quantity;
	private String Size;
	
	
	@ManyToOne
	@JsonIgnore
	private Cart cart;
	
}
