package cc.lord.merchant.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_merchant")
public class Merchant {
    @Id
    @Column(name = "MCH_ID")
    private Long mchId;

    /**
     * 商户名称
     */
    @Column(name = "MCH_NAME")
    private String mchName;

    /**
     * 联系人
     */
    @Column(name = "MCH_CONTACTS")
    private String mchContacts;

    /**
     * 联系人电话
     */
    @Column(name = "MCH_CONTACTS_TELEPHONE")
    private String mchContactsTelephone;

    /**
     * 营业执照
     */
    @Column(name = "MCH_BUSINESSLICENCE")
    private String mchBusinesslicence;

    /**
     * 营业执照号
     */
    @Column(name = "MCH_BUSINESSLICENCE_NUMBE")
    private String mchBusinesslicenceNumbe;

    /**
     * 商户图片
     */
    @Column(name = "MCH_IMG")
    private String mchImg;

    /**
     * 商户类别
     */
    @Column(name = "MCH_TYPE")
    private Integer mchType;

    /**
     * 商户电话
     */
    @Column(name = "MCH_TELEPHONE")
    private String mchTelephone;

    /**
     * 商户地址
     */
    @Column(name = "MCH_ADDRESS")
    private String mchAddress;

    /**
     * 工作日开始时间
     */
    @Column(name = "MCH_WEEKDAY_BEGIN")
    private String mchWeekdayBegin;

    /**
     * 工作日结束时间
     */
    @Column(name = "MCH_WEEKDAY_END")
    private String mchWeekdayEnd;

    /**
     * 周末开始时间
     */
    @Column(name = "MCH_WEEKEND_BEGIN")
    private String mchWeekendBegin;

    /**
     * 周末结束时间
     */
    @Column(name = "MCH_WEEKEND_END")
    private String mchWeekendEnd;

    /**
     * 人均消费
     */
    @Column(name = "MCH_PER_CAPITA_CONSUME")
    private String mchPerCapitaConsume;

    /**
     * 0 待审核 1已通过  2未通过
     */
    @Column(name = "MCH_STATUS")
    private Integer mchStatus;

    /**
     * 创建时间
     */
    @Column(name = "MCH_CREATE_DATE")
    private Date mchCreateDate;

    /**
     * CURRENT_TIMESTAMP
     */
    @Column(name = "MCH_UPDATE_DATE")
    private Date mchUpdateDate;

    /**
     * 0未删除 1已删除
     */
    @Column(name = "MCH_DELETE")
    private Integer mchDelete;

    @Column(name = "MCH_COMMENTS")
    private String mchComments;

    @Column(name = "MCH_DISABLE")
    private Integer mchDisable;

    @Column(name = "MCH_MAP")
    private String mchMap;

    public String getMchMap() {
        return mchMap;
    }

    public void setMchMap(String mchMap) {
        this.mchMap = mchMap;
    }

    public String getMchComments() {
        return mchComments;
    }

    public void setMchComments(String mchComments) {
        this.mchComments = mchComments;
    }

    public Integer getMchDisable() {
        return mchDisable;
    }

    public void setMchDisable(Integer mchDisable) {
        this.mchDisable = mchDisable;
    }

    /**
     * @return MCH_ID
     */
    public Long getMchId() {
        return mchId;
    }

    /**
     * @param mchId
     */
    public void setMchId(Long mchId) {
        this.mchId = mchId;
    }

    /**
     * 获取商户名称
     *
     * @return MCH_NAME - 商户名称
     */
    public String getMchName() {
        return mchName;
    }

    /**
     * 设置商户名称
     *
     * @param mchName 商户名称
     */
    public void setMchName(String mchName) {
        this.mchName = mchName == null ? null : mchName.trim();
    }

    /**
     * 获取联系人
     *
     * @return MCH_CONTACTS - 联系人
     */
    public String getMchContacts() {
        return mchContacts;
    }

    /**
     * 设置联系人
     *
     * @param mchContacts 联系人
     */
    public void setMchContacts(String mchContacts) {
        this.mchContacts = mchContacts == null ? null : mchContacts.trim();
    }

    /**
     * 获取联系人电话
     *
     * @return MCH_CONTACTS_TELEPHONE - 联系人电话
     */
    public String getMchContactsTelephone() {
        return mchContactsTelephone;
    }

    /**
     * 设置联系人电话
     *
     * @param mchContactsTelephone 联系人电话
     */
    public void setMchContactsTelephone(String mchContactsTelephone) {
        this.mchContactsTelephone = mchContactsTelephone == null ? null : mchContactsTelephone.trim();
    }

    /**
     * 获取营业执照
     *
     * @return MCH_BUSINESSLICENCE - 营业执照
     */
    public String getMchBusinesslicence() {
        return mchBusinesslicence;
    }

    /**
     * 设置营业执照
     *
     * @param mchBusinesslicence 营业执照
     */
    public void setMchBusinesslicence(String mchBusinesslicence) {
        this.mchBusinesslicence = mchBusinesslicence == null ? null : mchBusinesslicence.trim();
    }

    /**
     * 获取营业执照号
     *
     * @return MCH_BUSINESSLICENCE_NUMBE - 营业执照号
     */
    public String getMchBusinesslicenceNumbe() {
        return mchBusinesslicenceNumbe;
    }

    /**
     * 设置营业执照号
     *
     * @param mchBusinesslicenceNumbe 营业执照号
     */
    public void setMchBusinesslicenceNumbe(String mchBusinesslicenceNumbe) {
        this.mchBusinesslicenceNumbe = mchBusinesslicenceNumbe == null ? null : mchBusinesslicenceNumbe.trim();
    }

    /**
     * 获取商户图片
     *
     * @return MCH_IMG - 商户图片
     */
    public String getMchImg() {
        return mchImg;
    }

