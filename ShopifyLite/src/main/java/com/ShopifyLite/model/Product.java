package com.ShopifyLite.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
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
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator ="product_seq")
	@SequenceGenerator(name="product_seq", sequenceName="product_seq",allocationSize=1, initialValue=1)
	private Integer pid;
	private String name;
	private String type;
	@Lob
	@Column(name = "image", columnDefinition = "MEDIUMBLOB")
    private byte[] image;
	private Integer price;
	private LocalDate manufactureDate;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String sizeQuan;
	
	
	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Quantity> quantityList=new ArrayList<>();
	

	
	
}
