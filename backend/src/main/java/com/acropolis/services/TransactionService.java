package com.acropolis.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.acropolis.entity.Transaction;
import com.acropolis.entity.Users;
import com.acropolis.models.SearchModel;
import com.acropolis.models.SummaryModel;
import com.acropolis.models.TransactionModel;
import com.acropolis.repositories.TransactionRepository;
import com.acropolis.repositories.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository trep;
	
	@Autowired
	private UserRepository urep;
	
	@Transactional
	public Transaction save(TransactionModel model) {
	    try {
	        Transaction tr = new Transaction(model);
	        tr.setCreated(LocalDate.now());
	        Users userob = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        if ("income".equalsIgnoreCase(tr.getType())) {
	            userob.setBalance(userob.getBalance() + model.getAmount());
	        } else {
	            userob.setBalance(userob.getBalance() - model.getAmount());
	        }
	        tr.setUser(userob);
	        urep.save(userob);
	        return trep.save(tr);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}


	public Transaction get(int id) {
		try {
			return trep.findById(id).get();
		}catch(Exception e) {
			return null;
		}
	}

	public List<Transaction> list() {
		try {
			return trep.findAll();
		}catch(Exception e) {
			return null;
		}
	}
	
	@Transactional
	public Transaction update(TransactionModel model,Integer transid) {
		Users userob = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Transaction tr=new Transaction(trep.findById(transid).get());
		//Revert the old transaction
		if("income".equals(tr.getType()))
			userob.setBalance(userob.getBalance()-tr.getAmount());
		else
			userob.setBalance(userob.getBalance()+tr.getAmount());
		
		//Apply new transaction
		if("income".equals(model.getType()))
			userob.setBalance(userob.getBalance()+model.getAmount());
		else
			userob.setBalance(userob.getBalance()-model.getAmount());
		urep.save(userob);
		return trep.save(tr.update(model));
	}

	public void delete(Transaction tr) {
		Users userob = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if("income".equals(tr.getType()))
			userob.setBalance(userob.getBalance()-tr.getAmount());
		else
			userob.setBalance(userob.getBalance()+tr.getAmount());
		urep.save(userob);
		trep.delete(tr);		
	}

	public List<Transaction> list(Users ob) {
		return trep.findByUser(ob);
	}

	public List<Transaction> search(SearchModel model) {
		Users user=(Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Transaction> list=null;
		if(model.getType()==null || model.getType()=="")
			list=trep.findByMonthANDYear(model.getMonth(),model.getYear(),user);
		else
			list=trep.findByMonthANDYearANDType(model.getMonth(),model.getYear(),model.getType(),user);
		return list;
	}

	

	
	
	
	
	
}
