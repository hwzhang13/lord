package cc.lord.merchant.service;

import cc.lord.common.service.IService;
import cc.lord.merchant.domain.Label;

import java.util.List;

public interface LabelService extends IService<Label> {

    List<Label> findAllLabelList(Label label);

    void addLabel(Label label);

    void modifyLabel(Label label);

    void removeLabel(Label label);

    void disableLabel(Label label);
}
