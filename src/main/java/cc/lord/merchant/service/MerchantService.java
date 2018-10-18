package cc.lord.merchant.service;


import cc.lord.common.domain.QueryRequest;
import cc.lord.common.service.IService;
import cc.lord.merchant.domain.Merchant;
import cc.lord.merchant.domain.MerchantVo;


import java.util.List;

public interface MerchantService extends IService<Merchant> {

    List<MerchantVo> findMerchantList(Merchant merchant, QueryRequest request)throws Exception;

    MerchantVo findMerchantById(Long merchantId)throws Exception;

    void addMerchant(MerchantVo merchant)throws Exception;

    void modifyMerchant(MerchantVo merchant)throws Exception;

    void removeMerchant(Long merchantId)throws Exception;

    void auditedMerchant(Merchant merchant)throws Exception;

    void notThroughMerchant(Merchant merchant)throws Exception;

    void disableMerchant(Merchant merchant)throws Exception;
}
