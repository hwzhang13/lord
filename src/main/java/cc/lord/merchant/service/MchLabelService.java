package cc.lord.merchant.service;

import cc.lord.common.service.IService;
import cc.lord.merchant.domain.MchLabel;
import cc.lord.merchant.domain.MchLabelVo;

import java.util.List;


public interface MchLabelService extends IService<MchLabel> {

    void addMchLabel(MchLabel mchLabel);

    void removeLabel(Long mchId);

    List<MchLabelVo> findMchLabelVoList(Long mchId);
}
