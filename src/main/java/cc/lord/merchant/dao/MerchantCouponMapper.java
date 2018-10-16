package cc.lord.merchant.dao;

import cc.lord.common.config.MyMapper;
import cc.lord.merchant.domain.MerchantCoupon;

import java.util.List;

public interface MerchantCouponMapper extends MyMapper<MerchantCoupon> {

    void deleteMerchantCouponById(Long couponId);

    List<MerchantCoupon> selectMerchantCouponByMchId(Long mchId);
}