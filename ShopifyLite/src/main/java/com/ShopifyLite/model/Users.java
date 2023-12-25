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
@Document( collection = "User" )
public class Users {
	@Id
	private Integer userId;
	private String name;
	private String email;
	private String password;
	private String phone;
	private LocalDate dob;
	private Float amount;
	
}
