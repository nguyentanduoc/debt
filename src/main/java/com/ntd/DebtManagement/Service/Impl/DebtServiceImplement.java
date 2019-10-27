package com.ntd.DebtManagement.Service.Impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntd.DebtManagement.Converter.DebtConverter;
import com.ntd.DebtManagement.DTO.DebtDTO;
import com.ntd.DebtManagement.Model.Debt;
import com.ntd.DebtManagement.Repository.DebtRepository;
import com.ntd.DebtManagement.Service.DebtService;

@Service
public class DebtServiceImplement implements DebtService{

	@Autowired
	private DebtRepository debtRepo;
	
	@Autowired
	private DebtConverter converter;
	
	@Override
	public List<DebtDTO> findAllDebt() {
		List<Debt> res = debtRepo.findAll();
		return converter.convertToDTO(res);
	}

	@Override
	public List<DebtDTO> findDebtsByMemberId(Long id) {
		return converter.convertToDTO(debtRepo.findByMemberId((id)));
	}

	@Override
	public DebtDTO addNewDebt(DebtDTO debtDTO) {
		Date date = new Date();
		if(debtDTO.getCreatedDate()==null) {
			debtDTO.setCreatedDate(new Timestamp(date.getTime()));
		}
		if(debtDTO.getUpdatedDate()==null) {
			debtDTO.setUpdatedDate(new Timestamp(date.getTime()));
		}
		Debt res = debtRepo.saveAndFlush(converter.convertToEntity(debtDTO));
		return converter.convertToDTO(res);
	}

	@Override
	public List<DebtDTO> isMissDealine() {
		Date date = new Date();
		List<Debt> lsDebt = debtRepo.findDebtNotMissDeadline(new Timestamp(date.getTime()),(double)0);
		return converter.convertToDTO(lsDebt);
	}

}
