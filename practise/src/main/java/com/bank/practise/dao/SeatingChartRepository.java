package com.bank.practise.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import com.bank.practise.entity.SeatingChart;

public interface SeatingChartRepository extends JpaRepository<SeatingChart, Integer> {
	
	//獲得座位資訊
	@Procedure(name = "getAllSeatInfo")
	List<Object[]>getAllSeatInfo();
}
