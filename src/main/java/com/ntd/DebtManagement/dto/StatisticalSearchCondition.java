package com.ntd.DebtManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StatisticalSearchCondition {

	private Long agencyId;

	private List<Date> rangeDate;
}
