package com.ntd.DebtManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ntd.DebtManagement.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{

}
