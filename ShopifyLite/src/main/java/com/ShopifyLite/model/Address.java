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
public class Address {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator ="address_seq")
	@SequenceGenerator(name="address_seq", sequenceName="address_seq",allocationSize=1, initialValue=1)
	private Integer aId;
	private String flatNo;
	private String addressLine1;
	private String addressLine2;
	private String nearBy;
	private String district;
	private String state;
	private String pinCode;
	
	@ManyToOne
	@JsonIgnore
	private Users user;
	
}
