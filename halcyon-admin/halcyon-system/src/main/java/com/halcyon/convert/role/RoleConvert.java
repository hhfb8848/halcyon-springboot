package com.halcyon.convert.role;

import com.halcyon.dao.entity.SysRole;
import com.halcyon.vo.account.AccountRoleVO;
import com.halcyon.vo.role.RoleSimpleVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author sjh
 * @version 1.0
 * @date 2024-08-01 21:56
 * @description: 角色转换
 */
@Mapper
public interface RoleConvert {

    RoleConvert INSTANCE = Mappers.getMapper(RoleConvert.class);

    List<RoleSimpleVO> convertSimpleList(List<SysRole> roleList);

    List<AccountRoleVO> convertAccountRoleList(List<SysRole> roleList);
}
