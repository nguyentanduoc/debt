package com.ntd.DebtManagement.Controller;

import java.util.List;

import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ntd.DebtManagement.DTO.MemberDTO;
import com.ntd.DebtManagement.Service.MemberService;

@RestController
@RequestMapping("/api")
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/member/find-all")
	public List<MemberDTO> findAllMember(){
		return memberService.findAllMember();
	}
	
	@GetMapping("/member/find-by-id/{id}")
	public MemberDTO findMemberById(@PathVariable Long id){
		return memberService.findById(id);
	}
	
	@PostMapping("/member/add-member")
	public MemberDTO addNewMember(@RequestBody MemberDTO dto) {
		return memberService.addNewMember(dto);
	}
	
	
}
