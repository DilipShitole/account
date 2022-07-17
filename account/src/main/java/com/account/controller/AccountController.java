package com.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.account.model.request.AccontModel;
import com.account.model.response.AccountSummary;
import com.account.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	AccountService accountService;
	
	@GetMapping("/{account}")
	public ResponseEntity<AccountSummary> allCustomer(@PathVariable("account") String account_id) {
		AccountSummary account = accountService.findAccount(account_id);
		return new ResponseEntity<AccountSummary>(account, HttpStatus.OK);}
	
	@PostMapping("/save")
	private ResponseEntity<AccontModel> saveAccount(@RequestBody AccontModel accontModel){
		AccontModel accontModel1=accountService.saveAccount(accontModel);
		return new ResponseEntity<AccontModel>(accontModel1, HttpStatus.OK);
	}
	
	
	@GetMapping("/getAccountdetails")
	private ResponseEntity<List<AccontModel>> getAccountdetails(){
		List<AccontModel> account=accountService.getAccountdetails();
		return new ResponseEntity<List<AccontModel>>(account, HttpStatus.OK);
	}
	
	@PutMapping("/update/{account}")
	private ResponseEntity<AccontModel> updateAccount(@PathVariable String account,@RequestBody AccontModel accontModel ){
		AccontModel accontModel1=accountService.updateAccount(account,accontModel);
		return new ResponseEntity<AccontModel>(accontModel1, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/delete/{account_no}")
	private ResponseEntity<String> deleteById(@PathVariable String account_no){
		String message=accountService.deleteById(account_no);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	@PutMapping("/updatebalance/{account}/{balance}")
	public ResponseEntity<String> updateAccountBalance(@PathVariable String account, @PathVariable Float balance){
		String cust=accountService.updateAccountBalance(account,balance);
		return new ResponseEntity<String>(cust,HttpStatus.OK);
	}
	
	

}
