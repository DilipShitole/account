package com.account.model.request;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class AccontModel {
	@MongoId
	private String account;
	private Float balance;
	private String account_type;
	private String date;
	
}
