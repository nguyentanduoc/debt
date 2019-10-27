package com.ntd.DebtManagement.dto;

import java.util.Date;


public class HistoryDTO {
	private Long id;

	private Date createdDate;

	private Date updatedDate;

	private Double price;
	
	private Long idDebt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getIdDebt() {
		return idDebt;
	}

	public void setIdDebt(Long idDebt) {
		this.idDebt = idDebt;
	}

	public HistoryDTO() {
		
	}

	public HistoryDTO(Long id, Date createdDate, Date updatedDate, Double price, Long idDebt) {
		super();
		this.id = id;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.price = price;
		this.idDebt = idDebt;
	}
	
	
}
