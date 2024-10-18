package cc.realtec.real.xxljob.core.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * xxl-job log, used to track trigger process
 * @author xuxueli  2015-12-19 23:19:09
 */
@Data
public class XxlJobLog {
	
	private long id;
	
	// job info
	private int jobGroup;
	private int jobId;

	// execute info
	private String executorAddress;
	private String executorHandler;
	private String executorParam;
	private String executorShardingParam;
	private int executorFailRetryCount;
	
	// trigger info
	private LocalDateTime triggerTime;
	private int triggerCode;
	private String triggerMsg;
	
	// handle info
	private LocalDateTime handleTime;
	private int handleCode;
	private String handleMsg;

	// alarm info
	private int alarmStatus;

}
