package com.account.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.account.model.request.AccontModel;
import com.account.model.response.AccountDetails;
import com.account.model.response.AccountSummary;
import com.account.model.response.Customer;
import com.account.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${customer_details_byid}")
	private String customer_by_id_url;

	public AccontModel saveAccount(AccontModel accontModel) {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:MM:ss");
		String requiredDate = df.format(new Date(System.currentTimeMillis()));
		accontModel.setDate(requiredDate);
		accountRepository.save(accontModel);
		return accontModel;
	}

	public List<AccontModel> getAccountdetails() {
		List<AccontModel> accountmodel1 = accountRepository.findAll();
		return accountmodel1;
	}

	public AccontModel updateAccount(String account, AccontModel accontModel) {
		Optional<AccontModel> accontModel1 = accountRepository.findById(account);
		AccontModel accontModel2 = accontModel1.get();
		accontModel2.setAccount(accontModel.getAccount());
		accontModel2.setBalance(accontModel.getBalance());
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:MM:ss");
		String requiredDate = df.format(new Date(System.currentTimeMillis()));
		accontModel2.setDate(requiredDate);
		accountRepository.save(accontModel2);

		return accontModel2;
	}

	public String deleteById(String account_no) {
		accountRepository.deleteById(account_no);
		return "deleted";
	}
	public AccountSummary findAccount(String account_id) {
		Optional<AccontModel> acc=accountRepository.findById(account_id);
		AccontModel acc1=acc.get();
		HttpHeaders header=new HttpHeaders();
	   HttpEntity<Customer> entity=new HttpEntity<Customer>(header);
	   Map<String, String> vars = new HashMap<>();
	   vars.put("account",account_id);
	   ResponseEntity<Customer> cus=restTemplate.exchange(customer_by_id_url,HttpMethod.GET,entity,Customer.class,vars);
	   Customer customer=cus.getBody();
	   System.out.println("customer body  "+customer.toString());
	   AccountSummary accSummary=new AccountSummary();
	   AccountDetails accDetails=new AccountDetails();
	   accDetails.setAccount_no(acc1.getAccount());
	   accDetails.setAccount_type(acc1.getAccount_type());
	   accDetails.setBalance(acc1.getBalance());
	   accDetails.setDate(acc1.getDate());
	   accSummary.setAccountDetails(accDetails);
	   accSummary.setCustomer(customer);
	   return accSummary;
	}
	
	
	public String updateAccountBalance(String account, Float balance) {
		Optional<AccontModel> accountDetails=accountRepository.findById(account);
		AccontModel acc1=accountDetails.get();
		acc1.setBalance(balance);
		accountRepository.save(acc1);
		return "account updated successFully";
	}
	
	
}
