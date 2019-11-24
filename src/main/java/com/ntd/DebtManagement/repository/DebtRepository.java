package com.ntd.DebtManagement.repository;

import com.ntd.DebtManagement.model.Debt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
@Repository
public interface DebtRepository extends JpaRepository<Debt, Long> {
	@Query(value ="select d from Debt d where d.idMember=?1")
	List<Debt> findByMemberId(Long id);
	
	@Query(value = "select d from Debt d where d.deadline<?1 and d.price>?2")
	List<Debt> findDebtNotMissDeadline(Timestamp time, Double price);

	@Query(value = "select d from Debt d where d.createdDate >= ?1 and d.createdDate <= ?2 and d.idMember = ?3")
	List<Debt> search(Date minDate, Date maxDate, Long memberId);

}
