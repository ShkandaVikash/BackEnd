package com.bankservice.backend.ws.controller;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankservice.backend.ws.exception.CustomeError;
import com.bankservice.backend.ws.model.AddressModel;
import com.bankservice.backend.ws.model.BankServiceModel;

@RestController
@RequestMapping("/api/bankinfo")
@CrossOrigin(origins = "*")
public class BankServiceController {

	
	@PostMapping()
	public BankServiceModel createUser(@RequestBody BankServiceModel bankServiceModel) {
		bankServiceModel.setAccountNumber(ThreadLocalRandom.current().nextInt(1, 1000 + 1));
		return bankServiceModel;
	}

	
	@GetMapping(path = "/addresses")
	public ResponseEntity<AddressModel> validateAddress(AddressModel addressModel) throws Exception {
		validateAddressInfo(addressModel);
		return new ResponseEntity(addressModel,HttpStatus.OK);
	}

	private void validateAddressInfo(AddressModel addressModel) throws Exception {
		CustomeError customeError = new CustomeError();
		if(nullcheck(addressModel.getPinCode())) {
			Pattern p = Pattern.compile("[^0-9]");
			Matcher m = p.matcher(addressModel.getPinCode());
			if (m.find()) {
				//customeError.setErrorMessage("Pin Code should contain only Numbers.");
				throw new Exception("Pin Code should contain only Numbers",customeError);
				//throw customeError;
			}
		}
	}

	
	@GetMapping(path = "/creditcheck/{userName}")
	public ResponseEntity<String> validateCredit(@PathVariable String userName) throws Exception {
		CustomeError customeError = new CustomeError();
		String tempUserName[]= userName.split("-");
		if(Integer.valueOf(tempUserName[1])%2==0) {
			throw new Exception("The Customer cannot be Created for the reasons of overdue",customeError);
			//customeError.setErrorMessage("The Customer cannot be Created for the reasons of overdue");
			
		}
		return new ResponseEntity(userName,HttpStatus.OK);
	}
	
	private boolean nullcheck(String fields) {
		Optional<String> aOptStr = Optional.ofNullable(fields);
		return aOptStr.isPresent();
	}
	
	@GetMapping(path = "/User1")
	public BankServiceModel getUserDetails() throws Exception {
		BankServiceModel bankServiceModel = new BankServiceModel();
		bankServiceModel.setUserName("Bharathi");
		bankServiceModel.setAccountType("Saving Account");
		bankServiceModel.setAutoDebit(true);
		bankServiceModel.setAccountNumber(5060);
		AddressModel addressModel = new AddressModel();
		addressModel.setFlatNo("3/35");
		addressModel.setDistrict("Salem");
		addressModel.setPinCode("600202");
		addressModel.setStreetName("Sanjeevarayan Pettai");
		addressModel.setState("Tamil Nadu");
		bankServiceModel.setAddressModel(addressModel);
		return bankServiceModel;
	}
	
}
