package com.ntd.DebtManagement.Service.Impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntd.DebtManagement.Converter.MemberConverter;
import com.ntd.DebtManagement.DTO.MemberDTO;
import com.ntd.DebtManagement.Model.Member;
import com.ntd.DebtManagement.Repository.MemberRepository;
import com.ntd.DebtManagement.Service.MemberService;

@Service
public class MemberServiceImplement implements MemberService{

	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private MemberConverter converter;
	
	@Override
	public List<MemberDTO> findAllMember() {
		List<MemberDTO> res = converter.convertToDTO(memberRepo.findAll());
		return res;
	}

	@Override
	public MemberDTO addNewMember(MemberDTO dto) {
		Date date = new Date();
		if(dto.getCreatedDate()==null) {
			dto.setCreatedDate(new Timestamp(date.getTime()));
		}
		if(dto.getUpdatedDate()==null) {
			dto.setUpdatedDate(new Timestamp(date.getTime()));
		}
		Member entity = memberRepo.saveAndFlush(converter.convertToEntity(dto));
		return converter.convertToDTO(entity);
	}

	@Override
	public MemberDTO findById(Long id) {		
		return converter.convertToDTO(memberRepo.findById(id).get());
	}

}
