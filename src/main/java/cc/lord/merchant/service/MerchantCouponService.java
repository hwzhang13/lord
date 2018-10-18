package cc.lord.merchant.service;

import cc.lord.common.domain.QueryRequest;
import cc.lord.common.service.IService;
import cc.lord.merchant.domain.MerchantCoupon;

import java.util.List;

public interface MerchantCouponService extends IService<MerchantCoupon> {


    List<MerchantCoupon> findMerchantCouponList(MerchantCoupon merchantCoupon, QueryRequest request);

    void deleteMerchantCouponById(Long couponId);

    void disableMerchantCoupon(MerchantCoupon merchantCoupon);

    void auditedMerchantCoupon(MerchantCoupon merchantCoupon);

    void notThroughMerchantCoupon(MerchantCoupon merchantCoupon);

    List<MerchantCoupon> findMerchantCouponByMchId(Long mchId);

    void modifyMerchantCoupon(MerchantCoupon merchantCoupon);
}
