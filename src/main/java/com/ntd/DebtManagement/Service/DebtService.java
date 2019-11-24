package com.ntd.DebtManagement.service;

import java.util.List;
import java.util.Map;

import com.ntd.DebtManagement.dto.DebtDTO;
import com.ntd.DebtManagement.dto.MemberDTO;
import com.ntd.DebtManagement.dto.SearchDto;
import com.ntd.DebtManagement.dto.StatisticalSearchCondition;

public interface DebtService {
	List<DebtDTO> findAllDebt();

	List<DebtDTO> findDebtsByMemberId(Long id);

	DebtDTO addNewDebt(DebtDTO debtDTO);

	List<DebtDTO> isMissDeadline();

	Map<String, Object> search(SearchDto searchDto);

	void deleteDebt(Long idDebt);

	Map<String, Object> statisticalSearch(StatisticalSearchCondition condition) throws Exception;
}
