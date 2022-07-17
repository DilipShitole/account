package com.account.model.response;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
private String id;
	private String firstname;
	private String lastname;
	private String mobile;
	private String email;
	private String account;
	

}
