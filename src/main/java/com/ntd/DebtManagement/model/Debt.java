package com.ntd.DebtManagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "debt")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Debt {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "price", nullable = false)
	private Double price;

	@Column(name = "status", nullable = false)
	private String status;

	@Column(name = "deadline")
	private Date deadline;

	@Column(name = "id_member", nullable = false)
	private Long idMember;

	@Column(name = "note")
	private String note;

	@ManyToOne(optional = false)
	@JoinColumn(name = "id_member", nullable = false, insertable = false, updatable = false)
	private Member member;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<History> listHistory = new ArrayList<>();
}
