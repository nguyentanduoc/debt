package com.ntd.DebtManagement.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ntd.DebtManagement.dto.HistoryDTO;
import com.ntd.DebtManagement.model.Debt;
import com.ntd.DebtManagement.model.History;
import com.ntd.DebtManagement.repository.DebtRepository;
import com.ntd.DebtManagement.repository.HistoryRepository;
import com.ntd.DebtManagement.service.HistoryService;

@Service
public class HistoryServiceImplement implements HistoryService {

	@Autowired
	private HistoryRepository historyRepo;

	@Autowired
	private DebtRepository debtRepo;

	@Override
	public List<HistoryDTO> findAllHistory() {
		List<HistoryDTO> listHistoryDTOResult = convertListHistoryToDTO(historyRepo.findAll());
		return listHistoryDTOResult;
	}

	@Override
	public HistoryDTO addNewHistory(HistoryDTO dto) {
		History historyConverted = convertHistoryToEntity(dto);
		History history = historyRepo.saveAndFlush(historyConverted);
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
		HistoryDTO historyDTOResult = convertHistoryToDTO(history);
		return historyDTOResult;
	}

	// ????
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
			if (debt.getPrice() != 0) {
				if (cashBackMoney == debt.getPrice()) {
					history.setPrice(debt.getPrice());
					debt.setStatus("PAYOFF_DEBT");
					debt.setUpdatedDate(new Timestamp(date.getTime()));
					debtRepo.saveAndFlush(debt);
					res.add(historyRepo.saveAndFlush(history));
					cashBackMoney = 0d;
					break;
				} else if (cashBackMoney < debt.getPrice()) {
					history.setPrice(cashBackMoney);
					debt.setStatus("PAYING_DEBT");
					debt.setUpdatedDate(new Timestamp(date.getTime()));
					debtRepo.saveAndFlush(debt);
					res.add(historyRepo.saveAndFlush(history));
					cashBackMoney = 0d;
					break;
				} else {
					history.setPrice(debt.getPrice());
					cashBackMoney -= debt.getPrice();
					debt.setStatus("PAYOFF_DEBT");
					debt.setUpdatedDate(new Timestamp(date.getTime()));
					res.add(historyRepo.saveAndFlush(history));
					debtRepo.saveAndFlush(debt);
				}
			}
		}
		List<HistoryDTO> listHistoryDTOResult = convertListHistoryToDTO(res);
		return listHistoryDTOResult;
	}

	@Override
	public void deleteHistory(Long idHistory) {
		historyRepo.deleteById(idHistory);
	}

	public List<HistoryDTO> convertListHistoryToDTO(List<History> listHistoryEntity) {
		List<HistoryDTO> listDebtResultDTO = new ArrayList<HistoryDTO>();
		for (History entity : listHistoryEntity) {
			HistoryDTO dto = convertHistoryToDTO(entity);
			listDebtResultDTO.add(dto);
		}
		return listDebtResultDTO;
	}

	public List<History> convertListHistoryToEntity(List<HistoryDTO> listHistoryDTO) {
		List<History> listHistoryResult = new ArrayList<History>();
		for (HistoryDTO dto : listHistoryDTO) {
			History entity = convertHistoryToEntity(dto);
			listHistoryResult.add(entity);
		}
		return listHistoryResult;
	}

	public HistoryDTO convertHistoryToDTO(History history) {
		HistoryDTO res = new HistoryDTO();
		BeanUtils.copyProperties(history, res);
		return res;
	}

	public History convertHistoryToEntity(HistoryDTO historyDTO) {
		History res = new History();
		BeanUtils.copyProperties(historyDTO, res);
		return res;
	}

}
