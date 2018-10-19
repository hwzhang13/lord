package cc.lord.merchant.domain;

import java.util.Date;
import java.util.List;

public class MerchantVo extends Merchant {

    private List<MerchantCoupon> merchantCoupons;

    private List<MchLabelVo> mchLabelVos;

    private String labelVos;


    public MerchantVo(){

    }

    public String getLabelVos() {
        return labelVos;
    }

    public void setLabelVos(String labelVos) {
        this.labelVos = labelVos;
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
