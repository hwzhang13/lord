package cc.lord.system.service;

import java.util.List;

import cc.lord.system.domain.UserOnline;

public interface SessionService {

	List<UserOnline> list();

	boolean forceLogout(String sessionId);
}
