package com.ShopifyLite.model;

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
@Document( collection = "Address" )
public class Address {
	@Id
	private Integer aId;
	private String flatNo;
	private String addressLine1;
	private String addressLine2;
	private String nearBy;
	private String district;
	private String state;
	private String pinCode;
	
}
