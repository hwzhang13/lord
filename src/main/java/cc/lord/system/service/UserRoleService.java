package cc.lord.system.service;

import cc.lord.common.service.IService;
import cc.lord.system.domain.UserRole;

public interface UserRoleService extends IService<UserRole> {

	void deleteUserRolesByRoleId(String roleIds);

	void deleteUserRolesByUserId(String userIds);
}
