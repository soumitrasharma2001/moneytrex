package com.acropolis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.acropolis.entity.ExpenseLimiters;
import com.acropolis.entity.Users;
import com.acropolis.models.ExpenseLimiterModel;
import com.acropolis.repositories.ExpenseLimitersRepository;

@Service
public class ExpenseLimitersService {
	
	@Autowired
	private ExpenseLimitersRepository erep;

	public ExpenseLimiters save(ExpenseLimiterModel model) {
		try {
		ExpenseLimiters exp=new ExpenseLimiters(model);
		Users userob=(Users)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		exp.setUser(userob);
		return erep.save(exp);
		}catch(Exception e) {
		return null;
		}
		
	}

	public ExpenseLimiters get(int id) {
		try {
			return erep.findById(id).get();
		}catch(Exception e) {
			return null;
		}
	}

	public ExpenseLimiters update(ExpenseLimiters exp) {
		return erep.save(exp);
	}

	public void delete(ExpenseLimiters exp) {
		erep.delete(exp);
		
	}

	public ExpenseLimiters get() {
		Users ob=(Users)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try {
			return erep.findByUser(ob).orElse(null);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
	}
	
	
	
	
	
}
