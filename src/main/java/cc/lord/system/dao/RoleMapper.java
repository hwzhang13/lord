package cc.lord.system.dao;

import java.util.List;

import cc.lord.common.config.MyMapper;
import cc.lord.system.domain.Role;
import cc.lord.system.domain.RoleWithMenu;

public interface RoleMapper extends MyMapper<Role> {
	
	List<Role> findUserRole(String userName);
	
	List<RoleWithMenu> findById(Long roleId);
}