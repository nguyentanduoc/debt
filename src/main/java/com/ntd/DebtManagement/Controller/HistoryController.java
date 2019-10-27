package com.ntd.DebtManagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ntd.DebtManagement.DTO.HistoryDTO;
import com.ntd.DebtManagement.Service.HistoryService;

@RestController
@RequestMapping("/api")
public class HistoryController {
	@Autowired
	private HistoryService historyService;
	
	@GetMapping("/history/find-all")
	public List<HistoryDTO> findAllHistory(){
		return historyService.findAllHistory();
	}
	
	@PostMapping("/history/add-history")
	public HistoryDTO addNewHistory(@RequestBody HistoryDTO dto) {
		return historyService.addNewHistory(dto);
	}
	
	@GetMapping("/history/cash-back/{idMember}/{price}")
	public List<HistoryDTO> cashBackHistory(@PathVariable Long idMember, @PathVariable Double price){
		return historyService.cashBack(idMember, price);
	}
}
