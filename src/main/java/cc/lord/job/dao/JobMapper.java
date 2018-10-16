package cc.lord.job.dao;

import java.util.List;

import cc.lord.common.config.MyMapper;
import cc.lord.job.domain.Job;

public interface JobMapper extends MyMapper<Job> {
	
	List<Job> queryList();
}