package com.infoplus.ibft.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.infoplus.ibft.entity.NapasReconciliationTransTemp;

@Transactional
public interface NapasRccTransTempReps extends JpaRepository<NapasReconciliationTransTemp, Long>{
	
	@Procedure(name = "procCompareNapasTrans")
	Integer exeCompareNapasTrans(@Param("p_transDate") String transDate);
	
}
