package com.ntd.DebtManagement.controller;

import java.util.List;
import java.util.Map;

import com.ntd.DebtManagement.dto.SearchDto;
import com.ntd.DebtManagement.dto.StatisticalSearchCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ntd.DebtManagement.service.DebtService;
import com.ntd.DebtManagement.dto.DebtDTO;

@RestController
@RequestMapping("/api")
public class DebtController {

	@Autowired
	private DebtService debtService;

	@GetMapping("/debt/find-all")
	public ResponseEntity<List<DebtDTO>> findAllDebt() {
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

	@PostMapping("/debt/search")
	public ResponseEntity<Map<String, Object>> search(@RequestBody SearchDto searchDto) {
		return ResponseEntity.ok().body(debtService.search(searchDto));
	}

	@DeleteMapping("/debt/{idDebt}")
	public ResponseEntity<Map<String, Object>> delete(@PathVariable Long idDebt) {
		debtService.deleteDebt(idDebt);
		return new ResponseEntity(HttpStatus.OK);
	}

	@PostMapping("/statistical/search")
	public ResponseEntity statisticalSearch(@RequestBody StatisticalSearchCondition condition) {
		return ResponseEntity.ok().body(debtService.statisticalSearch(condition));
	}
}
