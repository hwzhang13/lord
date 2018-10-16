package cc.lord.system.dao;

import java.util.List;

import cc.lord.common.config.MyMapper;
import cc.lord.system.domain.Dept;

public interface DeptMapper extends MyMapper<Dept> {
	
	// 删除父节点，子节点变成顶级节点（根据实际业务调整）
	void changeToTop(List<String> deptIds);
}