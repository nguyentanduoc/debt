package com.ntd.DebtManagement.Service;

import java.util.List;

import com.ntd.DebtManagement.DTO.DebtDTO;
import com.ntd.DebtManagement.Model.Debt;

public interface DebtService {
	public List<DebtDTO> findAllDebt();
	
	public List<DebtDTO> findDebtsByMemberId(Long id);
	
	public DebtDTO addNewDebt(DebtDTO debtDTO);
	
	public List<DebtDTO> isMissDealine();
}
