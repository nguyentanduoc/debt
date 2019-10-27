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
@Table(name = "member")
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "fullname", nullable = false, length = 255)
	private String fullName;

	@Column(name = "email", nullable = false, unique = true, length = 255)
	private String email;

	@Column(name = "address", nullable = false, length = 255)
	private String address;

	@Column(name = "phone_number", nullable = false, length = 20)
	private String phoneNumber;

	@Column(name = "created_date", nullable = true)
	private Date createdDate;

	@Column(name = "updated_date", nullable = true)
	private Date updatedDate;
	
	@Column(name="member_of", nullable =true)
	private Long memberOf;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.ALL)
	private List<Debt> listDebt = new ArrayList<Debt>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "memberObject", cascade = CascadeType.ALL)
	private List<Member> listMember = new ArrayList<Member>();
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "member_of", nullable=true, insertable = false, updatable = false)
	private Member memberObject;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
	
	public Long getMemberOf() {
		return memberOf;
	}

	public void setMemberOf(Long memberOf) {
		this.memberOf = memberOf;
	}

	public List<Debt> getListDebts() {
		return listDebt;
	}

	public void setListDebts(List<Debt> listDebts) {
		this.listDebt = listDebts;
	}

	public List<Member> getListMember() {
		return listMember;
	}

	public void setListMember(List<Member> listMember) {
		this.listMember = listMember;
	}
	
	public Member() {
	}

	public Member(Long id, String fullName, String email, String address, String phoneNumber, Date createdDate,
			Date updatedDate) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

}
