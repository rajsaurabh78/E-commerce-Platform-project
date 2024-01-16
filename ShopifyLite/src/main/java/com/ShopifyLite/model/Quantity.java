package com.ShopifyLite.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
public class Quantity {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator ="quantity_seq")
	@SequenceGenerator(name="quantity_seq", sequenceName="quantity_seq",allocationSize=1, initialValue=1)
	private Integer qId;
	private Integer total;
	
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "pId")
    private Product product;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "sId")
    private Size size;

}
