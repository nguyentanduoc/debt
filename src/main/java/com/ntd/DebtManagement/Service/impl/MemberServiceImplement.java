package com.ntd.DebtManagement.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ntd.DebtManagement.dto.MemberDTO;
import com.ntd.DebtManagement.model.Member;
import com.ntd.DebtManagement.repository.MemberRepository;
import com.ntd.DebtManagement.service.MemberService;

@Service
public class MemberServiceImplement implements MemberService {

	@Autowired
	private MemberRepository memberRepo;

	@Override
	public List<MemberDTO> findAllMember() {
		List<Member> lsMemberEntity = memberRepo.findAll();
		return convertListMemberToDTO(lsMemberEntity);
	}

	@Override
	public MemberDTO addNewMember(MemberDTO dto) {
		Date date = new Date();
		if (dto.getCreatedDate() == null) {
			dto.setCreatedDate(new Timestamp(date.getTime()));
		}
		if (dto.getUpdatedDate() == null) {
			dto.setUpdatedDate(new Timestamp(date.getTime()));
		}
		if(dto.getAgency() == null) {
			dto.setAgency(false);
		}
		Member entityConverted = convertMemberToEntity(dto);
		Member entity = memberRepo.saveAndFlush(entityConverted);
		return convertMemberToDTO(entity);
	}

	@Override
	public List<MemberDTO> getAgency() {
		List<Member> members = memberRepo.getMemberByAgencyIsTrue();
		if (members.size() > 0) {
			return convertListMemberToDTO(members);
		}
		return null;
	}

	@Override
	public MemberDTO findById(Long id) {
		Member memberEntity = memberRepo.findById(id).get();
		return convertMemberToDTO(memberEntity);
	}

	@Override
	public List<MemberDTO> getMemberOf(Long idMember) {
		List<Member> members = memberRepo.getMemberByMemberOf(idMember);
		if (members.size() > 0) {
			return convertListMemberToDTO(members);
		}
		return null;
	}

	@Override
	public void deleteMember(Long idMember){
		memberRepo.deleteById(idMember);
	}

	public MemberDTO convertMemberToDTO(Member member) {
		MemberDTO res = new MemberDTO();
		BeanUtils.copyProperties(member, res);
		return res;
	}

	public Member convertMemberToEntity(MemberDTO memberDTO) {
		Member res = new Member();
		BeanUtils.copyProperties(memberDTO, res);
		return res;
	}


	public List<MemberDTO> convertListMemberToDTO(List<Member> lsMember) {
		List<MemberDTO> res = new ArrayList<MemberDTO>();
		for (Member entity : lsMember) {
			MemberDTO dto = convertMemberToDTO(entity);
			res.add(dto);
		}
		return res;
	}


	public List<Member> convertListMemberToEntity(List<MemberDTO> lsMemberDTO) {
		List<Member> res = new ArrayList<Member>();
		for (MemberDTO dto : lsMemberDTO) {
			Member entity = convertMemberToEntity(dto);
			res.add(entity);
		}
		return res;
	}


}
