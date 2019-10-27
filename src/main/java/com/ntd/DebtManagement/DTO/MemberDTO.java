package com.ntd.DebtManagement.DTO;

import java.util.Date;
import java.util.List;

public class MemberDTO {
	private Long id;
	
	private Long memberOf;

	private String fullName;

	private String email;

	private String address;

	private String phoneNumber;

	private Date createdDate;

	private Date updatedDate;
	
	private List<DebtDTO> listDebt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMemberOf() {
		return memberOf;
	}

	public void setMemberOf(Long memberOf) {
		this.memberOf = memberOf;
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
	
	public List<DebtDTO> getListDebt() {
		return listDebt;
	}

	public void setListDebt(List<DebtDTO> listDebt) {
		this.listDebt = listDebt;
	}

	public MemberDTO(Long id, String fullName, String email, String address, String phoneNumber, Date createdDate,
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

	public MemberDTO() {

	}
	
	
}
