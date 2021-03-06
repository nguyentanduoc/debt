package com.ntd.DebtManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ntd.DebtManagement.service.HistoryService;
import com.ntd.DebtManagement.dto.HistoryDTO;

@RestController
@RequestMapping("/api")
public class HistoryController {
	@Autowired
	private HistoryService historyService;

	@GetMapping("/history/find-all")
	public ResponseEntity<List<HistoryDTO>> findAllHistory() {
		return ResponseEntity.ok().body(historyService.findAllHistory());
	}

	@PostMapping("/history/add-history")
	public ResponseEntity<HistoryDTO> addNewHistory(@RequestBody HistoryDTO dto) {
		return ResponseEntity.ok().body(historyService.addNewHistory(dto));
	}

	@GetMapping("/history/cash-back/{idMember}/{price}")
	public ResponseEntity<List<HistoryDTO>> cashBackHistory(@PathVariable Long idMember, @PathVariable Double price) {
		historyService.cashBack(idMember, price);
		return ResponseEntity.ok().body(null);
	}

	@DeleteMapping("/history/{idHistory}")
	public ResponseEntity deleteHistory(@PathVariable Long idHistory) {
		historyService.deleteHistory(idHistory);
		return new ResponseEntity(HttpStatus.OK);
	}
}