    /**
     * 设置商户图片
     *
     * @param mchImg 商户图片
     */
    public void setMchImg(String mchImg) {
        this.mchImg = mchImg == null ? null : mchImg.trim();
    }

    /**
     * 获取商户类别
     *
     * @return MCH_TYPE - 商户类别
     */
    public Integer getMchType() {
        return mchType;
    }

    /**
     * 设置商户类别
     *
     * @param mchType 商户类别
     */
    public void setMchType(Integer mchType) {
        this.mchType = mchType;
    }

    /**
     * 获取商户电话
     *
     * @return MCH_TELEPHONE - 商户电话
     */
    public String getMchTelephone() {
        return mchTelephone;
    }

    /**
     * 设置商户电话
     *
     * @param mchTelephone 商户电话
     */
    public void setMchTelephone(String mchTelephone) {
        this.mchTelephone = mchTelephone == null ? null : mchTelephone.trim();
    }

    /**
     * 获取商户地址
     *
     * @return MCH_ADDRESS - 商户地址
     */
    public String getMchAddress() {
        return mchAddress;
    }

    /**
     * 设置商户地址
     *
     * @param mchAddress 商户地址
     */
    public void setMchAddress(String mchAddress) {
        this.mchAddress = mchAddress == null ? null : mchAddress.trim();
    }

    /**
     * 获取工作日开始时间
     *
     * @return MCH_WEEKDAY_BEGIN - 工作日开始时间
     */
    public String getMchWeekdayBegin() {
        return mchWeekdayBegin;
    }

    /**
     * 设置工作日开始时间
     *
     * @param mchWeekdayBegin 工作日开始时间
     */
    public void setMchWeekdayBegin(String mchWeekdayBegin) {
        this.mchWeekdayBegin = mchWeekdayBegin == null ? null : mchWeekdayBegin.trim();
    }

    /**
     * 获取工作日结束时间
     *
     * @return MCH_WEEKDAY_END - 工作日结束时间
     */
    public String getMchWeekdayEnd() {
        return mchWeekdayEnd;
    }

    /**
     * 设置工作日结束时间
     *
     * @param mchWeekdayEnd 工作日结束时间
     */
    public void setMchWeekdayEnd(String mchWeekdayEnd) {
        this.mchWeekdayEnd = mchWeekdayEnd == null ? null : mchWeekdayEnd.trim();
    }

    /**
     * 获取周末开始时间
     *
     * @return MCH_WEEKEND_BEGIN - 周末开始时间
     */
    public String getMchWeekendBegin() {
        return mchWeekendBegin;
    }

    /**
     * 设置周末开始时间
     *
     * @param mchWeekendBegin 周末开始时间
     */
    public void setMchWeekendBegin(String mchWeekendBegin) {
        this.mchWeekendBegin = mchWeekendBegin == null ? null : mchWeekendBegin.trim();
    }

    /**
     * 获取周末结束时间
     *
     * @return MCH_WEEKEND_END - 周末结束时间
     */
    public String getMchWeekendEnd() {
        return mchWeekendEnd;
    }

    /**
     * 设置周末结束时间
     *
     * @param mchWeekendEnd 周末结束时间
     */
    public void setMchWeekendEnd(String mchWeekendEnd) {
        this.mchWeekendEnd = mchWeekendEnd == null ? null : mchWeekendEnd.trim();
    }

    /**
     * 获取人均消费
     *
     * @return MCH_PER_CAPITA_CONSUME - 人均消费
     */
    public String getMchPerCapitaConsume() {
        return mchPerCapitaConsume;
    }

    /**
     * 设置人均消费
     *
     * @param mchPerCapitaConsume 人均消费
     */
    public void setMchPerCapitaConsume(String mchPerCapitaConsume) {
        this.mchPerCapitaConsume = mchPerCapitaConsume == null ? null : mchPerCapitaConsume.trim();
    }

    /**
     * 获取0 待审核 1宸查€氳繃  2鏈€氳繃
     *
     * @return MCH_STATUS - 0 待审核 1宸查€氳繃  2鏈€氳繃
     */
    public Integer getMchStatus() {
        return mchStatus;
    }

    /**
     * 设置0 待审核 1宸查€氳繃  2鏈€氳繃
     *
     * @param mchStatus 0 待审核 1宸查€氳繃  2鏈€氳繃
     */
    public void setMchStatus(Integer mchStatus) {
        this.mchStatus = mchStatus;
    }

    /**
     * 获取创建时间
     *
     * @return MCH_CREATE_DATE - 创建时间
     */
    public Date getMchCreateDate() {
        return mchCreateDate;
    }

    /**
     * 设置创建时间
     *
     * @param mchCreateDate 创建时间
     */
    public void setMchCreateDate(Date mchCreateDate) {
        this.mchCreateDate = mchCreateDate;
    }

    /**
     * 获取CURRENT_TIMESTAMP
     *
     * @return MCH_UPDATE_DATE - CURRENT_TIMESTAMP
     */
    public Date getMchUpdateDate() {
        return mchUpdateDate;
    }

    /**
     * 设置CURRENT_TIMESTAMP
     *
     * @param mchUpdateDate CURRENT_TIMESTAMP
     */
    public void setMchUpdateDate(Date mchUpdateDate) {
        this.mchUpdateDate = mchUpdateDate;
    }

    /**
     * 获取0未删除 1已删除
     *
     * @return MCH_DELETE - 0未删除 1已删除
     */
    public Integer getMchDelete() {
        return mchDelete;
    }

    /**
     * 设置0未删除 1已删除
     *
     * @param mchDelete 0未删除 1已删除
     */
    public void setMchDelete(Integer mchDelete) {
        this.mchDelete = mchDelete;
    }
}