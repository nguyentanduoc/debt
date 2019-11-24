package com.ntd.DebtManagement.repository;

import com.ntd.DebtManagement.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
	List<Member> getMemberByAgencyIsTrue();

	@Query("select m from Member m where m.memberOf = ?1")
	List<Member> getMemberByMemberOf(Long id);
}
