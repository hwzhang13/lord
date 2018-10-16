package cc.lord.system.dao;

import java.util.List;

import cc.lord.common.config.MyMapper;
import cc.lord.system.domain.User;
import cc.lord.system.domain.UserWithRole;

public interface UserMapper extends MyMapper<User> {

	List<User> findUserWithDept(User user);
	
	List<UserWithRole> findUserWithRole(Long userId);
	
	User findUserProfile(User user);
}