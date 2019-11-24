package com.ntd.DebtManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HistoryDTO {
	private Long id;

	private Date createdDate;

	private Date updatedDate;

	private Double price;

	private Long idDebt;
}
