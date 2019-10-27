package com.ntd.DebtManagement.service;

import java.util.List;

import com.ntd.DebtManagement.dto.HistoryDTO;

public interface HistoryService {
	public List<HistoryDTO> findAllHistory();
	
	public HistoryDTO addNewHistory(HistoryDTO dto);
	
	public List<HistoryDTO> addListHistory(List<HistoryDTO> dto);
	
	public List<HistoryDTO> cashBack(Long idMember, Double price);
}
