package cc.realtec.real.xxljob.service;


import cc.realtec.real.xxljob.core.model.XxlJobInfo;
import cc.realtec.real.xxljob.core.model.XxlJobUser;
import com.xxl.job.core.biz.model.ReturnT;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * core job action for xxl-job
 * 
 * @author xuxueli 2016-5-28 15:30:33
 */
public interface XxlJobService {

	/**
	 * page list
	 *
	 * @param start
	 * @param length
	 * @param jobGroup
	 * @param jobDesc
	 * @param executorHandler
	 * @param author
	 * @return
	 */
	public Map<String, Object> pageList(int start, int length, int jobGroup, int triggerStatus, String jobDesc, String executorHandler, String author);

	/**
	 * add job
	 *
	 * @param jobInfo
	 * @return
	 */
	public ReturnT<String> add(XxlJobInfo jobInfo);

	/**
	 * update job
	 *
	 * @param jobInfo
	 * @return
	 */
	public ReturnT<String> update(XxlJobInfo jobInfo);

	/**
	 * remove job
	 * 	 *
	 * @param id
	 * @return
	 */
	public ReturnT<String> remove(int id);

	/**
	 * start job
	 *
	 * @param id
	 * @return
	 */
	public ReturnT<String> start(int id);

	/**
	 * stop job
	 *
	 * @param id
	 * @return
	 */
	public ReturnT<String> stop(int id);

	/**
	 * trigger
	 *
	 * @param loginUser
	 * @param jobId
	 * @param executorParam
	 * @param addressList
	 * @return
	 */
	public ReturnT<String> trigger(XxlJobUser loginUser, int jobId, String executorParam, String addressList);

	 ReturnT<String> trigger(String appName,String executorHandler, String executorParam);

	/**
	 * dashboard info
	 *
	 * @return
	 */
	public Map<String,Object> dashboardInfo();

	/**
	 * chart info
	 *
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public ReturnT<Map<String,Object>> chartInfo(LocalDateTime startDate, LocalDateTime endDate);

	ReturnT<String> start(String appName, String executorHandler);

	ReturnT<String> stop(String appName, String executorHandler);
}
