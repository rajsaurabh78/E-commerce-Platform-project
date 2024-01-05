package com.ShopifyLite.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Authority {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator ="authority_seq")
	@SequenceGenerator(name="authority_seq", sequenceName="authority_seq",allocationSize=1, initialValue=500)
	private Integer id;
	
	private String name;
	
	@ManyToOne
	@JsonIgnore
	private Users user;

	
}
