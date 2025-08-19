package com.acropolis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acropolis.entity.ExpenseLimiters;
import com.acropolis.entity.Transaction;
import com.acropolis.entity.Users;
import com.acropolis.models.ExpenseLimiterModel;
import com.acropolis.models.SearchModel;
import com.acropolis.models.SummaryModel;
import com.acropolis.models.TransactionModel;
import com.acropolis.repositories.TransactionRepository;
import com.acropolis.services.ExpenseLimitersService;
import com.acropolis.services.TransactionService;
import com.acropolis.services.UserService;
import com.acropolis.util.APIResponse;

@CrossOrigin
@RestController
@RequestMapping("/auth/cust")
public class CustomerController {
	
	@Autowired
	private TransactionService tser;
	@Autowired
	private ExpenseLimitersService exser;
	
	@Autowired
	private UserService userv;

	@GetMapping("/getsummary")
	public ResponseEntity<APIResponse> getSummary(){
		SummaryModel summary=userv.getSummary();
		if(summary!=null)
			return ResponseEntity.ok(new APIResponse("Details fetched...",true,summary));
		else
			return ResponseEntity.ok(new APIResponse("Failed to fetch",false,null));
	}
	
	@PostMapping("/save")
	public ResponseEntity<APIResponse> save(@RequestBody TransactionModel model){
		Transaction tr= tser.save(model);
		if(tr!=null)
			return ResponseEntity.ok(new APIResponse("Transacation Saved...", true, tr));
		else
			return ResponseEntity.ok(new APIResponse("Transacation Could not be Saved...", false, null));
	}
	
	@GetMapping("/gettrans/{id}")
	public ResponseEntity<APIResponse> get(@PathVariable("id") int id){
		Transaction tr= tser.get(id);
		if(tr!=null)
			return ResponseEntity.ok(new APIResponse("Transacation found..", true, tr));
		else
			return ResponseEntity.ok(new APIResponse("Transacation not found...", false, null));
	}
	
	@GetMapping("/translist")
	public ResponseEntity<APIResponse> list(){
		Users ob=(Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Transaction> list= tser.list(ob);
		if(list!=null)
			return ResponseEntity.ok(new APIResponse("Transacation List..", true,list));
		else
			return ResponseEntity.ok(new APIResponse("Transacation not found...", false, null));
	}
	
	@PutMapping("/upd/{id}")
	public ResponseEntity<APIResponse> get(@PathVariable("id") int id,@RequestBody TransactionModel model){
		Transaction tr= tser.get(id);
		if(tr!=null) {
			tr=tser.update(model,id);
			return ResponseEntity.ok(new APIResponse("Transacation updated..", true, tr));
		}
		else
			return ResponseEntity.ok(new APIResponse("Transacation could not be update...", false, null));
	}
	
	@DeleteMapping("/del/{id}")
	public ResponseEntity<APIResponse> delete(@PathVariable("id") int id){
		Transaction tr= tser.get(id);
		if(tr!=null) {
			tser.delete(tr);
			return ResponseEntity.ok(new APIResponse("Transacation removed..", true,null));
		}
		else
			return ResponseEntity.ok(new APIResponse("Transacation could not be remove...", false, null));
	}

	@PostMapping("/savelimit")
	public ResponseEntity<APIResponse> saveLimit(@RequestBody ExpenseLimiterModel model){
		ExpenseLimiters exp=exser.save(model);
		if(exp!=null) {
			return ResponseEntity.ok(new APIResponse("Expense Limit is set now..", true,exp));
		}
		else
			return ResponseEntity.ok(new APIResponse("Could not be set the limit...", false, null));
	}
	
	@GetMapping("/getlimit")
	public ResponseEntity<APIResponse> getlimit(){
		ExpenseLimiters exp= exser.get();
		if(exp!=null)
			return ResponseEntity.ok(new APIResponse("Expense Limiter found..", true, exp));
		else
			return ResponseEntity.ok(new APIResponse("Limit not found...", false, null));
	}
	
	@PutMapping("/updlimit/{limit}")
	public ResponseEntity<APIResponse> update(@PathVariable("limit") int limit){
		ExpenseLimiters exp= exser.get();
		if(exp!=null) {
			exp.setLimits(limit);
			exp=exser.update(exp);
			return ResponseEntity.ok(new APIResponse("Transacation updated..", true, exp));
		}
		else
			return ResponseEntity.ok(new APIResponse("Transacation could not be update...", false, null));
	}
	
	@DeleteMapping("/dellimit")
	public ResponseEntity<APIResponse> deleteLimit(){
		ExpenseLimiters exp= exser.get();
		if(exp!=null) {
			exser.delete(exp);
			return ResponseEntity.ok(new APIResponse("Limit is removed..", true,null));
		}
		else
			return ResponseEntity.ok(new APIResponse("Limit could not be remove...", false, null));
	}
	
	@PostMapping("/searchtrans")
	public ResponseEntity<APIResponse> search(@RequestBody SearchModel model){
		System.out.println(model);
		List<Transaction> tr=tser.search(model);
		if(tr!=null)
			return ResponseEntity.ok(new APIResponse("Transaction Found",true,tr));
		else
			return ResponseEntity.ok(new APIResponse("Transaction not Found",false,null));
	}
}
