package cc.lord.merchant.controller;

import cc.lord.common.annotation.Log;
import cc.lord.common.controller.BaseController;
import cc.lord.common.domain.QueryRequest;
import cc.lord.common.domain.ResponseBo;
import cc.lord.merchant.domain.Label;
import cc.lord.merchant.service.LabelService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("merchant/label")
public class LabelController extends BaseController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    private static final Integer AUDITED=1;//已审核
    private static final Integer NOT_THROUGH=2;//未通过

    @Autowired
    private LabelService labelService;

    @RequestMapping
    @RequiresPermissions("label:list")
    public String index(){
        return "merchant/label";
    }

    @Log("获取标签列表")
    @RequestMapping("list")
    @RequiresPermissions("label:list")
    @ResponseBody
    public Map<String, Object> getLabelList(QueryRequest request, Label label){
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<Label> list = this.labelService.findAllLabelList(label);
        PageInfo<Label> pageInfo = new PageInfo<>(list);
        return getDataTable(pageInfo);
    }

    @Log("获取标签详情")
    @RequestMapping("getLabel")
    @ResponseBody
    public ResponseBo getLabel(Long labelId){
        try {
            Label label=this.labelService.selectByKey(labelId);
            return ResponseBo.ok(label);
        }catch (Exception e){
            log.error("获取标签详情", e);
            return ResponseBo.error("获取标签详情，请联系网站管理员！");
        }
    }

    @Log("删除标签")
    @ResponseBody
    @RequestMapping("deleteLabel")
    @RequiresPermissions("label:delete")
    public ResponseBo deleteLabel(Long labelId){

        if (null==labelId){
            log.error("删除标签:","参数错误");
            return ResponseBo.error("参数错误");
        }
        try {
            Label label=new Label();
            label.setLabelId(labelId);
            this.labelService.removeLabel(label);
            return ResponseBo.ok();
        }catch (Exception e){
            log.error("删除标签", e);
            return ResponseBo.error("删除标签，请联系网站管理员！");
        }
    }

    @Log("禁用标签")
    @RequestMapping("disableLabel")
    @RequiresPermissions("label:disable")
    @ResponseBody
    public ResponseBo disableLabel(Label label){

        if (label.getLabelId()==null){
            log.error("禁用标签:","标签编号错误");
            return ResponseBo.error("标签编号错误");
        }
        if (label.getLabelDisable()==null){
            log.error("禁用标签:","操作参数错误");
            return ResponseBo.error("操作参数错误");
        }
        try {
            this.labelService.disableLabel(label);
            return ResponseBo.ok();
        }catch (Exception e){
            log.error("禁用标签:",e);
            return ResponseBo.error("禁用标签，请联系网站管理员！");
        }
    }

    @Log("审核标签通过")
    @RequestMapping("auditedLabel")
    @RequiresPermissions("label:audited")
    @ResponseBody
    public ResponseBo auditedLabel(Label label){
        if (null==label.getLabelId()){
            log.error("审核标签通过：","编号错误");
            return ResponseBo.error("优惠券编号错误");
        }
        if (null==label.getLabelStatus()){
            log.error("审核标签通过：","状态错误");
            return ResponseBo.error("标签状态错误");
        }
        try {
            label.setLabelStatus(AUDITED);
            this.labelService.modifyLabel(label);
            return ResponseBo.ok();
        }catch (Exception e){
            log.error("审核标签通过:",e);
            return ResponseBo.error("审核标签通过，请联系网站管理员！");
        }
    }

    @Log("审核标签拒绝")
    @RequestMapping("notThroughLabel")
    @RequiresPermissions("label:audited")
    @ResponseBody
    public ResponseBo notThroughLabel(Label label){
        if (null==label.getLabelId()){
            log.error("审核标签拒绝：","编号错误");
            return ResponseBo.error("优惠券编号错误");
        }
        if (null==label.getLabelStatus()){
            log.error("审核标签拒绝：","状态错误");
            return ResponseBo.error("优惠状态错误");
        }
        try {
            label.setLabelStatus(NOT_THROUGH);
            this.labelService.modifyLabel(label);
            return ResponseBo.ok();
        }catch (Exception e){
            log.error("审核标签拒绝:",e);
            return ResponseBo.error("审核商户优惠券拒绝，请联系网站管理员！");
        }
    }

    @Log("添加标签信息")
    @RequiresPermissions("label:add")
    @RequestMapping("addLabel")
    @ResponseBody
    public ResponseBo addLabel(Label label){

        if (null==label.getLabelType()){
            log.error("添加标签:","标签分类未选择");
            return ResponseBo.error("标签分类未选择");
        }
        if (StringUtils.isEmpty(label.getLabelName())){
            log.error("添加标签:","标签名称未填写");
            return ResponseBo.error("标签名称未填写");
        }
        try {
            this.labelService.addLabel(label);
            return ResponseBo.ok();
        }catch (Exception e){
            log.error("添加标签信息", e);
            return ResponseBo.error("添加标签信息，请联系网站管理员！");
        }
    }

    @Log("修改标签信息")
    @ResponseBody
    @RequiresPermissions("label:modify")
    @RequestMapping("modifyLabel")
    public ResponseBo modifyLabel(Label label){
        if (null==label.getLabelId()){
            log.error("修改标签信息：","编号错误");
            return ResponseBo.error("优惠券编号错误");
        }
        try {
            this.labelService.modifyLabel(label);
            return ResponseBo.ok();
        }catch (Exception e){
            log.error("修改标签信息", e);
            return ResponseBo.error("修改标签信息，请联系网站管理员！");
        }
    }

}
