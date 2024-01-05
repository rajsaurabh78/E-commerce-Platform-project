package com.ShopifyLite.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Id;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
public class Users {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator ="user_seq")
	@SequenceGenerator(name="user_seq", sequenceName="user_seq",allocationSize=1, initialValue=1)

	private Integer userId;
	private String name;
	private String email;
	private String password;
	private String phone;
	private LocalDate dob;
	private Float amount;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Authority> authorities=new ArrayList<>();
	
}
