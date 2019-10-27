package com.ntd.DebtManagement.service;

import java.util.List;

import com.ntd.DebtManagement.dto.DebtDTO;

public interface DebtService {
	public List<DebtDTO> findAllDebt();
	
	public List<DebtDTO> findDebtsByMemberId(Long id);
	
	public DebtDTO addNewDebt(DebtDTO debtDTO);
	
	public List<DebtDTO> isMissDeadline();
}
