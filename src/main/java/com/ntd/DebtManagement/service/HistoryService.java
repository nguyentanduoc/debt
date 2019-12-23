package com.ntd.DebtManagement.service;

import java.util.List;

import com.ntd.DebtManagement.dto.HistoryDTO;

public interface HistoryService {
	List<HistoryDTO> findAllHistory();
	
	HistoryDTO addNewHistory(HistoryDTO dto);
	
	List<HistoryDTO> addListHistory(List<HistoryDTO> dto);
	
	void cashBack(Long idMember, Double price);

	void deleteHistory(Long idHistory);
}
