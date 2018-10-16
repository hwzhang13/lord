package cc.lord.merchant.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_merchant_coupon")
public class MerchantCoupon {
    @Id
    @Column(name = "COUPON_ID")
    private Long couponId;

    /**
     * 商户id
     */
    @Column(name = "MCH_ID")
    private Long mchId;

    @Column(name = "MCH_NAME")
    private String mchName;

    /**
     * 优惠类型
     */
    @Column(name = "COUPON_TYPE")
    private Integer couponType;

    /**
     * 优惠金额
     */
    @Column(name = "COUPON_AMOUNT")
    private Double couponAmount;

    /**
     * 使用金额
     */
    @Column(name = "COUPON_USAGE_AMOUNT")
    private Double couponUsageAmount;

    /**
     * 优惠支付方式
     */
    @Column(name = "COUPON_PAYMENT_TYPE")
    private Integer couponPaymentType;

    /**
     * 优惠支付方式
     */
    @Column(name = "COUPON_PAYMENT_TYPE_NAME")
    private String couponPaymentTypeName;

    /**
     * 优惠二维码
     */
    @Column(name = "COUPON_QR_CODE")
    private String couponQrCode;

    /**
     * 优惠开始时间
     */
    @Column(name = "COUPON_TIME_BEGIN")
    private Date couponTimeBegin;

    /**
     * 优惠结束时间
     */
    @Column(name = "COUPON_TIME_END")
    private Date couponTimeEnd;

    /**
     * 优惠创建时间
     */
    @Column(name = "COUPON_CREATE_DATE")
    private Date couponCreateDate;

    /**
     * CURRENT_TIMESTAMP
     */
    @Column(name = "COUPON_UPDATE_DATE")
    private Date couponUpdateDate;

    /**
     * 0 待审核1审核通过2未通过
     */
    @Column(name = "COUPON_STATUS")
    private Integer couponStatus;

    /**
     * 1删除
     */
    @Column(name = "COUPON_DELETE")
    private Integer couponDelete;

    @Column(name = "COUPON_COMMENTS")
    private String couponComments;

    @Column(name = "COUPON_DISABLE")
    private String couponDisable;

    /**
     * 0未过期 1过期
     */
    @Column(name = "COUPON_EXPIRE")
    private Integer couponExpire;

    public Integer getCouponExpire() {
        return couponExpire;
    }

    public void setCouponExpire(Integer couponExpire) {
        this.couponExpire = couponExpire;
    }

    public String getMchName() {
        return mchName;
    }

    public void setMchName(String mchName) {
        this.mchName = mchName;
    }

    /**
     * @return COUPON_ID
     */
    public Long getCouponId() {
        return couponId;
    }

    /**
     * @param couponId
     */
    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    /**
     * 获取商户id
     *
     * @return MCH_ID - 商户id
     */
    public Long getMchId() {
        return mchId;
    }

    /**
     * 设置商户id
     *
     * @param mchId 商户id
     */
    public void setMchId(Long mchId) {
        this.mchId = mchId;
    }

    /**
     * 获取优惠类型
     *
     * @return COUPON_TYPE - 优惠类型
     */
    public Integer getCouponType() {
        return couponType;
    }

    /**
     * 设置优惠类型
     *
     * @param couponType 优惠类型
     */
    public void setCouponType(Integer couponType) {
        this.couponType = couponType;
    }

    /**
     * 获取优惠金额
     *
     * @return COUPON_AMOUNT - 优惠金额
     */
    public Double getCouponAmount() {
        return couponAmount;
    }

    /**
     * 设置优惠金额
     *
     * @param couponAmount 优惠金额
     */
    public void setCouponAmount(Double couponAmount) {
        this.couponAmount = couponAmount;
    }

    /**
     * 获取使用金额
     *
     * @return COUPON_USAGE_AMOUNT - 使用金额
     */
    public Double getCouponUsageAmount() {
        return couponUsageAmount;
    }

    /**
     * 设置使用金额
     *
     * @param couponUsageAmount 使用金额
     */
    public void setCouponUsageAmount(Double couponUsageAmount) {
        this.couponUsageAmount = couponUsageAmount;
    }

    /**
     * 获取优惠支付方式
     *
     * @return COUPON_PAYMENT_TYPE - 优惠支付方式
     */
    public Integer getCouponPaymentType() {
        return couponPaymentType;
    }

