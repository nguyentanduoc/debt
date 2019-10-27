package com.ntd.DebtManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ntd.DebtManagement.service.DebtService;
import com.ntd.DebtManagement.dto.DebtDTO;

@RestController
@RequestMapping("/api")
public class DebtController {

	@Autowired
	private DebtService debtService;
	
	@GetMapping("/debt/find-all")
	public ResponseEntity<List<DebtDTO>> findAllDebt(){
		List<DebtDTO> result = debtService.findAllDebt();
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping("/debt/find-debt-miss-deadline")
	public ResponseEntity<List<DebtDTO>> findDebtMissDeadline() {
		return ResponseEntity.ok().body(debtService.isMissDeadline());
	}
	
	@GetMapping("/debt/find-debt-by-member/{id}")
	public ResponseEntity<List<DebtDTO>> findDebtByMember(@PathVariable Long id) {
		return ResponseEntity.ok().body(debtService.findDebtsByMemberId(id));
	}
	
	@PostMapping("/debt/add-debt")
	public ResponseEntity<DebtDTO> addDebt(@RequestBody DebtDTO dto) {
		return ResponseEntity.ok().body(debtService.addNewDebt(dto));
	}
}
