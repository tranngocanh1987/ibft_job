package com.infoplus.ibft.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.infoplus.ibft.entity.NapasRccTransCompare;

@Transactional
public interface NapasRccTransCompareReps  extends JpaRepository<NapasRccTransCompare, Long>{
	 @Query("SELECT a FROM NapasRccTransCompare a where a.status <> '0' and a.record_status = 'O' and DATE(created_dt) = DATE(SYSDATE())") 
	 List<NapasRccTransCompare> findMissedTrans();
}
