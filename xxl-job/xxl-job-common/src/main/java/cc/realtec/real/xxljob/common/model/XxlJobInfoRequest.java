package cc.realtec.real.xxljob.common.model;

import lombok.Data;

@Data
public class XxlJobInfoRequest {
    private String AppName;
    private String jobDesc;
    private String author;
    private String executorHandler;
    private String scheduleType;
    private String scheduleConf;
    private String executorParam;
}
