package com.bank.practise.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "employee")
@NamedStoredProcedureQuery(name = "getEmpId",procedureName = "getEmpId")
@NamedStoredProcedureQuery(name = "updateSeat",procedureName = "updateSeat",parameters = {
						   @StoredProcedureParameter(mode = ParameterMode.IN,name = "empId",type = Integer.class),
						   @StoredProcedureParameter(mode = ParameterMode.IN,name = "seatId",type = Integer.class)
})//調用Stored Procedure
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_id",nullable = false )
	private Integer empId;
	
	@Column(name = "name",nullable = false,length = 50)
	private String name;
	
	@Column(name = "email",nullable = false,length = 50)
	private String email;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "floor_ear_seq")
	private SeatingChart floorSeatseq;

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public SeatingChart getFloorSeatseq() {
		return floorSeatseq;
	}

	public void setFloorSeatseq(SeatingChart floorSeatseq) {
		this.floorSeatseq = floorSeatseq;
	}
	
}
