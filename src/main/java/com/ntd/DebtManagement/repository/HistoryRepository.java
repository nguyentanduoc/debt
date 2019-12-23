package com.ntd.DebtManagement.repository;

import com.ntd.DebtManagement.model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {

    @Query(value = "select d from History d where d.createdDate >= ?1 and d.createdDate <= ?2 and d.idMember = ?3")
    List<History> search(Date minDate, Date maxDate, Long memberId);

    @Query(value = "select d from History d where d.idMember=?1")
    List<History> findByMemberId(Long id);
}
