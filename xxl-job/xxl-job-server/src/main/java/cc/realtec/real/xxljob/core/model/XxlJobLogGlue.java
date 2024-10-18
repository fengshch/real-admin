package cc.realtec.real.xxljob.core.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * xxl-job log for glue, used to track job code process
 * @author xuxueli 2016-5-19 17:57:46
 */
@Data
public class XxlJobLogGlue {
	
	private int id;
	private int jobId;				// 任务主键ID
	private String glueType;		// GLUE类型	#com.xxl.job.core.glue.GlueTypeEnum
	private String glueSource;
	private String glueRemark;
	private LocalDateTime addTime;
	private LocalDateTime updateTime;

}

