package com.bankservice.backend.ws.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Component
public class BankServiceModel {
	
	@Autowired
	AddressModel addressModel;
	private String userName;
	private String accountType;
	private boolean autoDebit ;
	private Integer accountNumber;

}
