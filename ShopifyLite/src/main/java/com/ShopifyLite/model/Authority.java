package com.ShopifyLite.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Document(collation = "Role")
public class Authority {
	@Id
	private Integer id;
	
	private String name;
	
//	@ManyToOne
	@JsonIgnore
	private Users user;

	
}
