package cc.lord.merchant.service.impl;

import cc.lord.common.domain.QueryRequest;
import cc.lord.common.service.impl.BaseService;
import cc.lord.merchant.dao.MerchantCouponMapper;
import cc.lord.merchant.domain.MerchantCoupon;
import cc.lord.merchant.service.MerchantCouponService;
import cc.lord.system.domain.SysLog;
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
public class MerchantCouponServiceImpl extends BaseService<MerchantCoupon> implements MerchantCouponService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MerchantCouponMapper merchantCouponMapper;

    @Override
    public List<MerchantCoupon> findMerchantCouponList(MerchantCoupon merchantCoupon, QueryRequest request) {
        try {
            Example example = new Example(SysLog.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andCondition("COUPON_DELETE=", 0);
            if (null!=merchantCoupon.getCouponStatus()) {
                criteria.andCondition("COUPON_STATUS=", merchantCoupon.getCouponStatus());
            }
            if (StringUtils.isNotBlank(merchantCoupon.getMchName())) {
                criteria.andCondition("MCH_NAME like", "%" + merchantCoupon.getMchName() + "%");
            }
            example.setOrderByClause("COUPON_CREATE_DATE desc");
            return this.selectByExample(example);
        } catch (Exception e) {
            log.error("获取商户优惠失败", e);
            return new ArrayList<>();
        }
    }

    @Override
    public void deleteMerchantCouponById(Long couponId) {
        try {
            this.merchantCouponMapper.deleteMerchantCouponById(couponId);
        }catch (Exception e){
            log.error("error", e);
        }
    }

    @Override
    public void disableMerchantCoupon(MerchantCoupon merchantCoupon) {
        try {
            this.merchantCouponMapper.updateByPrimaryKeySelective(merchantCoupon);
        }catch (Exception e){
            log.error("error", e);
        }
    }

    @Override
    public void auditedMerchantCoupon(MerchantCoupon merchantCoupon) {
        try {
            this.merchantCouponMapper.updateByPrimaryKeySelective(merchantCoupon);
        }catch (Exception e){
            log.error("error", e);
        }
    }

    @Override
    public void notThroughMerchantCoupon(MerchantCoupon merchantCoupon) {
        try {
            this.merchantCouponMapper.updateByPrimaryKeySelective(merchantCoupon);
        }catch (Exception e){
            log.error("error", e);
        }
    }

    @Override
    public List<MerchantCoupon> findMerchantCouponByMchId(Long mchId) {
        try {
            return this.merchantCouponMapper.selectMerchantCouponByMchId(mchId);
        }catch (Exception e){
            log.error("error", e);
            return new ArrayList<>();
        }
    }

    @Override
    public void modifyMerchantCoupon(MerchantCoupon merchantCoupon) {
        try {
            this.merchantCouponMapper.updateByPrimaryKeySelective(merchantCoupon);
        }catch (Exception e){
            e.printStackTrace();
            log.error("error", e);
        }
    }

    @Override
    public void addMerchantCoupon(MerchantCoupon merchantCoupon) {
        try {
            merchantCoupon.setCouponCreateDate(new Date());
            this.merchantCouponMapper.insertSelective(merchantCoupon);
        }catch (Exception e){
            e.printStackTrace();
            log.error("error", e);
        }
    }
}
