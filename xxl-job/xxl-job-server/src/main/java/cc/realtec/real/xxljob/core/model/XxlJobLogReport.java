package cc.realtec.real.xxljob.core.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class XxlJobLogReport {

    private int id;

    private LocalDateTime triggerDay;

    private int runningCount;
    private int sucCount;
    private int failCount;
    private LocalDateTime updateTime;
}
