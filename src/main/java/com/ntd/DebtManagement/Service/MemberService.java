package com.ntd.DebtManagement.Service;

import java.util.List;

import com.ntd.DebtManagement.DTO.MemberDTO;

public interface MemberService {
	public List<MemberDTO> findAllMember();
	
	public MemberDTO findById(Long id);
		
	public MemberDTO addNewMember(MemberDTO dto);
}
