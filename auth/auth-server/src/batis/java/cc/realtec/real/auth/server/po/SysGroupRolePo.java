package cc.realtec.real.auth.server.po;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

import java.io.Serial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  实体类。
 *
 * @author bill
 * @since 2024-10-24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("sys_group_role")
public class SysGroupRolePo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String groupId;

    @Id
    private String roleId;

    private Boolean system;

    private String createdBy;

    private String updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
