package com.ntd.DebtManagement.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ntd.DebtManagement.Model.Debt;
@Repository
public interface DebtRepository extends JpaRepository<Debt, Long> {
	@Query("select d from Debt d where d.idMember=?1")
	List<Debt> findByMemberId(Long id);
	
	@Query("select d from Debt d where d.deadline<?1 and d.price>?2")
	List<Debt> findDebtNotMissDeadline(Timestamp time, Double price);
}
