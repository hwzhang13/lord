package cc.lord.merchant.domain;

import javax.persistence.*;

@Table(name = "t_mch_label")
public class MchLabel {
    @Column(name = "LABEL_ID")
    private Long labelId;

    @Column(name = "MCH_ID")
    private Long mchId;

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
}