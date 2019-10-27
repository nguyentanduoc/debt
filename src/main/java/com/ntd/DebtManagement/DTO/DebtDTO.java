package com.ntd.DebtManagement.DTO;

import java.util.Date;

public class DebtDTO {
	private Long id;

	private Long idMember;

	private Date createdDate;

	private Date updatedDate;

	private Double price;

	private String status;

	private Date deadline;
	
	private String note;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdMember() {
		return idMember;
	}

	public void setIdMember(Long idMember) {
		this.idMember = idMember;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public DebtDTO(Long id, Long idMember, Date createdDate, Date updatedDate, Double price, String status,
			Date deadline, String note) {
		super();
		this.id = id;
		this.idMember = idMember;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.price = price;
		this.status = status;
		this.deadline = deadline;
		this.note = note;
	}

	public DebtDTO() {

	}

}
