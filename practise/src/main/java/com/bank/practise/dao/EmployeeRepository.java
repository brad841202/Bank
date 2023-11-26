package com.bank.practise.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.bank.practise.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>
{
	
	//獲取員工 Id
	@Procedure(name = "getEmpId")
	List<Integer> findAllEmp();
	
	//更新員工座位
	@Procedure(name = "updateSeat")
	void updateEmployeeSeat(@Param("empId")Integer empId,
							@Param("seatId")Integer seatId);
	
	
	
}
