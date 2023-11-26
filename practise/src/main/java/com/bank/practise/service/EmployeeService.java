package com.bank.practise.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.practise.dao.EmployeeRepository;
import com.bank.practise.dto.SeatingChartDTO;

import jakarta.transaction.Transactional;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository empRepo;
	
	@Transactional
	public List<SeatingChartDTO> findAllEmp(){
		List<Integer> empIds = empRepo.findAllEmp();
		
		List<SeatingChartDTO> result = new ArrayList<>();
		
		for(Integer empId:empIds) {
			SeatingChartDTO dto = new SeatingChartDTO(empId);
			result.add(dto);
		}
		return result;
	}
	
	
}
