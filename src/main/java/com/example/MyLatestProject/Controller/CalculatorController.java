package com.example.MyLatestProject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.MyLatestProject.Service.AdditionService;
import com.example.MyLatestProject.Service.MultiplicationService;
import com.example.MyLatestProject.Service.SubtractionService;

@RestController
@RequestMapping("/calculator")
public class CalculatorController
{
	@Autowired
	AdditionService addservice;
	
	@Autowired
	SubtractionService subservice;
	
	@Autowired
	MultiplicationService mulservice;
	
	@GetMapping("/add")
	public int add(@RequestParam("i") int i,@RequestParam("j") int j)
	{
		return addservice.add(i, j);
	}
	
	@GetMapping("/sub")
	public int sub(@RequestParam("i") int i,@RequestParam("j") int j)
	{
		return subservice.sub(i, j);
	}
	
	@GetMapping("/div")
	public float div(@RequestParam("i") int i,@RequestParam("j") int j)
	{
		return i%j;
	}
	
	@GetMapping("/mul")
	public int mul(@RequestParam("i") int i,@RequestParam("j") int j)
	{
		return mulservice.mul(i, j);
	}
}