package com.infoplus.ibft.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.infoplus.ibft.entity.NapasTransactionCompare;

@Transactional
public interface NapasTransCompareReps extends JpaRepository<NapasTransactionCompare, Long>{
	
	 @Query("SELECT a FROM NapasTransactionCompare a where a.status <> '0' and a.record_status = 'O' and a.compare_trn_date = :transDate") 
	 List<NapasTransactionCompare> findMissedTransByTransDate(@Param("transDate") String transDate);
}
