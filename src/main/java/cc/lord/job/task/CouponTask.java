package cc.lord.job.task;

import cc.lord.common.annotation.CronTag;
import cc.lord.merchant.domain.MerchantCoupon;
import cc.lord.merchant.service.MerchantCouponService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@CronTag("couponTask")
public class CouponTask {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private static final Integer EXPIRE=1;


    @Autowired
    private MerchantCouponService merchantCouponService;

    //@Scheduled(cron = "0 0/1 * * * ? ")

    /**
     * 执行优惠过期任务
     */
    private void executeCouponExpireTask(){
        log.info("执行优惠过期任务:任务开始");
        //查询审核通过的优惠券
        List<MerchantCoupon> merchantCoupons=null;
        try {
            MerchantCoupon merchantCoupon=new MerchantCoupon();
            merchantCoupon.setCouponStatus(1);
            merchantCoupons=merchantCouponService.findMerchantCouponList(merchantCoupon,null);
        }catch (Exception e){
            e.printStackTrace();
            log.error("执行优惠过期任务异常:",e.getMessage());
            log.error("执行优惠过期任务:查询优惠信息异常");
            return;
        }

        if (CollectionUtils.isEmpty(merchantCoupons)){
            log.error("执行优惠过期任务:无优惠信息");
            return;
        }

        for (MerchantCoupon merchantCoupon:merchantCoupons) {

            if (merchantCoupon.getCouponExpire()==EXPIRE){
                continue;
            }

            if (merchantCoupon.getCouponTimeEnd().getTime()<=new Date().getTime()){
                log.info("执行优惠过期任务:当前优惠券已过期");
                try {
                    merchantCoupon.setCouponExpire(EXPIRE);
                    merchantCouponService.save(merchantCoupon);
                }catch (Exception e){
                    e.printStackTrace();
                    log.error("执行优惠过期任务:修改优惠券状态异常");
                    continue;
                }
                continue;
            }
        }
        log.info("执行优惠过期任务:任务结束");
    }
}
