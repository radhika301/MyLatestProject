package com.example.MyLatestProject.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MyLatestProject.entity.calculator;
import com.example.MyLatestProject.repo.CalculatorRepository;

@Service
public class AdditionService
{
	@Autowired
	CalculatorRepository calrep;
	
	
	public int add(int i, int j) {
	    calculator cal = calrep.findById(11)
	       .orElseThrow(() -> new RuntimeException("Calculator not found with id: 1"));
	    
	    String desc = cal.getDescription();
	    System.out.println("operationnnnnnnnnnnnnnnnnnn done was " + desc);
	    
	    return i+j;
	}
	
}