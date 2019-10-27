package com.ntd.DebtManagement.Service.Impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntd.DebtManagement.Converter.DebtConverter;
import com.ntd.DebtManagement.Converter.HistoryConverter;
import com.ntd.DebtManagement.DTO.DebtDTO;
import com.ntd.DebtManagement.DTO.HistoryDTO;
import com.ntd.DebtManagement.Model.Debt;
import com.ntd.DebtManagement.Model.History;
import com.ntd.DebtManagement.Repository.DebtRepository;
import com.ntd.DebtManagement.Repository.HistoryRepository;
import com.ntd.DebtManagement.Service.HistoryService;

@Service
public class HistoryServiceImplement implements HistoryService {

	@Autowired
	private HistoryRepository historyRepo;

	@Autowired
	private HistoryConverter converter;

	@Autowired
	private DebtRepository debtRepo;

	@Autowired
	private DebtConverter debtConverter;

	@Override
	public List<HistoryDTO> findAllHistory() {
		return converter.convertToDTO(historyRepo.findAll());
	}

	@Override
	public HistoryDTO addNewHistory(HistoryDTO dto) {
		History history = historyRepo.saveAndFlush(converter.convertToEntity(dto));
		Debt debt = debtRepo.findById(dto.getIdDebt()).get();
		Double calculatedDebt = debt.getPrice() - dto.getPrice();
		if (calculatedDebt.equals(0)) {
			debt.setStatus("PAYOFF_DEBT");
		} else {
			debt.setStatus("PAYING_DEBT");
		}
		debt.setUpdatedDate(dto.getUpdatedDate());
		debt.setPrice(calculatedDebt);
		debtRepo.saveAndFlush(debt);
		return converter.convertToDTO(history);
	}

	@Override
	public List<HistoryDTO> addListHistory(List<HistoryDTO> lsHistory) {
		List<HistoryDTO> result = new ArrayList<HistoryDTO>();
		for (HistoryDTO dto : lsHistory) {
			HistoryDTO res = this.addNewHistory(dto);
			result.add(res);
		}
		return result;
	}

	@Override
	public List<HistoryDTO> cashBack(Long idMember, Double price) {
		List<Debt> lsDebt = debtRepo.findByMemberId(idMember);

		Comparator<Debt> compareById = new Comparator<Debt>() {
			@Override
			public int compare(Debt d1, Debt d2) {
				return d1.getId().compareTo(d2.getId());
			}
		};
		Collections.sort(lsDebt, compareById);
		List<History> res = new ArrayList<History>();
		Date date = new Date();
		Double cashBackMoney = price;
		for (Debt debt : lsDebt) {
			History history = new History();
			history.setIdDebt(debt.getId());
			history.setCreatedDate(new Timestamp(date.getTime()));
			history.setUpdatedDate(new Timestamp(date.getTime()));
			if (cashBackMoney == debt.getPrice()) {
				history.setPrice(debt.getPrice());
				debt.setPrice((double) 0);
				debt.setStatus("PAYOFF_DEBT");
				debt.setUpdatedDate(new Timestamp(date.getTime()));
				debtRepo.saveAndFlush(debt);
				res.add(historyRepo.saveAndFlush(history));
				cashBackMoney = (double) 0;
				break;
			} else if (cashBackMoney < debt.getPrice()) {
				history.setPrice(cashBackMoney);
				debt.setPrice(debt.getPrice()- cashBackMoney);
				debt.setStatus("PAYING_DEBT");
				debt.setUpdatedDate(new Timestamp(date.getTime()));
				debtRepo.saveAndFlush(debt);
				res.add(historyRepo.saveAndFlush(history));
				cashBackMoney = (double) 0;
				break;
			} else {
				history.setPrice(debt.getPrice());
				cashBackMoney -= debt.getPrice();
				debt.setPrice((double) 0);
				debt.setStatus("PAYOFF_DEBT");
				debt.setUpdatedDate(new Timestamp(date.getTime()));
				res.add(historyRepo.saveAndFlush(history));
				debtRepo.saveAndFlush(debt);
				
			}

		}
		return converter.convertToDTO(res);
	}

}
