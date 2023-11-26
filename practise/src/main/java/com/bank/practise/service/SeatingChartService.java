package com.bank.practise.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.practise.dao.EmployeeRepository;
import com.bank.practise.dao.SeatingChartRepository;
import com.bank.practise.dto.SeatingChartDTO;
import jakarta.transaction.Transactional;


@Service
public class SeatingChartService {
	
	@Autowired
	private SeatingChartRepository seatRepo;
	
	@Autowired
	private EmployeeRepository empRepo;
	
	//查詢座位
	@Transactional
	public List<SeatingChartDTO> findAll(){
		List<Object[]> results = seatRepo.getAllSeatInfo();
		List<SeatingChartDTO> seatingChartDTOs = new ArrayList<>();
		
		for (Object ob : results) {
            if (ob instanceof Object[]) {
                Object[] row = (Object[]) ob;

              
                Integer floorSeatSeq = (row[0] != null) ? (Integer) row[0] : null;
                Integer floorNo = (row[1] != null) ? (Integer) row[1] : null;
                Integer seatNo = (row[2] != null) ? (Integer) row[2] : null;
                Integer empId = (row[3] != null) ? (Integer) row[3] : null;

                SeatingChartDTO dto = new SeatingChartDTO(floorSeatSeq, floorNo, seatNo, empId);
                seatingChartDTOs.add(dto);
            }
        }
        return seatingChartDTOs;
    }
	
	
	
	//更新座位
	@Transactional
	public void updateSeatEmployee(List<SeatingChartDTO> seatingChartDTO) {
		
		 for (SeatingChartDTO sDTO : seatingChartDTO) {
			 	//檢查 SeatingChartDTO 中的員工 ID 是否為空
		        if (sDTO.getEmpId() != null) {
		        	empRepo.updateEmployeeSeat(sDTO.getEmpId(),sDTO.getFloorSeatSeq());
		        } else {
		            throw new RuntimeException("error");
		        }
		    }
	}
	
}
