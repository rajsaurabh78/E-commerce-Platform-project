package com.ShopifyLite.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
@Document( collection = "Product" )
public class Product {
	@Id
	private Integer pId;
	private String name;
	private String type;
//	private String size;
	private Integer price;
	private LocalDate manufactureDate;
	
}
