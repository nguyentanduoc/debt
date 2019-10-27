package com.ntd.DebtManagement.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ntd.DebtManagement.dto.DebtDTO;
import com.ntd.DebtManagement.model.Debt;
import com.ntd.DebtManagement.repository.DebtRepository;
import com.ntd.DebtManagement.service.DebtService;

@Service
public class DebtServiceImplement implements DebtService {

	@Autowired
	private DebtRepository debtRepo;

	@Override
	public List<DebtDTO> findAllDebt() {
		List<Debt> listDebtEntity = debtRepo.findAll();
		List<DebtDTO> listDebtResultDTO = convertListDebtToDTO(listDebtEntity);
		return listDebtResultDTO;
	}

	@Override
	public List<DebtDTO> findDebtsByMemberId(Long id) {
		List<Debt> listDebtResult = debtRepo.findByMemberId((id));
		List<DebtDTO> ListDebtDTOResult = convertListDebtToDTO(listDebtResult);
		return ListDebtDTOResult;
	}

	@Override
	public DebtDTO addNewDebt(DebtDTO debtDTO) {
		Date date = new Date();
		if (debtDTO.getCreatedDate() == null) {
			debtDTO.setCreatedDate(new Timestamp(date.getTime()));
		}
		if (debtDTO.getUpdatedDate() == null) {
			debtDTO.setUpdatedDate(new Timestamp(date.getTime()));
		}
		
		Debt debtEntityConverted = convertDebtToEntity(debtDTO);
		Debt resDebtEntity = debtRepo.saveAndFlush(debtEntityConverted);
		DebtDTO resDebtDTO = convertDebtToDTO(resDebtEntity);
		return resDebtDTO;
	}

	@Override
	public List<DebtDTO> isMissDeadline() {
		Date date = new Date();
		List<Debt> listDebtEntity = debtRepo.findDebtNotMissDeadline(new Timestamp(date.getTime()), (double) 0);
		List<DebtDTO> listDebtDTO = convertListDebtToDTO(listDebtEntity);
		return listDebtDTO;
	}

	public List<DebtDTO> convertListDebtToDTO(List<Debt> listDebtEntity) {
		List<DebtDTO> listDebtResultDTO = new ArrayList<DebtDTO>();
		for (Debt entity : listDebtEntity) {
			DebtDTO dto = convertDebtToDTO(entity);
			listDebtResultDTO.add(dto);
		}
		return listDebtResultDTO;
	}

	public List<Debt> convertListDebtToEntity(List<DebtDTO> listDebtDTO) {
		List<Debt> listDebtResult = new ArrayList<Debt>();
		for (DebtDTO dto : listDebtDTO) {
			Debt entity = convertDebtToEntity(dto);			
			listDebtResult.add(entity);
		}
		return listDebtResult;
	}
	
	public DebtDTO convertDebtToDTO(Debt debt) {
		DebtDTO res = new DebtDTO();
		BeanUtils.copyProperties(debt, res);
		return res;
	}
	
	public Debt convertDebtToEntity(DebtDTO debtDTO) {
		Debt res = new Debt();
		BeanUtils.copyProperties(debtDTO, res);
		return res;
	}

}
