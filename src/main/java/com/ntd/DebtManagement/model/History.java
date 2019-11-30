package com.ntd.DebtManagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "history")
@AllArgsConstructor
@NoArgsConstructor
@Data
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
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "id_debt", insertable = false, updatable = false)
	private Debt debtObject;
}
