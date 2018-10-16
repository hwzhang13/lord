package cc.lord.merchant.domain;

import java.util.Date;
import java.util.List;

public class MerchantVo extends Merchant {

    private List<MerchantCoupon> merchantCoupons;

    private List<MchLabelVo> mchLabelVos;

    public MerchantVo(){

    }
    public List<MchLabelVo> getMchLabelVos() {
        return mchLabelVos;
    }

    public void setMchLabelVos(List<MchLabelVo> mchLabelVos) {
        this.mchLabelVos = mchLabelVos;
    }

    public List<MerchantCoupon> getMerchantCoupons() {
        return merchantCoupons;
    }

    public void setMerchantCoupons(List<MerchantCoupon> merchantCoupons) {
        this.merchantCoupons = merchantCoupons;
    }
}
