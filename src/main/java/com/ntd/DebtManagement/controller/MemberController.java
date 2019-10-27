package com.ntd.DebtManagement.controller;

import java.util.List;

import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ntd.DebtManagement.service.MemberService;
import com.ntd.DebtManagement.dto.MemberDTO;

@RestController
@RequestMapping("/api")
public class MemberController {
	@Autowired
	private MemberService memberService;

	@GetMapping("/member/find-all")
	public ResponseEntity<List<MemberDTO>> findAllMember() {
		List<MemberDTO> res = memberService.findAllMember();
		return ResponseEntity.ok().body(res);
	}

	@GetMapping("/member/find-by-id/{id}")
	public ResponseEntity<MemberDTO> findMemberById(@PathVariable Long id) {
		MemberDTO res = memberService.findById(id);
		return ResponseEntity.ok().body(res);
	}

	@PostMapping("/member/add-member")
	public ResponseEntity<MemberDTO> addNewMember(@RequestBody MemberDTO dto) {
		MemberDTO res = memberService.addNewMember(dto);
		return ResponseEntity.ok().body(res);
	}

}
