package com.ntd.DebtManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberDTO {
	private Long id;

	private Long memberOf;

	private String fullName;

	private String address;

	private String phoneNumber;

	private Date createdDate;

	private Date updatedDate;

	private Double sumDebt;

	private Double sumHistory;

	private Double sumRest;

	private Boolean agency;
}
