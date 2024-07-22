package com.ShopifyLite.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDTO {
	
	private Integer userId;
	private String name;
	private String email;
	private String password;
	private String phone;
	private LocalDate dob;

}
