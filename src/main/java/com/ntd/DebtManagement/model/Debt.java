package com.ntd.DebtManagement.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "debt")
public class Debt {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "created_date", nullable = true)
	private Date createdDate;

	@Column(name = "updated_date", nullable = true)
	private Date updatedDate;

	@Column(name = "price", nullable = false)
	private Double price;

	@Column(name = "status", nullable = false)
	private String status;

	@Column(name = "deadline", nullable = true)
	private Date deadline;

	@Column(name = "id_member", nullable = false)
	private Long idMember;

	@Column(name = "note", nullable = true)
	private String note;

	@ManyToOne(optional = false)
	@JoinColumn(name = "id_member", nullable = false, insertable = false, updatable = false)
	private Member member;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "debtObject", cascade = CascadeType.ALL)
	private List<History> listHistory = new ArrayList<History>();

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

	public Long getIdMember() {
		return idMember;
	}

	public void setIdMember(Long idMember) {
		this.idMember = idMember;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<History> getListHistory() {
		return listHistory;
	}

	public void setListHistory(List<History> listHistory) {
		this.listHistory = listHistory;
	}

	public Debt(Long id, Date createdDate, Date updatedDate, Double price, String status, Date deadline, String note) {
		super();
		this.id = id;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.price = price;
		this.status = status;
		this.deadline = deadline;
		this.note = note;
	}

	public Debt() {

	}
}
