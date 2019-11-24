package com.ntd.DebtManagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

@Data
@Entity
@Table(name = "member")
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "fullname", nullable = false, length = 255)
	private String fullName;

	@Column(name = "address", nullable = false, length = 255)
	private String address;

	@Column(name = "phone_number", nullable = false, length = 20)
	private String phoneNumber;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "member_of")
	private Long memberOf;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.ALL)
	private List<Debt> listDebt = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "memberObject", cascade = CascadeType.ALL)
	private List<Member> listMember = new ArrayList<>();

	@ManyToOne()
	@JoinColumn(name = "member_of", insertable = false, updatable = false)
	private Member memberObject;

	@Column(name = "is_agency", nullable = false)
	private boolean agency;
}
