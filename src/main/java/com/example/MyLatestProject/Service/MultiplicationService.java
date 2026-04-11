package com.example.MyLatestProject.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.MyLatestProject.entity.calculator;
import com.example.MyLatestProject.repo.CalculatorRepository;

@Service
public class MultiplicationService
{
	@Autowired
	CalculatorRepository calrep;
	
	   Pageable pageable = PageRequest.of(0, 3);
	
	
	public int mul(int i, int j)
	{
		Pageable pageable = PageRequest.of(0, 3);
		Page<calculator> page=calrep.findAll(pageable);
		
		List<calculator>  callist=page.getContent();
		//page.get
		for(calculator cal:callist)
		{
			System.out.println("operations list"+cal.getOperation());
		}
		System.out.println("before");
		for(int k=0;k<4;k++)
		{
			System.out.println("after");
			System.out.println("page"+page.getNumber()+":"+page.getContent());
		}
		
		return i*j;
	}
}