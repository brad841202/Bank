package com.bank.practise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.practise.dto.SeatingChartDTO;
import com.bank.practise.service.EmployeeService;

@RestController
@CrossOrigin
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/employees")
	 public ResponseEntity<?>findAllEmp(){
		List<SeatingChartDTO> employeeResults  = employeeService.findAllEmp();
		if(employeeResults.isEmpty()) {
			return new ResponseEntity<String>("沒有該筆資料",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(employeeResults,HttpStatus.OK);
	}
}
