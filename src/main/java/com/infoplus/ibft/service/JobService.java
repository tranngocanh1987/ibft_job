package com.infoplus.ibft.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.stereotype.Service;

import com.infoplus.ibft.entity.IbftJob;
import com.infoplus.ibft.job.NapasBatchReceive;
import com.infoplus.ibft.model.JobModel;
import com.infoplus.ibft.repository.IbftJobReps;

@Service
public class JobService extends BaseService {
	@Autowired
	IbftJobReps jobReps;
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	NapasBatchReceive napasBatchReceive;

	@Autowired
	private ScheduledAnnotationBeanPostProcessor postProcessor;

	private static final String SCHEDULED_TASKS = "scheduledTasks";

	public List<JobModel> getAllWorkingJobs(){
		List<IbftJob> lstJobEntities = jobReps.getAllWoringJobs();
		List<JobModel> lstJobModels = new ArrayList<JobModel>();
		
		if (lstJobEntities != null && lstJobEntities.size() > 0) {
			Set<ScheduledTask> setTasks = postProcessor.getScheduledTasks();
			
			for (IbftJob jobEntity : lstJobEntities) {
				JobModel model = new JobModel(jobEntity);
				model.setWorkingStatus("STOP");

				String fullDir = model.getDirName() + "." + model.getFuncName();

				for (ScheduledTask task : setTasks) {
					if (task.toString().toUpperCase().equalsIgnoreCase(fullDir.toUpperCase())) {
						model.setWorkingStatus("RUNNING");
						break;
					}
				}
				lstJobModels.add(model);
			}
		}
		return lstJobModels;
	}
	
	public Page<JobModel> getAllJobs(Pageable pageable) {
		int total = jobReps.countAllWoringJob();
		List<IbftJob> lstJobEntities = jobReps.getAllWoringJob(pageable);
		List<JobModel> lstJobModels = new ArrayList<JobModel>();
		Page<JobModel> pageModel = null;
		
		if (lstJobEntities != null && lstJobEntities.size() > 0) {
			Set<ScheduledTask> setTasks = postProcessor.getScheduledTasks();

			for (IbftJob jobEntity : lstJobEntities) {
				JobModel model = new JobModel(jobEntity);
				model.setWorkingStatus("STOP");

				String fullDir = model.getDirName() + "." + model.getFuncName();

				for (ScheduledTask task : setTasks) {
					if (task.toString().toUpperCase().equalsIgnoreCase(fullDir.toUpperCase())) {
						model.setWorkingStatus("RUNNING");
						break;
					}
				}
				lstJobModels.add(model);
				
				pageModel = new PageImpl<JobModel>(lstJobModels, pageable, total);
			}
		}
		return pageModel;
	}

	public boolean startJob(String jobName) {
		try {
			List<IbftJob> lstJobEntities = jobReps.getJobByName(jobName);

			if (lstJobEntities != null && lstJobEntities.size() > 0) {
				JobModel model = new JobModel(lstJobEntities.get(0));
				
				if (!isStartedJob(model)) {
					Object cls = context.getBean(jobName);
					postProcessor.postProcessAfterInitialization(cls, SCHEDULED_TASKS);
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (Exception ex) {
			logger.error("Start Job error! " + ex.toString());
			return false;
		}
	}

	public boolean stopJob(String jobName) {
		try {
			Object cls = context.getBean(jobName);
			postProcessor.postProcessBeforeDestruction(cls, SCHEDULED_TASKS);
			return true;
		} catch (Exception ex) {
			logger.error("Start Job error! " + ex.toString());
			return false;
		}
	}
	
	public boolean restart(String jobName) {
		try {
			List<IbftJob> lstJobEntities = jobReps.getJobByName(jobName);

			if (lstJobEntities != null && lstJobEntities.size() > 0) {
				JobModel model = new JobModel(lstJobEntities.get(0));
				
				if (isStartedJob(model)) {
					//Class<? extends BaseService> cls = (Class<? extends BaseService>) Class.forName(model.getSerivceName());
					Object obj = context.getBean(model.getServiceName());
					((BaseService) obj).send();
					
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (Exception ex) {
			logger.error("Start Job error! " + ex.toString());
			return false;
		}
	}

	private boolean isStartedJob(JobModel jobModel) {
		try {
			String fullDir = jobModel.getDirName() + "." + jobModel.getFuncName();

			Set<ScheduledTask> setTasks = postProcessor.getScheduledTasks();

			for (ScheduledTask task : setTasks) {
				if (task.toString().toUpperCase().equalsIgnoreCase(fullDir.toUpperCase())) {
					return true;
				}
			}
		} catch (Exception ex) {
			logger.error("Start Job error! " + ex.toString());
		}
		return false;
	}
}
