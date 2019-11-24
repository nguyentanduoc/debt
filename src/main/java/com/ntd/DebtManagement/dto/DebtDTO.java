package com.ntd.DebtManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DebtDTO {
	private Long id;

	private Long idMember;

	private Date createdDate;

	private Date updatedDate;

	private Double price;

	private String status;

	private Date deadline;

	private String note;

	private List<HistoryDTO> listHistory;

	private Double totalHistory;

}
