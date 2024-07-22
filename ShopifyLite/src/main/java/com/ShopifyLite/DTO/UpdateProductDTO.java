package com.ShopifyLite.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductDTO {
	
	private Integer pid;
	private String name;
	private String type;
	private Integer price;
	private LocalDate manufactureDate;
	private String sizeQuan;
}
