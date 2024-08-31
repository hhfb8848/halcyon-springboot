package com.halcyon.dto.roleMenu;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

/**
 * @author sjh
 * @version 1.0
 * @date 2024-07-05 20:25
 * @description: 菜单分配
 */
@Data
public class RoleMenuAssignDTO {

    @NotNull(message = "角色ID不能为空")
    private Long roleId;

    private Set<Long> menuIds;
}
