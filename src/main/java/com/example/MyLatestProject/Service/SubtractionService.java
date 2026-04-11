package com.example.MyLatestProject.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MyLatestProject.entity.calculator;
import com.example.MyLatestProject.repo.CalculatorRepository;

@Service
public class SubtractionService
{
	@Autowired
	CalculatorRepository calrep;
	
	
	public int sub(int i, int j) {
		
		System.out.println("inside sub");
		List<calculator> callist=calrep.findAll();
		
		for(calculator cal:callist)
		{
			System.out.println("operations are"+cal.getOperation());
		}
		
	    calculator cal = calrep.findById(2)
	       .orElseThrow(() -> new RuntimeException("Calculator not found with id: 1"));
	    
	    String desc = cal.getDescription();
	    System.out.println("operationnnnnnnnnnnnnnnnnnn done was " + desc);
	    
	    return i-j;
	}
	
}