package com.example.MyLatestProject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MyLatestProject.entity.calculator;



@Repository
public interface CalculatorRepository extends JpaRepository<calculator, Integer>
{
	
	
}