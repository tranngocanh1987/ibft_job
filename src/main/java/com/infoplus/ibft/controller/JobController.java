package com.infoplus.ibft.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.infoplus.ibft.model.JobModel;
import com.infoplus.ibft.service.JobService;

@RestController
public class JobController {
	
	@Autowired
	JobService jobService;

	private int totalPages = 1;
	
	@GetMapping({ "/hello" })
	public String firstPage() {
		return "Hello World";
	}

	
	@GetMapping(value = "/stopScheduler/{jobName}")
	public String stopSchedule(@PathVariable String jobName) {
		
		if(jobService.stopJob(jobName))
			return jobName + " STOPED";
		else
			return jobName + " CAN NOT STOP";
	}

	@GetMapping(value = "/startScheduler/{jobName}")
	public String startSchedule(@PathVariable String jobName) {
		if(jobService.startJob(jobName))
			return jobName + " STARTED";
		else
			return jobName + " CAN NOT STARTED";
	}
	
	@GetMapping(value = "/restartScheduler/{jobName}")
	public String restartSchedule(@PathVariable String jobName) {
		if(jobService.restart(jobName))
			return jobName + " RESTARTED";
		else
			return jobName + " CAN NOT RESTARTED";
	}
	
	@GetMapping(value = "/listAllScheduler")
	public String listAllScheduler() throws JsonProcessingException {
		List<JobModel> lstJobs = jobService.getAllWorkingJobs();	
		return lstJobs.toString();
	}

	@GetMapping(value = "/listScheduler")
	public ModelAndView listSchedule() throws JsonProcessingException {
		ModelAndView modelAndView = new ModelAndView("list-job");
		
		List<JobModel> lstJobs = jobService.getAllWorkingJobs();	
		modelAndView.addObject("jobList", lstJobs);
		return modelAndView;
	}
		
	private ModelAndView getListJob(String name, int currentPage) {
		ModelAndView modelAndView = new ModelAndView(name);
		
		if(currentPage < 1 )
			currentPage = 1;
		else if( currentPage > 1 && currentPage > totalPages )
			currentPage = totalPages;
		
		PageRequest pageable  = PageRequest.of(currentPage-1, 10);
		Page<JobModel> lstJobs = jobService.getAllJobs(pageable);
		
		totalPages = lstJobs.getTotalPages();
		
		if(totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages).boxed().collect(Collectors.toList());
			 modelAndView.addObject("pageNumbers", pageNumbers);
		}
		modelAndView.addObject("activeJobList", true);
		modelAndView.addObject("jobList", lstJobs.getContent());
		return modelAndView;
	}
}