    /**
     * 设置优惠支付方式
     *
     * @param couponPaymentType 优惠支付方式
     */
    public void setCouponPaymentType(Integer couponPaymentType) {
        this.couponPaymentType = couponPaymentType;
    }

    /**
     * 获取优惠支付方式
     *
     * @return COUPON_PAYMENT_TYPE_NAME - 优惠支付方式
     */
    public String getCouponPaymentTypeName() {
        return couponPaymentTypeName;
    }

    /**
     * 设置优惠支付方式
     *
     * @param couponPaymentTypeName 优惠支付方式
     */
    public void setCouponPaymentTypeName(String couponPaymentTypeName) {
        this.couponPaymentTypeName = couponPaymentTypeName == null ? null : couponPaymentTypeName.trim();
    }

    /**
     * 获取优惠二维码
     *
     * @return COUPON_QR_CODE - 优惠二维码
     */
    public String getCouponQrCode() {
        return couponQrCode;
    }

    /**
     * 设置优惠二维码
     *
     * @param couponQrCode 优惠二维码
     */
    public void setCouponQrCode(String couponQrCode) {
        this.couponQrCode = couponQrCode == null ? null : couponQrCode.trim();
    }

    /**
     * 获取优惠开始时间
     *
     * @return COUPON_TIME_BEGIN - 优惠开始时间
     */
    public Date getCouponTimeBegin() {
        return couponTimeBegin;
    }

    /**
     * 设置优惠开始时间
     *
     * @param couponTimeBegin 优惠开始时间
     */
    public void setCouponTimeBegin(Date couponTimeBegin) {
        this.couponTimeBegin = couponTimeBegin;
    }

    /**
     * 获取优惠结束时间
     *
     * @return COUPON_TIME_END - 优惠结束时间
     */
    public Date getCouponTimeEnd() {
        return couponTimeEnd;
    }

    /**
     * 设置优惠结束时间
     *
     * @param couponTimeEnd 优惠结束时间
     */
    public void setCouponTimeEnd(Date couponTimeEnd) {
        this.couponTimeEnd = couponTimeEnd;
    }

    /**
     * 获取优惠创建时间
     *
     * @return COUPON_CREATE_DATE - 优惠创建时间
     */
    public Date getCouponCreateDate() {
        return couponCreateDate;
    }

    /**
     * 设置优惠创建时间
     *
     * @param couponCreateDate 优惠创建时间
     */
    public void setCouponCreateDate(Date couponCreateDate) {
        this.couponCreateDate = couponCreateDate;
    }

    /**
     * 获取CURRENT_TIMESTAMP
     *
     * @return COUPON_UPDATE_DATE - CURRENT_TIMESTAMP
     */
    public Date getCouponUpdateDate() {
        return couponUpdateDate;
    }

    /**
     * 设置CURRENT_TIMESTAMP
     *
     * @param couponUpdateDate CURRENT_TIMESTAMP
     */
    public void setCouponUpdateDate(Date couponUpdateDate) {
        this.couponUpdateDate = couponUpdateDate;
    }

    /**
     * 获取0 待审核1审核通过2未通过
     *
     * @return COUPON_STATUS - 0 待审核1审核通过2未通过
     */
    public Integer getCouponStatus() {
        return couponStatus;
    }

    /**
     * 设置0 待审核1审核通过2未通过
     *
     * @param couponStatus 0 待审核1审核通过2未通过
     */
    public void setCouponStatus(Integer couponStatus) {
        this.couponStatus = couponStatus;
    }

    /**
     * 获取1删除
     *
     * @return COUPON_DELETE - 1删除
     */
    public Integer getCouponDelete() {
        return couponDelete;
    }

    /**
     * 设置1删除
     *
     * @param couponDelete 1删除
     */
    public void setCouponDelete(Integer couponDelete) {
        this.couponDelete = couponDelete;
    }

    public String getCouponComments() {
        return couponComments;
    }

    public void setCouponComments(String couponComments) {
        this.couponComments = couponComments;
    }

    public String getCouponDisable() {
        return couponDisable;
    }

    public void setCouponDisable(String couponDisable) {
        this.couponDisable = couponDisable;
    }
}