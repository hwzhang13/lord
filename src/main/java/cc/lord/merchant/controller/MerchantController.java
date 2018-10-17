package cc.lord.merchant.controller;

import cc.lord.common.annotation.Log;
import cc.lord.common.controller.BaseController;
import cc.lord.common.domain.QueryRequest;
import cc.lord.common.domain.ResponseBo;
import cc.lord.merchant.domain.Merchant;
import cc.lord.merchant.domain.MerchantVo;
import cc.lord.merchant.service.MerchantService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@RequestMapping("/merchant")
@Controller
public class MerchantController extends BaseController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private static final Integer AUDITED=1;//已审核
    private static final Integer NOT_THROUGH=2;//未通过

    @Autowired
    private MerchantService merchantService;

    @RequestMapping("index")
    @RequiresPermissions("merchant:list")
    public String index(Model model) {
        return "merchant/merchant";
    }

    @RequestMapping("audited")
    @RequiresPermissions("merchant:audited")
    public String auditedIndex(){
        return "merchant/audited";
    }

    /**
     * 查询商户列表
     * @param request
     * @param merchant
     * @return
     */
    @Log("获取商户列表信息")
    @RequestMapping("list")
    @RequiresPermissions("merchant:list")
    @ResponseBody
    public Map<String, Object> merchantList(QueryRequest request, Merchant merchant){
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<MerchantVo> list = this.merchantService.findMerchantList(merchant, request);
        PageInfo<MerchantVo> pageInfo = new PageInfo<>(list);
        return getDataTable(pageInfo);
    }

    /**
     * 查询商户详情
     * @param merchantId
     * @return
     */
    @RequestMapping("getMerchant")
    @ResponseBody
    public ResponseBo getMerchant(Long merchantId){
        try {
            MerchantVo merchant = this.merchantService.findMerchantById(merchantId);
            return ResponseBo.ok(merchant);
        } catch (Exception e) {
            log.error("获取用户失败", e);
            return ResponseBo.error("获取用户失败，请联系网站管理员！");
        }
    }

    @Log("添加商户信息")
    @RequiresPermissions("merchant:addMerchant")
    @RequestMapping("addMerchant")
    @ResponseBody
    public ResponseBo addMerchant(MerchantVo merchant){

        try {
            this.merchantService.addMerchant(merchant);
            return ResponseBo.ok();
        }catch (Exception e){
            log.error("添加商户信息", e);
            return ResponseBo.error("添加商户信息，请联系网站管理员！");
        }
    }

    @Log("修改商户信息")
    @ResponseBody
    @RequiresPermissions("merchant:modifyMerchant")
    @RequestMapping("modifyMerchant")
    public ResponseBo modifyMerchant(MerchantVo merchant){
        try {
            this.merchantService.modifyMerchant(merchant);
            return ResponseBo.ok();
        }catch (Exception e){
            log.error("修改商户信息", e);
            return ResponseBo.error("修改商户信息，请联系网站管理员！");
        }
    }

    @Log("删除商户")
    @RequiresPermissions("merchant:removeMerchant")
    @RequestMapping("deleteMerchant")
    @ResponseBody
    public ResponseBo removeMerchant(Long merchantId){
        if (null==merchantId){
            log.error("删除商户","参数错误");
            return ResponseBo.error("参数错误");
        }
        try {
            this.merchantService.removeMerchant(merchantId);
            return ResponseBo.ok();
        }catch (Exception e){
            log.error("删除商户", e);
            return ResponseBo.error("删除商户，请联系网站管理员！");
        }
    }

    @Log("审核商户通过")
    @RequestMapping("auditedMerchant")
    @RequiresPermissions("merchant:audited")
    @ResponseBody
    public ResponseBo auditedMerchant(Merchant merchant){
        if (null==merchant.getMchId()){
            log.error("审核商户：","编号错误");
            return ResponseBo.error("编号错误");
        }
        /*if (null==merchant.getMchStatus()){
            log.error("审核商户：","状态错误");
            return ResponseBo.error("状态错误");
        }*/
        try {
            merchant.setMchStatus(AUDITED);
            this.merchantService.auditedMerchant(merchant);
            return ResponseBo.ok();
        }catch (Exception e){
            log.error("审核商户:",e);
            return ResponseBo.error("审核商户，请联系网站管理员！");
        }
    }

    @Log("审核商户拒绝")
    @RequestMapping("notThroughMerchant")
    @RequiresPermissions("merchant:audited")
    @ResponseBody
    public ResponseBo notThroughMerchant(Merchant merchant){
        if (null==merchant.getMchId()){
            log.error("审核商户：","编号错误");
            return ResponseBo.error("编号错误");
        }
        /*if (null==merchant.getMchStatus()){
            log.error("审核商户：","状态错误");
            return ResponseBo.error("状态错误");
        }*/
        try {
            merchant.setMchStatus(NOT_THROUGH);
            this.merchantService.notThroughMerchant(merchant);
            return ResponseBo.ok();
        }catch (Exception e){
            log.error("审核商户拒绝:",e);
            return ResponseBo.error("审核商户拒绝，请联系网站管理员！");
        }
    }

    @Log("禁用商户")
    @RequestMapping("disableMerchant")
    @RequiresPermissions("merchant:disable")
    @ResponseBody
    public ResponseBo disableMerchant(Merchant merchant){

        if (merchant.getMchId()==null){
            log.error("禁用商户:","商户编号错误");
            return ResponseBo.error("商户编号错误");
        }
        if (merchant.getMchDisable()==null){
            log.error("禁用商户:","操作参数错误");
            return ResponseBo.error("操作参数错误");
        }
        try {
            this.merchantService.disableMerchant(merchant);
            return ResponseBo.ok();
        }catch (Exception e){
            log.error("禁用商户:",e);
            return ResponseBo.error("禁用商户，请联系网站管理员！");
        }
    }

}
