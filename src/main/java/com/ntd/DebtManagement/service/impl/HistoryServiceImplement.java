package com.ntd.DebtManagement.service.impl;

import java.sql.Timestamp;
import java.util.*;

import com.ntd.DebtManagement.model.Member;
import com.ntd.DebtManagement.repository.MemberRepository;
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

    @Autowired
    private MemberRepository memberRepository;

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
    public void cashBack(Long idMember, Double price) {
        History history = new History();
        history.setPrice(price);
        history.setIdMember(idMember);
        history.setCreatedDate(new Date());
        history.setUpdatedDate(new Date());
        historyRepo.saveAndFlush(history);
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
