package com.ntd.DebtManagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ntd.DebtManagement.Model.History;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
	@Query("select d from History d where d.idDebt=?1")
	List<History> findByDebt(Long id);
}
