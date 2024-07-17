package com.ShopifyLite.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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
@Table(name = "User")
public class Users {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator ="user_seq")
	@SequenceGenerator(name="user_seq", sequenceName="user_seq",allocationSize=1, initialValue=1)
	private Integer userId;
	
	private String name;
	@Column(unique = true)
	private String email;
	@JsonProperty(access =JsonProperty.Access.WRITE_ONLY)
	private String password;
	private String phone;
	private LocalDate dob;
	private Double amount;
	
	@ManyToOne
	@JsonIgnore
	private Cart cart;
	
	@ManyToOne
	@JsonIgnore
	private Order order;
	@JsonProperty(access =JsonProperty.Access.WRITE_ONLY)
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Authority> authorities=new ArrayList<>();
	
	@OneToMany(mappedBy="user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Address> addressList=new ArrayList<>();
	
}
