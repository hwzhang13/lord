package cc.lord.merchant.dao;

import cc.lord.common.config.MyMapper;
import cc.lord.merchant.domain.MchLabel;
import cc.lord.merchant.domain.MchLabelVo;

import java.util.List;

public interface MchLabelMapper extends MyMapper<MchLabel> {

    void removeLabelByMchId(Long mchId);

    List<MchLabelVo> findMchLabelVoList(Long mchId);

    int insertMchLabel(MchLabel mchLabel);
}