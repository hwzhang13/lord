package cc.lord.merchant.service.impl;


import cc.lord.common.domain.QueryRequest;
import cc.lord.common.service.impl.BaseService;
import cc.lord.merchant.dao.MerchantMapper;
import cc.lord.merchant.domain.*;
import cc.lord.merchant.service.MchLabelService;
import cc.lord.merchant.service.MerchantCouponService;
import cc.lord.merchant.service.MerchantService;
import cc.lord.system.domain.SysLog;
import cc.lord.system.domain.User;
import org.apache.commons.lang3.StringUtils;
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
public class MerchantServiceImpl extends BaseService<Merchant> implements MerchantService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MerchantMapper merchantMapper;

    @Autowired
    private MerchantCouponService merchantCouponService;

    @Autowired
    private MchLabelService mchLabelService;

    @Override
    public List<MerchantVo> findMerchantList(Merchant merchant, QueryRequest request) {
        if (StringUtils.isNotBlank(merchant.getMchName())){
            merchant.setMchName("%"+merchant.getMchName()+"%");
        }

        List<MerchantVo> merchantVos=null;
        try {
            merchantVos=merchantMapper.findMerchantList(merchant);
        }catch (Exception e){
            log.error("获取商户失败", e);
            return new ArrayList<>();
        }
        /*try {
            for (MerchantVo merchantVo:merchantVos) {
                //商户优惠
                merchantVo.setMerchantCoupons(merchantCouponService.findMerchantCouponByMchId(merchantVo.getMchId()));

                merchantVo.setMchLabelVos(mchLabelService.findMchLabelVoList(merchantVo.getMchId()));
                merchantVos.add(merchantVo);
            }

        } catch (Exception e) {
            log.error("获取商户失败", e);
            return new ArrayList<>();
        }*/
        return merchantVos;
    }

    @Override
    public MerchantVo findMerchantById(Long merchantId) {

        List<MerchantCoupon> merchantCoupons=null;
        try {
            merchantCoupons=merchantCouponService.findMerchantCouponByMchId(merchantId);
        }catch (Exception e){
            log.error("error", e);
            return new MerchantVo();
        }
        try {
            MerchantVo merchantVo=this.merchantMapper.findMerchantById(merchantId);
            merchantVo.setMerchantCoupons(merchantCoupons);
            merchantVo.setMchLabelVos(mchLabelService.findMchLabelVoList(merchantId));
            return merchantVo;
        } catch (Exception e) {
            log.error("error", e);
            return new MerchantVo();
        }

    }

    @Override
    public void addMerchant(MerchantVo merchant) {
        try {
            merchant.setMchCreateDate(new Date());
            this.merchantMapper.insert(merchant);
        }catch (Exception e){
            log.error("error", e);
        }

        for (MchLabel mchLabel:merchant.getMchLabelVos()) {
            try {
                mchLabel.setMchId(merchant.getMchId());
                this.mchLabelService.addMchLabel(mchLabel);
            }catch (Exception e){
                log.error("error", e);
            }
        }
    }

    @Override
    public void modifyMerchant(MerchantVo merchant) {
        try {
            this.merchantMapper.updateByPrimaryKeySelective(merchant);
        }catch (Exception e){
            log.error("error",e);
        }

        try {
            this.mchLabelService.removeLabel(merchant.getMchId());
        }catch (Exception e){
            log.error("error",e);
        }

        for (MchLabel mchLabel:merchant.getMchLabelVos()) {
            try {
                this.mchLabelService.addMchLabel(mchLabel);
            }catch (Exception e){
                log.error("error", e);
            }
        }
    }

    @Override
    public void removeMerchant(Long merchantId) {
        try {
            this.merchantMapper.deleteMerchantById(merchantId);
        }catch (Exception e){
            log.error("error",e);
        }
    }

    @Override
    public void auditedMerchant(Merchant merchant) {
        try {
            this.merchantMapper.updateMerchantStatusById(merchant.getMchId(),merchant.getMchStatus(),merchant.getMchComments());
        }catch (Exception e){
            log.error("error",e);
        }
    }

    @Override
    public void notThroughMerchant(Merchant merchant) {
        try {
            this.merchantMapper.updateMerchantStatusById(merchant.getMchId(),merchant.getMchStatus(),merchant.getMchComments());
        }catch (Exception e){
            log.error("error",e);
        }
    }

    @Override
    public void disableMerchant(Merchant merchant) {
        try {
            this.merchantMapper.updateByPrimaryKeySelective(merchant);
        }catch (Exception e){
            log.error("error",e);
        }
    }
}
