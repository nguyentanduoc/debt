package com.ntd.DebtManagement.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "history")
public class History {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "created_date", nullable = false)
	private Date createdDate;

	@Column(name = "updated_date", nullable = false)
	private Date updatedDate;

	@Column(name = "price", nullable = false)
	private Double price;
	
	@Column(name="id_debt", nullable = false)
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

	public History(Long id, Date createdDate, Date updatedDate, Double price, Long idDebt) {
		super();
		this.id = id;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.price = price;
		this.idDebt = idDebt;
	}

	public History() {
	}
}
