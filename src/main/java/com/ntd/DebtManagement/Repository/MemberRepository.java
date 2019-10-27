package com.ntd.DebtManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ntd.DebtManagement.Model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{

}
