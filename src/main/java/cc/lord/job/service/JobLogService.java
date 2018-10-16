package cc.lord.job.service;

import java.util.List;

import cc.lord.common.service.IService;
import cc.lord.job.domain.JobLog;

public interface JobLogService extends IService<JobLog>{

	List<JobLog> findAllJobLogs(JobLog jobLog);

	void saveJobLog(JobLog log);
	
	void deleteBatch(String jobLogIds);
}
