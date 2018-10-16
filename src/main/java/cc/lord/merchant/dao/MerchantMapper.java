package cc.lord.merchant.dao;

import cc.lord.common.config.MyMapper;
import cc.lord.merchant.domain.Merchant;
import cc.lord.merchant.domain.MerchantVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MerchantMapper extends MyMapper<Merchant> {

    List<MerchantVo> findMerchantList(Merchant merchant);

    MerchantVo findMerchantById(Long merchantId);

    int deleteMerchantById(Long merchantId);

    int updateMerchantStatusById(@Param("mchId")Long mchId,@Param("status") Integer status,@Param("mchComments")String mchComments);
}