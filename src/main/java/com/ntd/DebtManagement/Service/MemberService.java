package com.ntd.DebtManagement.service;

import java.util.List;

import com.ntd.DebtManagement.dto.MemberDTO;

public interface MemberService {
	List<MemberDTO> findAllMember();
	
	MemberDTO findById(Long id);
		
	MemberDTO addNewMember(MemberDTO dto);

	List<MemberDTO> getAgency();

	List<MemberDTO> getMemberOf(Long idMember);

	void deleteMember(Long idMember);
}
