package com.ShopifyLite.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Size {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator ="Size_seq")
	@SequenceGenerator(name="Size_seq", sequenceName="Size_seq",allocationSize=1, initialValue=1)
	private Integer sId;
	@Column(unique = true)
	private String type;
	
}
