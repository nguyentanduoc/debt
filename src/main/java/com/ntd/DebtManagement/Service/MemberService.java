package com.ntd.DebtManagement.service;

import java.util.List;

import com.ntd.DebtManagement.dto.MemberDTO;

public interface MemberService {
	public List<MemberDTO> findAllMember();
	
	public MemberDTO findById(Long id);
		
	public MemberDTO addNewMember(MemberDTO dto);
}
