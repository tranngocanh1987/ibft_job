package com.infoplus.ibft.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.infoplus.ibft.entity.IbftJob;

@Transactional
public interface IbftJobReps extends JpaRepository<IbftJob, Long> {
	
	@Query("SELECT a FROM IbftJob a where a.record_status = 'O'")
	List<IbftJob> getAllWoringJobs();
	
	@Query("SELECT a FROM IbftJob a where a.record_status = 'O'")
	List<IbftJob> getAllWoringJob(Pageable pageable);
	
	@Query("SELECT a FROM IbftJob a where a.record_status = 'O' and a.job_name = :jobName")
	List<IbftJob> getJobByName(String jobName);
	
	@Query("SELECT count(a) FROM IbftJob a where a.record_status = 'O'")
	int countAllWoringJob();
}
