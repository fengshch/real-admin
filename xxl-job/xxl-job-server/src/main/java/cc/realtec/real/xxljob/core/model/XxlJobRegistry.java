package cc.realtec.real.xxljob.core.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by xuxueli on 16/9/30.
 */
@Data
public class XxlJobRegistry {

    private int id;
    private String registryGroup;
    private String registryKey;
    private String registryValue;
    private LocalDateTime updateTime;

}
