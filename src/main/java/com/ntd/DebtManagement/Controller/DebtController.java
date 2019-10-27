package com.ntd.DebtManagement.Controller;

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

import com.ntd.DebtManagement.DTO.DebtDTO;
import com.ntd.DebtManagement.Service.DebtService;

@RestController
@RequestMapping("/api")
public class DebtController {

	@Autowired
	private DebtService debtService;
	
	@GetMapping("/debt/find-all-debt")
	public ResponseEntity<List<DebtDTO>> findAllDebt(){
		List<DebtDTO> result = debtService.findAllDebt();
		HttpHeaders responseHeaders = new HttpHeaders();
		return new ResponseEntity<>(result, responseHeaders, HttpStatus.OK);
	}
	
	@GetMapping("/debt/find-debt-miss-deadline")
	public List<DebtDTO> findDebtMissDealine() {
		return debtService.isMissDealine();
	}
	
	@PostMapping("/debt/add-debt")
	public DebtDTO addDebt(@RequestBody DebtDTO dto) {
		return debtService.addNewDebt(dto);
	}
}
