package cc.realtec.real.auth.server.po;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import java.io.Serial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  实体类。
 *
 * @author bill
 * @since 2024-08-20
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("jobrunr_backgroundjobservers")
public class JobrunrBackgroundjobserversPO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private Integer workerpoolsize;

    private Integer pollintervalinseconds;

    private Timestamp firstheartbeat;

    private Timestamp lastheartbeat;

    private Integer running;

    private Long systemtotalmemory;

    private Long systemfreememory;

    private BigDecimal systemcpuload;

    private Long processmaxmemory;

    private Long processfreememory;

    private Long processallocatedmemory;

    private BigDecimal processcpuload;

    private String deletesucceededjobsafter;

    private String permanentlydeletejobsafter;

    private String name;

}
