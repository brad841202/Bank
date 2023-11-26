package com.bank.practise.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "seating_chart")
@NamedStoredProcedureQuery(name = "getAllSeatInfo",procedureName = "getAllSeatInfo") //調用Stored Procedure
public class SeatingChart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "floor_seat_seq",nullable = false)
	private Integer seatId;
	
	@Column(name = "floor_no",nullable = false,length = 50)
	private Integer floorNo;
	
	@Column(name = "seat_no",nullable = false,length = 50)	
	private Integer searNo;
	
	@OneToOne(mappedBy = "floorSeatseq")
	private Employee employee;

	public Integer getSeatId() {
		return seatId;
	}

	public void setSeatId(Integer seatId) {
		this.seatId = seatId;
	}

	public Integer getFloorNo() {
		return floorNo;
	}

	public void setFloorNo(Integer floorNo) {
		this.floorNo = floorNo;
	}

	public Integer getSearNo() {
		return searNo;
	}

	public void setSearNo(Integer searNo) {
		this.searNo = searNo;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}
