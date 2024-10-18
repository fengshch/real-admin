package cc.realtec.real.xxljob.common.model;

import lombok.Data;

@Data
public class XxlJobTaskRequest {
    private String appName;
    private String executorHandler;
    private String executorParam;
}
