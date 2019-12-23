package com.ntd.DebtManagement.service.impl;

import com.ntd.DebtManagement.dto.*;
import com.ntd.DebtManagement.model.Debt;
import com.ntd.DebtManagement.model.History;
import com.ntd.DebtManagement.repository.DebtRepository;
import com.ntd.DebtManagement.repository.HistoryRepository;
import com.ntd.DebtManagement.service.DebtService;
import com.ntd.DebtManagement.service.MemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class DebtServiceImplement implements DebtService {

    @Autowired
    private DebtRepository debtRepo;

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private MemberService memberService;

    @Override
    public List<DebtDTO> findAllDebt() {
        List<Debt> listDebtEntity = debtRepo.findAll();
        return convertListDebtToDTO(listDebtEntity);
    }

    @Override
    public List<DebtDTO> findDebtsByMemberId(Long id) {
        List<Debt> listDebtResult = debtRepo.findByMemberId((id));
        return convertListDebtToDTO(listDebtResult);
    }

    @Override
    public DebtDTO addNewDebt(DebtDTO debtDTO) {
        Date date = new Date();
        debtDTO.setStatus("new");
        if (debtDTO.getCreatedDate() == null) {
            debtDTO.setCreatedDate(new Timestamp(date.getTime()));
        }
        if (debtDTO.getUpdatedDate() == null) {
            debtDTO.setUpdatedDate(new Timestamp(date.getTime()));
        }

        Debt debtEntityConverted = convertDebtToEntity(debtDTO);
        Debt resDebtEntity = debtRepo.saveAndFlush(debtEntityConverted);
        return convertDebtToDTO(resDebtEntity);
    }

    @Override
    public List<DebtDTO> isMissDeadline() {
        Date date = new Date();
        List<Debt> listDebtEntity = debtRepo.findDebtNotMissDeadline(new Timestamp(date.getTime()), (double) 0);
        return convertListDebtToDTO(listDebtEntity);
    }

    @Override
    public Map<String, Object> search(SearchDto searchDto) {
        List<Debt> listDebtEntity;
        List<History> histories;
        if (searchDto.getRangeDate() != null && searchDto.getRangeDate().size() == 2) {
            Collections.sort(searchDto.getRangeDate());
            listDebtEntity = debtRepo.search(searchDto.getRangeDate().get(0), searchDto.getRangeDate().get(1), searchDto.getMemberId());
            histories = historyRepository.search(searchDto.getRangeDate().get(0), searchDto.getRangeDate().get(1), searchDto.getMemberId());
        } else {
            listDebtEntity = debtRepo.findByMemberId(searchDto.getMemberId());
            histories = historyRepository.findByMemberId(searchDto.getMemberId());
        }
        return convertListDebtToDTOWithSum(listDebtEntity, histories);
    }

    @Override
    public void deleteDebt(Long id) {
        debtRepo.deleteById(id);
    }

    @Override
    public Map<String, Object> statisticalSearch(StatisticalSearchCondition condition) {
        List<MemberDTO> members = memberService.getMemberOf(condition.getAgencyId());
        if (condition.getRangeDate() == null || condition.getRangeDate().size() < 2)
            return null;
        Collections.sort(condition.getRangeDate());
        Map<String, Object> result = new HashMap<>();
        Double totalDebt = 0d;
        Double totalRest = 0d;
        Double totalHistory = 0d;
        if (!members.isEmpty()) {
            for (int i = 0; i < members.size(); i++) {
                MemberDTO member = members.get(i);
                List<Debt> listDebtEntity = debtRepo.search(
                        condition.getRangeDate().get(0),
                        condition.getRangeDate().get(1),
                        member.getId());
                Double sumDebt = 0d;
                Double sumHistory = 0d;
                if (!listDebtEntity.isEmpty()) {
                    for (Debt debt : listDebtEntity) {
                        sumDebt += debt.getPrice();
                    }
                }
                List<History> histories = historyRepository.search(condition.getRangeDate().get(0),
                        condition.getRangeDate().get(1),
                        member.getId());
                if (!histories.isEmpty()) {
                    for (History history : histories) {
                        sumHistory += history.getPrice();
                    }
                }
                member.setSumDebt(sumDebt);
                member.setSumHistory(sumHistory);
                member.setSumRest(sumDebt - sumHistory);
                members.set(i, member);
                totalDebt += sumDebt;
                totalRest += sumDebt - sumHistory;
                totalHistory += sumHistory;
            }
            result.put("member", members);
            result.put("totalDebt", totalDebt);
            result.put("totalRest", totalRest);
            result.put("totalHistory", totalHistory);
        }
        return result;
    }

    public List<DebtDTO> convertListDebtToDTO(List<Debt> listDebtEntity) {
        List<DebtDTO> listDebtResultDTO = new ArrayList<>();
        for (Debt entity : listDebtEntity) {
            DebtDTO dto = convertDebtToDTO(entity);
            listDebtResultDTO.add(dto);
        }
        return listDebtResultDTO;
    }

    public Map<String, Object> convertListDebtToDTOWithSum(List<Debt> listDebtEntity, List<History> histories) {
        List<DebtDTO> listDebtResultDTO = new ArrayList<>();
        List<HistoryDTO> historyDTOS = new ArrayList<>();
        Double totalDebt = 0d;
        Double totalHistory = 0d;
        if (listDebtEntity != null && !listDebtEntity.isEmpty()) {
            for (Debt entity : listDebtEntity) {
                DebtDTO dto = convertDebtToDTO(entity);
                totalDebt = totalDebt + entity.getPrice();
                listDebtResultDTO.add(dto);
            }
        }
        if (histories != null && !histories.isEmpty()) {
            Double total = 0d;
            for (History history : histories) {
                HistoryDTO historyDTO = new HistoryDTO();
                BeanUtils.copyProperties(history, historyDTO);
                historyDTOS.add(historyDTO);
                total += history.getPrice();
            }
            totalHistory = totalHistory + total;
        }
        Map<String, Object> result = new HashMap<>();
        result.put("debts", listDebtResultDTO);
        result.put("totalDebt", totalDebt);
        result.put("totalHistory", totalHistory);
        result.put("rest", totalDebt - totalHistory);
        result.put("histories", historyDTOS);
        return result;
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
