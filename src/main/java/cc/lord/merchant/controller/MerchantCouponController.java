package cc.lord.merchant.controller;

import cc.lord.common.annotation.Log;
import cc.lord.common.controller.BaseController;
import cc.lord.common.domain.QueryRequest;
import cc.lord.common.domain.ResponseBo;
import cc.lord.merchant.domain.MerchantCoupon;
import cc.lord.merchant.service.MerchantCouponService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("merchant/coupon")
public class MerchantCouponController extends BaseController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    private static final Integer AUDITED=1;//已审核
    private static final Integer NOT_THROUGH=2;//未通过

    @Autowired
    private MerchantCouponService merchantCouponService;

    @RequestMapping
    @RequiresPermissions("coupon:list")
    private String index(){

        return "merchant/coupon";
    }

    @Log("获取优惠列表信息")
    @RequestMapping("list")
    @RequiresPermissions("coupon:list")
    @ResponseBody
    public Map<String, Object> merchantCouponList(QueryRequest request, MerchantCoupon merchantCoupon){
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<MerchantCoupon> list = this.merchantCouponService.findMerchantCouponList(merchantCoupon, request);
        PageInfo<MerchantCoupon> pageInfo = new PageInfo<>(list);
        return getDataTable(pageInfo);
    }

    @Log("获取商户优惠券列表")
    @RequestMapping("mchCouponList")
    @ResponseBody
    public ResponseBo getMerchantCouponByMchId(Long mchId){
        try {
            List<MerchantCoupon> list = this.merchantCouponService.findMerchantCouponByMchId(mchId);
            return ResponseBo.ok(list);
        }catch (Exception e){
            log.error("获取商户优惠失败", e);
            return ResponseBo.error("获取商户优惠失败，请联系网站管理员！");
        }

    }

    @Log("获取优惠券详情")
    @RequestMapping("getMerchantCoupon")
    @ResponseBody
    public ResponseBo getMerchantCoupon(Long couponId){
        try {
            MerchantCoupon merchantCoupon=this.merchantCouponService.selectByKey(couponId);
            return ResponseBo.ok(merchantCoupon);
        }catch (Exception e){
            log.error("获取商户优惠失败", e);
            return ResponseBo.error("获取商户优惠失败，请联系网站管理员！");
        }
    }

    @Log("删除商户优惠券")
    @ResponseBody
    @RequestMapping("deleteMerchantCoupon")
    @RequiresPermissions("coupon:delete")
    public ResponseBo deleteMerchantCoupon(Long couponId){
        try {
            this.merchantCouponService.deleteMerchantCouponById(couponId);
            return ResponseBo.ok();
        }catch (Exception e){
            log.error("删除商户优惠失败", e);
            return ResponseBo.error("删除商户优惠失败，请联系网站管理员！");
        }
    }

    @Log("禁用商户优惠券")
    @RequestMapping("disableMerchantCoupon")
    @RequiresPermissions("coupon:disable")
    @ResponseBody
    public ResponseBo disableMerchant(MerchantCoupon merchantCoupon){

        if (merchantCoupon.getCouponId()==null){
            log.error("禁用商户优惠券:","商户编号错误");
            return ResponseBo.error("商户编号错误");
        }
        if (merchantCoupon.getCouponDisable()==null){
            log.error("禁用商户优惠券:","操作参数错误");
            return ResponseBo.error("操作参数错误");
        }
        try {
            this.merchantCouponService.disableMerchantCoupon(merchantCoupon);
            return ResponseBo.ok();
        }catch (Exception e){
            log.error("禁用商户优惠券:",e);
            return ResponseBo.error("禁用商户优惠券，请联系网站管理员！");
        }
    }

    @Log("审核商户优惠券通过")
    @RequestMapping("auditedMerchantCoupon")
    @RequiresPermissions("coupon:audited")
    @ResponseBody
    public ResponseBo auditedMerchant(MerchantCoupon merchantCoupon){
        if (null==merchantCoupon.getCouponId()){
            log.error("审核商户优惠：","编号错误");
            return ResponseBo.error("优惠券编号错误");
        }
        /*if (null==merchantCoupon.getCouponStatus()){
            log.error("审核商户优惠：","状态错误");
            return ResponseBo.error("优惠状态错误");
        }*/
        try {
            merchantCoupon.setCouponStatus(AUDITED);
            this.merchantCouponService.auditedMerchantCoupon(merchantCoupon);
            return ResponseBo.ok();
        }catch (Exception e){
            log.error("审核商户优惠券通过:",e);
            return ResponseBo.error("审核商户优惠券通过，请联系网站管理员！");
        }
    }

    @Log("审核商户优惠券拒绝")
    @RequestMapping("notThroughMerchantCoupon")
    @RequiresPermissions("coupon:audited")
    @ResponseBody
    public ResponseBo notThroughMerchant(MerchantCoupon merchantCoupon){
        if (null==merchantCoupon.getCouponId()){
            log.error("审核商户优惠：","编号错误");
            return ResponseBo.error("优惠券编号错误");
        }
        /*if (null==merchantCoupon.getCouponStatus()){
            log.error("审核商户优惠：","状态错误");
            return ResponseBo.error("优惠状态错误");
        }*/
        try {
            merchantCoupon.setCouponStatus(NOT_THROUGH);
            this.merchantCouponService.notThroughMerchantCoupon(merchantCoupon);
            return ResponseBo.ok();
        }catch (Exception e){
            log.error("审核商户优惠券拒绝:",e);
            return ResponseBo.error("审核商户优惠券拒绝，请联系网站管理员！");
        }
    }

    @Log("添加商户优惠信息")
    @RequiresPermissions("coupon:add")
    @RequestMapping("addMerchantCoupon")
    @ResponseBody
    public ResponseBo addMerchantCoupon(MerchantCoupon merchantCoupon){

        try {
            this.merchantCouponService.save(merchantCoupon);
            return ResponseBo.ok();
        }catch (Exception e){
            log.error("添加商户信息", e);
            return ResponseBo.error("添加商户信息，请联系网站管理员！");
        }
    }

    @Log("修改商户优惠信息")
    @ResponseBody
    @RequiresPermissions("coupon:modify")
    @RequestMapping("modifyMerchantCoupon")
    public ResponseBo modifyMerchantCoupon(MerchantCoupon merchantCoupon){
        if (null==merchantCoupon.getCouponId()){
            log.error("修改商户优惠信息：","编号错误");
            return ResponseBo.error("优惠券编号错误");
        }
        try {
            this.merchantCouponService.save(merchantCoupon);
            return ResponseBo.ok();
        }catch (Exception e){
            log.error("修改商户优惠信息", e);
            return ResponseBo.error("修改商户优惠信息，请联系网站管理员！");
        }
    }
}
