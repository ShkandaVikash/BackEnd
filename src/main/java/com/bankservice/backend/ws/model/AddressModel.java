package com.bankservice.backend.ws.model;

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
public class AddressModel {

	private String flatNo;
	private String streetName;
	private String district;
	private String state;
	private String pinCode;
	
}
