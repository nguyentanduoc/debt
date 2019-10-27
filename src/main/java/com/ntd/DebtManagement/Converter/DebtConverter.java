package com.ntd.DebtManagement.Converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ntd.DebtManagement.DTO.DebtDTO;
import com.ntd.DebtManagement.Model.Debt;

@Component
public class DebtConverter {
	public DebtDTO convertToDTO(Debt debt) {
		DebtDTO res = new DebtDTO();
		res.setId(debt.getId());
		res.setCreatedDate(debt.getCreatedDate());
		res.setDeadline(debt.getDeadline());
		res.setIdMember(debt.getIdMember());
		res.setPrice(debt.getPrice());
		res.setStatus(debt.getStatus());
		res.setUpdatedDate(debt.getUpdatedDate());
		res.setNote(debt.getNote());
		return res;
	}

	public List<DebtDTO> convertToDTO(List<Debt> lsDebt) {
		List<DebtDTO> res = new ArrayList<DebtDTO>();
		for (Debt debt : lsDebt) {
			DebtDTO dto = convertToDTO(debt);
			res.add(dto);
		}
		return res;
	}

	public Debt convertToEntity(DebtDTO debtDTO) {
		Debt res = new Debt();
		res.setId(debtDTO.getId());
		res.setCreatedDate(debtDTO.getCreatedDate());
		res.setDeadline(debtDTO.getDeadline());
		res.setIdMember(debtDTO.getIdMember());
		res.setPrice(debtDTO.getPrice());
		res.setStatus(debtDTO.getStatus());
		res.setUpdatedDate(debtDTO.getUpdatedDate());
		res.setNote(debtDTO.getNote());
		return res;
	}

	public List<Debt> convertToEntity(List<DebtDTO> lsDebtDTO) {
		List<Debt> res = new ArrayList<Debt>();
		for (DebtDTO debtDTO : lsDebtDTO) {
			Debt debt = convertToEntity(debtDTO);
			res.add(debt);
		}
		return res;
	}
}
