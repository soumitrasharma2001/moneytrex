package com.acropolis.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.acropolis.entity.ExpenseLimiters;
import com.acropolis.entity.Users;
import com.acropolis.models.LoginModel;
import com.acropolis.models.SummaryModel;
import com.acropolis.models.UserModel;
import com.acropolis.repositories.TransactionRepository;
import com.acropolis.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private UserRepository urep;
	
	@Autowired
	private TransactionRepository trep;
	
	@Autowired
	private ExpenseLimitersService exser;
	
	public Users saveUser(UserModel model) {
		try {
			Users ob=new Users(model);
			ob.setRole("ROLE_CUSTOMER");
			Users user=urep.save(ob);
			return user;
		}
		catch(Exception e) {
			return null;
		}
	}
	
	
	public List<Users> list(){
		return urep.findByRole("ROLE_CUSTOMER");
	}

	public Users checkLogin(LoginModel model) {
		
		Users ob=urep.findByEmailAndPassword(model.getEmail(), model.getPassword());
		if(ob!=null)
			return ob;
		else
			return null;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users ob=urep.findByEmail(username);
		if(ob!=null)
			return ob;
		else
			return null;
	}
	public SummaryModel getSummary() {
		Users user=(Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ExpenseLimiters expLimit=exser.get();
		System.out.println(expLimit);
		return new SummaryModel(user.getBalance(),
				trep.getIncomeOfLast30Days(user.getUserid()),
				trep.getExpenseOfLast30Days(user.getUserid()),
				expLimit!=null?expLimit.getLimits():0);
	}
	
}
