package cc.lord.merchant.service;


import cc.lord.common.domain.QueryRequest;
import cc.lord.common.service.IService;
import cc.lord.merchant.domain.Merchant;
import cc.lord.merchant.domain.MerchantVo;


import java.util.List;

public interface MerchantService extends IService<Merchant> {

    List<MerchantVo> findMerchantList(Merchant merchant, QueryRequest request);

    MerchantVo findMerchantById(Long merchantId);

    void addMerchant(MerchantVo merchant);

    void modifyMerchant(MerchantVo merchant);

    void removeMerchant(Long merchantId);

    void auditedMerchant(Merchant merchant);

    void notThroughMerchant(Merchant merchant);

    void disableMerchant(Merchant merchant);
}
