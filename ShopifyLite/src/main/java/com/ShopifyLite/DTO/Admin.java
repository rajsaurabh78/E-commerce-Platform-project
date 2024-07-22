package com.ShopifyLite.DTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ShopifyLite.model.Address;
import com.ShopifyLite.model.Authority;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
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

public class Admin {
	
	private String name;
	@Column(unique = true)
	private String email;
	@JsonProperty(access =JsonProperty.Access.WRITE_ONLY)
	private String password;
	private String phone;
	private LocalDate dob;
	private List<Address> addressList=new ArrayList<>();
	
}
