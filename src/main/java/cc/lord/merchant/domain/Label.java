package cc.lord.merchant.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_label")
public class Label {
    @Id
    @Column(name = "LABEL_ID")
    private Long labelId;

    @Column(name = "LABEL_TYPE")
    private Integer labelType;

    /**
     * 标签名称
     */
    @Column(name = "LABEL_NAME")
    private String labelName;

    @Column(name = "LABEL_CREATE_DATE")
    private Date labelCreateDate;

    @Column(name = "LABEL_UPDATE_DATE")
    private Date labelUpdateDate;

    /**
     * 是否禁用  1禁用
     */
    @Column(name = "LABEL_DISABLE")
    private Integer labelDisable;

    /**
     * 0待审核1已审核2未通过
     */
    @Column(name = "LABEL_STATUS")
    private Integer labelStatus;

    @Column(name = "LABEL_DELETE")
    private Integer labelDelete;

    /**
     * @return LABEL_ID
     */
    public Long getLabelId() {
        return labelId;
    }

    /**
     * @param labelId
     */
    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    /**
     * @return LABEL_TYPE
     */
    public Integer getLabelType() {
        return labelType;
    }

    /**
     * @param labelType
     */
    public void setLabelType(Integer labelType) {
        this.labelType = labelType;
    }

    /**
     * 获取标签名称
     *
     * @return LABEL_NAME - 标签名称
     */
    public String getLabelName() {
        return labelName;
    }

    /**
     * 设置标签名称
     *
     * @param labelName 标签名称
     */
    public void setLabelName(String labelName) {
        this.labelName = labelName == null ? null : labelName.trim();
    }

    /**
     * @return LABEL_CREATE_DATE
     */
    public Date getLabelCreateDate() {
        return labelCreateDate;
    }

    /**
     * @param labelCreateDate
     */
    public void setLabelCreateDate(Date labelCreateDate) {
        this.labelCreateDate = labelCreateDate;
    }

    /**
     * @return LABEL_UPDATE_DATE
     */
    public Date getLabelUpdateDate() {
        return labelUpdateDate;
    }

    /**
     * @param labelUpdateDate
     */
    public void setLabelUpdateDate(Date labelUpdateDate) {
        this.labelUpdateDate = labelUpdateDate;
    }

    /**
     * 获取是否禁用  1禁用
     *
     * @return LABEL_DISABLE - 是否禁用  1禁用
     */
    public Integer getLabelDisable() {
        return labelDisable;
    }

    /**
     * 设置是否禁用  1禁用
     *
     * @param labelDisable 是否禁用  1禁用
     */
    public void setLabelDisable(Integer labelDisable) {
        this.labelDisable = labelDisable;
    }

    /**
     * 获取0寰呭鏍?1宸插鏍?2鏈€氳繃
     *
     * @return LABEL_STATUS - 0寰呭鏍?1宸插鏍?2鏈€氳繃
     */
    public Integer getLabelStatus() {
        return labelStatus;
    }

    /**
     * 设置0寰呭鏍?1宸插鏍?2鏈€氳繃
     *
     * @param labelStatus 0寰呭鏍?1宸插鏍?2鏈€氳繃
     */
    public void setLabelStatus(Integer labelStatus) {
        this.labelStatus = labelStatus;
    }

    /**
     * @return LABEL_DELETE
     */
    public Integer getLabelDelete() {
        return labelDelete;
    }

    /**
     * @param labelDelete
     */
    public void setLabelDelete(Integer labelDelete) {
        this.labelDelete = labelDelete;
    }
}