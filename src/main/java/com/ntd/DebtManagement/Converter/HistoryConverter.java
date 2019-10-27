package com.ntd.DebtManagement.Converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ntd.DebtManagement.DTO.HistoryDTO;
import com.ntd.DebtManagement.Model.History;

@Component
public class HistoryConverter {
	public HistoryDTO convertToDTO(History history) {
		HistoryDTO res = new HistoryDTO();
		res.setId(history.getId());
		res.setCreatedDate(history.getCreatedDate());
		res.setUpdatedDate(history.getUpdatedDate());
		res.setPrice(history.getPrice());
		res.setIdDebt(history.getIdDebt());
		return res;
	}

	public List<HistoryDTO> convertToDTO(List<History> lsHistory) {
		List<HistoryDTO> res = new ArrayList<HistoryDTO>();
		for(History his:lsHistory) {
			HistoryDTO dto = convertToDTO(his);
			res.add(dto);
		}
		return res;
	}

	public History convertToEntity(HistoryDTO historyDTO) {
		History res = new History();
		res.setId(historyDTO.getId());
		res.setCreatedDate(historyDTO.getCreatedDate());
		res.setUpdatedDate(historyDTO.getUpdatedDate());
		res.setPrice(historyDTO.getPrice());
		res.setIdDebt(historyDTO.getIdDebt());
		return res;
	}

	public List<History> convertToEntity(List<HistoryDTO> lsHistoryDTO) {
		List<History> res = new ArrayList<History>();
		for(HistoryDTO dto:lsHistoryDTO) {
			History his  = convertToEntity(dto);
			res.add(his);
		}
		return res;
	}
}
