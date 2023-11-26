package com.bank.practise.dto;

import lombok.Data;

@Data
public class SeatingChartDTO {
	
	private Integer floorSeatSeq;
	private Integer floorNo;
	private Integer seatNo;
	private Integer empId;
	
	public SeatingChartDTO() {
		
	}
	
	//查詢座位
	public SeatingChartDTO(Integer floorSeatSeq, Integer floorNo, Integer seatNo, Integer empId) {
		this.floorSeatSeq = floorSeatSeq;
		this.floorNo = floorNo;
		this.seatNo = seatNo;
		this.empId = empId;
	}
	
	//查員工 ID
	public SeatingChartDTO(Integer empId) {
		this.empId = empId;
	}

}
