package com.ntd.DebtManagement.Service;

import java.util.List;

import com.ntd.DebtManagement.DTO.HistoryDTO;

public interface HistoryService {
	public List<HistoryDTO> findAllHistory();
	
	public HistoryDTO addNewHistory(HistoryDTO dto);
	
	public List<HistoryDTO> addListHistory(List<HistoryDTO> dto);
	
	public List<HistoryDTO> cashBack(Long idMember, Double price);
}
