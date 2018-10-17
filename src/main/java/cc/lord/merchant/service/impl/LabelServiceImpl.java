package cc.lord.merchant.service.impl;

import cc.lord.common.service.impl.BaseService;
import cc.lord.merchant.dao.LabelMapper;
import cc.lord.merchant.domain.Label;
import cc.lord.merchant.service.LabelService;
import cc.lord.system.domain.SysLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class LabelServiceImpl extends BaseService<Label> implements LabelService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LabelMapper labelMapper;

    @Override
    public List<Label> findAllLabelList(Label label) {
        try {
            Example example = new Example(SysLog.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andCondition("LABEL_DELETE =", 0);
            if (null!=label.getLabelStatus()) {
                criteria.andCondition("LABEL_STATUS =", label.getLabelStatus());
            }
            if (null!=label.getLabelType()) {
                criteria.andCondition("LABEL_TYPE =",  + label.getLabelType());
            }
            example.setOrderByClause("LABEL_CREATE_DATE desc");
            return this.selectByExample(example);
        } catch (Exception e) {
            log.error("获取标签列表失败", e);
            return new ArrayList<>();
        }
    }

    @Override
    public void addLabel(Label label) {
        label.setLabelCreateDate(new Date());
        try {
            this.labelMapper.insertSelective(label);
        }catch (Exception e){
            log.error("error",e.getMessage());
        }
    }

    @Override
    public void modifyLabel(Label label) {
        try {
            this.labelMapper.updateByPrimaryKeySelective(label);
        }catch (Exception e){
            log.error("error",e.getMessage());
        }
    }

    @Override
    public void removeLabel(Label label) {
        try {
            label.setLabelDelete(1);
            this.labelMapper.updateByPrimaryKeySelective(label);
        }catch (Exception e){
            log.error("error",e.getMessage());
        }
    }

    @Override
    public void disableLabel(Label label) {
        try {
            this.labelMapper.updateByPrimaryKeySelective(label);
        }catch (Exception e){
            log.error("error",e.getMessage());
        }
    }
}
