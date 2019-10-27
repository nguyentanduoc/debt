package com.ntd.DebtManagement.Converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ntd.DebtManagement.DTO.MemberDTO;
import com.ntd.DebtManagement.Model.Member;

@Component
public class MemberConverter {
	
	@Autowired
	private DebtConverter debtConverter;
	
	public MemberDTO convertToDTO(Member member) {
		MemberDTO res = new MemberDTO();
		res.setId(member.getId());
		res.setMemberOf(member.getMemberOf());
		res.setAddress(member.getAddress());
		res.setCreatedDate(member.getCreatedDate());
		res.setEmail(member.getEmail());
		res.setFullName(member.getFullName());
		res.setPhoneNumber(member.getPhoneNumber());
		res.setUpdatedDate(member.getUpdatedDate());
		if(member.getListDebts().size()!=0) {
			res.setListDebt(debtConverter.convertToDTO(member.getListDebts()));
		}
		return res;
	}

	public List<MemberDTO> convertToDTO(List<Member> lsMember) {
		List<MemberDTO> res = new ArrayList<MemberDTO>();
		for(Member mem:lsMember) {
			MemberDTO dto = new MemberDTO();
			dto = convertToDTO(mem);
			res.add(dto);
		}
		return res;
	}

	public Member convertToEntity(MemberDTO memberDTO) {
		Member res = new Member();
		res.setId(memberDTO.getId());
		res.setMemberOf(memberDTO.getMemberOf());
		res.setAddress(memberDTO.getAddress());
		res.setCreatedDate(memberDTO.getCreatedDate());
		res.setEmail(memberDTO.getEmail());
		res.setFullName(memberDTO.getFullName());
		res.setPhoneNumber(memberDTO.getPhoneNumber());
		res.setUpdatedDate(memberDTO.getUpdatedDate());
		return res;
	}

	public List<Member> convertToEntity(List<MemberDTO> lsMemberDTO) {
		List<Member> res = new ArrayList<Member>();
		for(MemberDTO memDTO:lsMemberDTO) {
			Member entity = new Member();
			entity = convertToEntity(memDTO);
			res.add(entity);
		}
		return res;
	}
}
