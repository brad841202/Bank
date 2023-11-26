package com.bank.practise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.practise.dto.SeatingChartDTO;
import com.bank.practise.service.SeatingChartService;

@RestController
@CrossOrigin
public class SeatingController {
	
	@Autowired
	private SeatingChartService seatingChartService;
	
	@GetMapping("/seats")
	public ResponseEntity<?>findAllSeat(){
		List<SeatingChartDTO> seatResults = seatingChartService.findAll();
		if(seatResults.isEmpty()) {
			return new ResponseEntity<String>("沒有該筆資料",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(seatResults,HttpStatus.OK);
	}
	
	@PutMapping("/seats")
	public ResponseEntity<?>updateSeat(@RequestBody List<SeatingChartDTO> seatingChartDTO){
		try {
			seatingChartService.updateSeatEmployee(seatingChartDTO);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("更新失敗",HttpStatus.BAD_REQUEST);
		}
	}
}
