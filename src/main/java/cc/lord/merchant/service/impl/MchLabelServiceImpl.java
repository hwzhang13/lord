package cc.lord.merchant.service.impl;

import cc.lord.common.service.impl.BaseService;
import cc.lord.merchant.dao.MchLabelMapper;
import cc.lord.merchant.domain.MchLabel;
import cc.lord.merchant.domain.MchLabelVo;
import cc.lord.merchant.service.MchLabelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MchLabelServiceImpl extends BaseService<MchLabel> implements MchLabelService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MchLabelMapper mchLabelMapper;

    @Override
    public void addMchLabel(MchLabel mchLabel) {
        try {
            this.mchLabelMapper.insertSelective(mchLabel);
        }catch (Exception e){
            log.error("error",e.getMessage());
        }
    }

    @Override
    public void removeLabel(Long mchId) {
        try {
            this.mchLabelMapper.removeLabelByMchId(mchId);
        }catch (Exception e){
            log.error("error",e.getMessage());
        }
    }

    @Override
    public List<MchLabelVo> findMchLabelVoList(Long mchId) {
        try {
            return this.mchLabelMapper.findMchLabelVoList(mchId);
        }catch (Exception e){
            log.error("error",e.getMessage());
            return new ArrayList<>();
        }
    }
}
